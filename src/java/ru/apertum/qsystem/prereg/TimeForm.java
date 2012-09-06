package ru.apertum.qsystem.prereg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SortedSet;
import java.util.TreeSet;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Messagebox;
import ru.apertum.qsystem.common.NetCommander;
import ru.apertum.qsystem.common.cmd.RpcGetGridOfWeek;
import ru.apertum.qsystem.common.model.QAdvanceCustomer;
import ru.apertum.qsystem.prereg.core.MailSender;
import ru.apertum.qsystem.prereg.core.ServiceList;

public class TimeForm {

    private Client client = (Client) Sessions.getCurrent().getAttribute("DATA");

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    private final TreeSet<Date> timeList = new TreeSet<>();

    public TreeSet<Date> getTimeList() {
        return timeList;
    }
    private Date selectedDate;

    public Date getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }
    private Date date = new Date();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Command
    public void submit() {
        if (selectedDate != null) {
            // ставим предварительного кастомера
            final QAdvanceCustomer result = NetCommander.standInServiceAdvance(ServiceList.netProperty, client.getService().getId(), selectedDate, -1, client.getInputData());
            client.setAdvClient(result);
            client.setDate(selectedDate);
            if (client.getEmail() != null && !client.getEmail().isEmpty()) {
                MailSender.getInstance().sendMessage(client);
            }
            Executions.sendRedirect("/finish.zul");
        }
    }

    @Command
    public void back() {
        //Executions.sendRedirect("/ss");
        Executions.sendRedirect("/selectService.zul");
    }

    @Command
    @NotifyChange("timeList")
    public void changeDate() {
        RpcGetGridOfWeek.GridAndParams gp;
        try {
            gp = NetCommander.getGridOfWeek(ServiceList.netProperty, client.getService().getId(), date, -1);
        } catch (Exception ex) {
            throw new RuntimeException("Bad net conversation: " + ex);
        }
        timeList.clear();
        if (gp.getSpError() == null) {
            final GregorianCalendar gcSt = new GregorianCalendar();
            gcSt.setTime(gp.getStartTime());
            final GregorianCalendar gcFin = new GregorianCalendar();
            gcFin.setTime(gp.getFinishTime());

            final GregorianCalendar gc = new GregorianCalendar();
            final GregorianCalendar gc1 = new GregorianCalendar();
            gc.setTime(date);
            if (gc.get(GregorianCalendar.DAY_OF_YEAR) > gc1.get(GregorianCalendar.DAY_OF_YEAR)) {
                for (Date d : gp.getTimes()) {
                    gc1.setTime(d);
                    if (gc.get(GregorianCalendar.DAY_OF_YEAR) == gc1.get(GregorianCalendar.DAY_OF_YEAR)
                            && gc1.get(GregorianCalendar.HOUR_OF_DAY) > gcSt.get(GregorianCalendar.HOUR_OF_DAY)
                            && gc1.get(GregorianCalendar.HOUR_OF_DAY) < gcFin.get(GregorianCalendar.HOUR_OF_DAY)) {
                        timeList.add(d);
                    }
                }
            }
        } else {
            System.out.println(gp.getSpError());
        }

    }
}

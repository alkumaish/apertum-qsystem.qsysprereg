package ru.apertum.qsystem.prereg;

import java.util.LinkedList;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import ru.apertum.qsystem.prereg.core.ServiceList;
import ru.apertum.qsystem.server.model.QService;

public class ServiceForm {

    private Client client = (Client) Sessions.getCurrent().getAttribute("DATA");

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    private final LinkedList<QService> serviceList = ServiceList.getInstance().getServiceList();

    public LinkedList<QService> getServiceList() {
        return serviceList;
    }
    private QService selectedService;

    public QService getSelectedService() {
        return selectedService;
    }

    public void setSelectedService(QService selectedService) {
        selectedService.setInput_caption(selectedService.getInput_caption().replaceAll("<.*?>",""));
        this.selectedService = selectedService;
    }

    @Command
    public void back() {
        Executions.sendRedirect("/");
    }

    @Command
    public void submit() {
        if (selectedService != null) {
            System.out.println(selectedService.getId() + " " + selectedService.getName() + " " + selectedService.getInput_caption());
            client.setService(selectedService);
            Executions.sendRedirect(selectedService.getInput_required() ? "/inputServiceData.zul" : "/selectTime.zul");
        }
    }
}

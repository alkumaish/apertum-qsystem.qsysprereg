package ru.apertum.qsystem.prereg;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import ru.apertum.qsystem.prereg.core.ServiceList;

public class ClientForm {

    private String com;

    @Init
    public void init() {
        this.com = Executions.getCurrent().getParameter("com");
        if (com == null) {
            com = "";
        }
        Sessions.getCurrent().setAttribute("PROPS", new SiteProperty(com,
                System.getProperty(com + "_" + "QSYSPREREG_TITLE", "Это заголовок QSYSPREREG_TITLE"),
                System.getProperty(com + "_" + "QSYSPREREG_CAPTION", "Это тоже заголовок QSYSPREREG_CAPTION"),
                System.getProperty(com + "_" + "QSYSPREREG_LOGO", ""),
                System.getProperty(com + "_" + "QSYSPREREG_PASSWORD", ""),
                System.getProperty(com + "_" + "QSYSTEM_SERVER_ADDR", "127.0.0.1"),
                System.getProperty(com + "_" + "QSYSTEM_SERVER_PORT", "3128").matches("-?\\d+(\\.\\d+)?")
                ? Integer.parseInt(System.getProperty(com + "_" + "QSYSTEM_SERVER_PORT", "3128")) : 3128));
    }
    private final RandomStringGenerator rsg = new RandomStringGenerator(5);
    private Client client = new Client();
    private String captcha = rsg.getRandomString(), captchaInput;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public Client getClient() {
        final Client cl = (Client) Sessions.getCurrent().getAttribute("DATA");
        if (cl != null) {
            client = cl;
        }
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getCaptchaInput() {
        return captchaInput;
    }

    public void setCaptchaInput(String captchaInput) {
        this.captchaInput = captchaInput;
    }

    public void regenerateCaptcha() {
        this.captcha = rsg.getRandomString();
    }
    private String foregroundColour = "#000000", backgroundColour = "#FDC966";

    public String getForegroundColour() {
        return foregroundColour;
    }

    public void setForegroundColour(String foregroundColor) {
        this.foregroundColour = foregroundColor;
    }

    public String getBackgroundColour() {
        return backgroundColour;
    }

    public void setBackgroundColour(String backgroundColor) {
        this.backgroundColour = backgroundColor;
    }

    @Command
    @NotifyChange("captcha")
    public void regenerate() {
        this.regenerateCaptcha();
    }

    @Command
    public void submit() {
        try {
            ServiceList.getInstance().getServiceList();
            Sessions.getCurrent().setAttribute("DATA", client);
            //Executions.sendRedirect("/selectService");
            Executions.sendRedirect("/selectService.zul");
        } catch (Throwable t) {
            System.err.println("Server SOO is down! " + t);
            Executions.sendRedirect("/error.zul");
        }
    }
}

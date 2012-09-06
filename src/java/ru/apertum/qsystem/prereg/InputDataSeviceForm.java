package ru.apertum.qsystem.prereg;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;

public class InputDataSeviceForm {

    private Client client = (Client) Sessions.getCurrent().getAttribute("DATA");

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
   
    private String inputData = "";

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData;
    }
    

    @Command
    public void submit() {
        if (inputData != null && !inputData.isEmpty()) {
            client.setInputData(inputData);
            Executions.sendRedirect("/selectTime.zul");
        }
    }

    @Command
    public void back() {
        //Executions.sendRedirect("/ss");
        Executions.sendRedirect("/selectService.zul");
    }

}

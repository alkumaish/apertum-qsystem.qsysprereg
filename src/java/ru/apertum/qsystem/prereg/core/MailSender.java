/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.apertum.qsystem.prereg.core;

import java.io.UnsupportedEncodingException;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ru.apertum.qsystem.prereg.Client;

/**
 *
 * @author Evgeniy Egorov
 */
public class MailSender {

    private MailSender() {
        try {
            ctx = new InitialContext();
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static MailSender getInstance() {
        return MailSenderHolder.INSTANCE;
    }

    private static class MailSenderHolder {

        private static final MailSender INSTANCE = new MailSender();
    }
    final private InitialContext ctx;

    public synchronized void sendMessage(Client client) {

        try {
            final Session mailSession = (Session) ctx.lookup("QSYSPREREG-MAIL");
            final Message msg = new MimeMessage(mailSession);
            msg.setSubject("[" + System.getProperty("QSYSPREREG_TITLE") + "] Предварительная регистрация " + client.getAdvClient().getId());
            msg.setRecipient(RecipientType.TO, new InternetAddress(client.getEmail(), client.toString()));
            msg.setText(System.getProperty("QSYSPREREG_CAPTION") + "\n\n\n"
                    + "   Здравствуйте " + client.getSourname() + " " + client.getName() + " " + client.getMiddlename() + "\n\n"
                    + "Вы зарегистрированы предварительно для получения услуги " + client.getService().getName() + "\n"
                    + "Номер регистрации " + client.getAdvClient().getId() + "\n"
                    + "Этот номер необходимо ввести при получении талона на киоске регистрации, не потеряйте его.\n"
                    + "Вам необходимо прийти " + client.getDay() + " c " + client.getStartT() + " до " + client.getFinishT() + "\n\n\n"
                    + client.getService().getInput_caption() + "  " + client.getInputData() + "\n\n"
                    + client.getService().getPreInfoPrintText() + "\n\n\n"
                    + "Это письмо выслано автоматически. Не отвечайте на него.\n"
                    + "QSystem - Copyright 2012 Apertum Projects");
            Transport.send(msg);
        } catch (NamingException | MessagingException | UnsupportedEncodingException me) {
            System.err.println("Server SUO isnt make mail! " + me);
        }
    }
}

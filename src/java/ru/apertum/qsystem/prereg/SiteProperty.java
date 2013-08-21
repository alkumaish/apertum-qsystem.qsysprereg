/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.apertum.qsystem.prereg;

/**
 *
 * @author Evgeniy Egorov
 */
public class SiteProperty {

    String id;
    String title;
    String caption;
    String logoPath;
    String password;
    String serverAddr;
    int serverPort;

    public SiteProperty(String id, String title, String caption, String logoPath, String password, String serverAddr, int serverPort) {
        this.id = id;
        this.title = title;
        this.caption = caption;
        this.logoPath = logoPath;
        this.password = password;
        this.serverAddr = serverAddr;
        this.serverPort = serverPort;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getServerAddr() {
        return serverAddr;
    }

    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
}

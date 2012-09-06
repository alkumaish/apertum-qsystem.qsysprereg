/*
 *  Copyright (C) 2010 {Apertum}Projects. web: www.apertum.ru email: info//@apertum.ru
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ru.apertum.qsystem.server.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ru.apertum.qsystem.common.model.QCustomer;


/**
 * Это пользователь. По большому счету роль и пользователь совпадают в системе.
 * Класс пользователя системы.
 * //@author Evgeniy Egorov
 */
//@Entity
//@Table(name = "users")
public class QUser {

    /**
     * Конструктор для формирования из БД.
     */
    public QUser() {
    }

    //@Override
    public String toString() {
        return getName();
    }
    @Expose
    @SerializedName("id")
    private Long id;

    //@Id
    //@Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Удаленный или нет.
     * Нельзя их из базы гасить чтоб констрейнты не поехали.
     * 0 - удаленный
     * 1 - действующий
     * Только для БД.
     */
    @Expose
    @SerializedName("enable")
    private Integer enable = 1;

    //@Column(name = "enable")
    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
    /**
     * Параметр доступа к администрированию системы.
     */
    @Expose
    @SerializedName("is_admin")
    private Boolean adminAccess = false;

    public final void setAdminAccess(Boolean adminAccess) {
        this.adminAccess = adminAccess;
    }

    //@Column(name = "admin_access")
    public Boolean getAdminAccess() {
        return adminAccess;
    }
    /**
     * Параметр доступа к отчетам системы.
     */
    @Expose
    @SerializedName("is_report_access")
    private Boolean reportAccess = false;

    public final void setReportAccess(Boolean reportAccess) {
        this.reportAccess = reportAccess;
    }

    //@Column(name = "report_access")
    public Boolean getReportAccess() {
        return reportAccess;
    }
    /**
     * Пароль пользователя. В программе хранится открыто.
     * В базе и xml зашифрован.
     */
    @Expose
    @SerializedName("pass")
    private String password = "";

    /**
     * Расшифрует
     * //@param password - зашифрованное слово
     */
    public final void setPassword(String password) {
        this.password = password;
    }

    /**
     * Зашифрует
     * //@return пароль в зашифрованном виде.
     */
    //@Column(name = "password")
    public String getPassword() {
        return password;
    }

    public boolean isCorrectPassword(String password) {
        return this.password.equals(password);
    }

    public void recoverAccess(String access) {
        this.password = access;
    }
    /**
     * Идентификатор рабочего места пользователя.
     */
    @Expose
    @SerializedName("point")
    private String point;

    public final void setPoint(String point) {
        this.point = point;
    }

    //@Column(name = "point")
    public String getPoint() {
        return point;
    }
    /**
     * Название пользователя.
     */
    @Expose
    @SerializedName("name")
    private String name;

    public final void setName(String name) {
        this.name = name;
    }

    //@Column(name = "name")
    //@Override
    public String getName() {
        return name;
    }
    @Expose
    @SerializedName("adress_rs")
    private Integer adressRS;

    public final void setAdressRS(Integer adressRS) {
        this.adressRS = adressRS;
    }

    //@Column(name = "adress_rs")
    public Integer getAdressRS() {
        return adressRS;
    }
    //******************************************************************************************************************
    //******************************************************************************************************************
    //************************************** Услуги юзера **************************************************************
    /**
     * Множество услуг, которые обрабатывает юзер.
     * По наименованию услуги получаем Класс - описалово участия юзера в этой услуге/
     * Имя услуги -> IProperty
     */
    
    /**
     * Количество услуг, которые обрабатывает юзер. // едет на коиента при логине
     */
    @Expose
    @SerializedName("services_cnt")
    private int servicesCnt = 0;

    public void setServicesCnt(int servicesCnt) {
        this.servicesCnt = servicesCnt;
    }

    /**
     * Количество услуг, которые обрабатывает юзер. // едет на коиента при логине
     * //@return
     */
    //@Transient
    public int getServicesCnt() {
        return servicesCnt;
    }
    /**
     * Customer, который попал на обработку к этому юзеру.
     * При вызове следующего, первый в очереди кастомер, выдерается из этой очереди совсем и
     * попадает сюда. Сдесь он живет и переживает все интерпритации, которые с ним делает юзер.
     * При редиректе в другую очередь юзером, данный кастомер отправляется в другую очередь,
     * возможно, с другим приоритетом, а эта ссылка становится null.
     */
    private QCustomer customer = null;

    public void setCustomer(QCustomer customer) {
        // небыло и не ставим
        if (customer == null && getCustomer() == null) {
            return;
        }
        // был кастомер у юзера и убираем его
        if (customer == null && getCustomer() != null) {
            // если убирается кастомер, то надо убрать признак юзера, который работает с кастомером
            if (getCustomer().getUser() != null) {
                getCustomer().setUser(null);
            }
            // раз юзера убрали, то и время начала работы этого юзера тож убирать
            if (getCustomer().getStartTime() != null) {
                getCustomer().setStartTime(null);
            }
        } else {
            // иначе кастомеру, определившимуся к юзеру, надо поставить признак работы с опред. юзером.
            customer.setUser(this);
        }
        this.customer = customer;
    }

    //@Transient
    public QCustomer getCustomer() {
        return customer;
    }
}

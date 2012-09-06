/*
 *  Copyright (C) 2010 {Apertum}Projects. web: www.apertum.ru email: info@apertum.ru
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
package ru.apertum.qsystem.common;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;


/**
 * @author Evgeniy Egorov
 * Сдесь находятся константы и общеиспользуемые конструкции
 *
 */
public final class Uses {

    // значения приоритета "очередника"
    public static final int PRIORITY_LOW = 0;
    public static final int PRIORITY_NORMAL = 1;
    public static final int PRIORITY_HI = 2;
    public static final int PRIORITY_VIP = 3;
    public static final int[] PRIORITYS = {PRIORITY_LOW, PRIORITY_NORMAL, PRIORITY_HI, PRIORITY_VIP};
    public static final LinkedHashMap<Integer, String> PRIORITYS_WORD = new LinkedHashMap<Integer, String>();
    // значения приоритета обрабатываемых услуг для юзера
    public static final int SERVICE_EXCLUDE = -1;
    public static final int SERVICE_REMAINS = 0;
    public static final int SERVICE_NORMAL = 1;
    public static final int SERVICE_VIP = 2;
    public static final int[] SERVICE_PRIORITYS = {SERVICE_EXCLUDE, SERVICE_REMAINS, SERVICE_NORMAL, SERVICE_VIP};
    public static final LinkedHashMap<Integer, String> COEFF_WORD = new LinkedHashMap<Integer, String>();
    
    // Наименования тегов и атрибутов в протоколах XML по статистике
    public static final String TAG_REP_STATISTIC = "Статистика";
    public static final String TAG_REP_PARAM_COUNT = "Знаменатель";
    public static final String TAG_REP_PARAM_AVG = "Среднее";
    public static final String TAG_REP_RECORD = "Запись";
    public static final String TAG_REP_SERVICE_WORKED = "ОбслуженоПоУслуге";
    public static final String TAG_REP_SERVICE_WAIT = "ОжидаютПоУслуге";
    public static final String TAG_REP_SERVICE_AVG_WORK = "СрВрОбслуживанияПоУслуге";
    public static final String TAG_REP_SERVICE_AVG_WAIT = "СрВрОжиданияПоУслуге";
    public static final String TAG_REP_SERVICE_KILLED = "ОтклоненныхПоУслуге";
    public static final String TAG_REP_USER_WORKED = "ОбслуженоПользователем";
    public static final String TAG_REP_USER_AVG_WORK = "СрВрОбслуживанияПользователем";
    public static final String TAG_REP_USER_KILLED = "ОтклоненныхПользователем";
    public static final String TAG_REP_WORKED = "Обслуженных";
    public static final String TAG_REP_AVG_TIME_WORK = "СрВрОбслуживания";
    public static final String TAG_REP_KILLED = "Отклоненных";
    // теги и имена атрибутов настроечного файла
    public static final String TAG_PROP_SERVICES = "Услуги";
    public static final String TAG_PROP_SERVICE = "Услуга";
    public static final String TAG_PROP_NAME = "Наименование";
    public static final String TAG_PROP_DESCRIPTION = "Описание";
    public static final String TAG_PROP_PREFIX = "Префикс";
    public static final String TAG_PROP_ADVANCE_LIMIT = "Лимит";
    public static final String TAG_PROP_ADVANCE_PERIOD_LIMIT = "ЛимитПредвЗаписиВДнях";
    public static final String TAG_PROP_USERS = "Пользователи";
    public static final String TAG_PROP_USER = "Пользователь";
    public static final String TAG_PROP_PASSWORD = "Пароль";
    public static final String TAG_PROP_OWN_SERVS = "ОказываемыеУслуги";
    public static final String TAG_PROP_OWN_SRV = "ОказываемаяУслуга";
    public static final String TAG_PROP_KOEF = "КоэффициентУчастия";
    public static final String TAG_PROP_CONNECTION = "Сеть";
    public static final String TAG_PROP_SERV_PORT = "ПортСервера";
    public static final String TAG_PROP_WEB_SERV_PORT = "ПортВебСервера";
    public static final String TAG_PROP_CLIENT_PORT = "ПортКлиента";
    public static final String TAG_PROP_SERV_ADDRESS = "АдресСервера";
    public static final String TAG_PROP_CLIENT_ADDRESS = "АдресКлиента";
    public static final String TAG_PROP_STATUS = "Статус";
    public static final String TAG_PROP_START_TIME = "ВремяНачалаРаботы";
    public static final String TAG_PROP_FINISH_TIME = "ВремяЗавершенияРаботы";
    public static final String TAG_PROP_VERSION = "ВерсияХранилищаКонфигурации";
    public static final String TAG_PROP_INPUT_REQUIRED = "ТребованиеКлиентскихДанных";
    public static final String TAG_PROP_INPUT_CAPTION = "ЗаголовокФормыВводаКлДанных";
    public static final String TAG_PROP_RESULT_REQUIRED = "ТребованиеРезультатаРаботы";
    // теги и имена атрибутов конфигурационных файлов главных табло
    public static final String TAG_BOARD_PROPS = "Параметры";
    public static final String TAG_BOARD_PROP = "Параметер";
    public static final String TAG_BOARD_NAME = "Наименование";
    public static final String TAG_BOARD_VALUE = "Значение";
    public static final String TAG_BOARD_TYPE = "Тип";
    // имена параметров для табло 
    public static final String TAG_BOARD_LINES_COUNT = "Количество строк на табло";
    public static final String TAG_BOARD_DELAY_VISIBLE = "Минимальное время индикации на табло";
    public static final String TAG_BOARD_FON_IMG = "Фоновое изображение";
    public static final String TAG_BOARD_FONT_SIZE = "Размер шрифта";
    public static final String TAG_BOARD_FONT_COLOR = "Цвет шрифта";
    public static final String TAG_BOARD_PANEL_SIZE = "Размер";
    public static final String TAG_BOARD_RUNNING_TEXT = "Бегущий текст";
    public static final String TAG_BOARD_VIDEO_FILE = "Видеофайл";
    public static final String TAG_BOARD_VISIBLE_PANEL = "visible";
    public static final String TAG_BOARD_SPEED_TEXT = "Скорость бегущего текста";
    public static final String TAG_BOARD_SIMPLE_DATE = "Простая дата";
    public static final String TAG_BOARD_FON_COLOR = "Цвет фона";
    public static final String TAG_BOARD_FONT_SIZE_CAPTION = "Размер шрифта заголовка";
    public static final String TAG_BOARD_FONT_SIZE_LINE = "Размер шрифта строк";
    public static final String TAG_BOARD_FONT_COLOR_CAPTION = "Цвет шрифта заголовка";
    public static final String TAG_BOARD_FONT_COLOR_LEFT = "Цвет шрифта левого столбца";
    public static final String TAG_BOARD_FONT_COLOR_RIGHT = "Цвет шрифта правого столбца";
    public static final String TAG_BOARD_LINE_BORDER = "Окантовка строк";
    public static final String TAG_BOARD_LINE_DELIMITER = "Разделитель столбцов";
    //имена тегов-разделов для табло
    public static final String TAG_BOARD = "Board";
    public static final String TAG_BOARD_MAIN = "Main";
    public static final String TAG_BOARD_TOP = "Top";
    public static final String TAG_BOARD_BOTTOM = "Bottom";
    public static final String TAG_BOARD_LEFT = "Left";
    public static final String TAG_BOARD_RIGHT = "Right";
    // Наименования параметров конфигурационных файлов главных табло
    public static final String BOARD_VALUE_PAUSE = "Время присутствия записи на табло";
    public static final String BOARD_ADRESS_MAIN_BOARD = "Адрес главного табло системы";
    public static final int BOARD_TYPE_INT = 1;
    public static final int BOARD_TYPE_DOUBLE = 2;
    public static final int BOARD_TYPE_STR = 3;
    public static final int BOARD_TYPE_BOOL = 4;
    // Наименования заданий
    public static final String TASK_FOR_ALL_SITE = "Для всех сайтов домена";
    public static final String TASK_STAND_IN = "Поставить в очередь";
    public static final String TASK_ADVANCE_STAND_IN = "Поставить в очередь предварительно";
    public static final String TASK_ADVANCE_CHECK_AND_STAND = "Поставить предварительно записанного";
    public static final String TASK_REDIRECT_CUSTOMER = "Переадресовать клиента к другой услуге";
    public static final String TASK_GET_SERVICES = "Получить перечень услуг";
    public static final String TASK_ABOUT_SERVICE = "Получить описание услуги";
    public static final String TASK_ABOUT_SERVICE_PERSON_LIMIT = "Получить возможность встать с этими данными";
    public static final String TASK_GET_SERVICE_PREINFO = "Получить информацию по услуге";
    public static final String TASK_GET_INFO_PRINT = "Получить информацию для печати";
    public static final String TASK_GET_USERS = "Получить перечень пользователей";
    public static final String TASK_GET_SELF = "Получить описание пользователя";
    public static final String TASK_GET_SELF_SERVICES = "Получить состояние очередей";
    public static final String TASK_GET_POSTPONED_POOL = "Получить состояние пула отложенных";
    public static final String TASK_INVITE_POSTPONED = "Вызвать отложенного из пула отложенных";
    public static final String TASK_GET_SELF_SERVICES_CHECK = "Получить состояние очередей с проверкой";
    public static final String TASK_INVITE_NEXT_CUSTOMER = "Получить следующего клиента";
    public static final String TASK_KILL_NEXT_CUSTOMER = "Удалить следующего клиента";
    public static final String TASK_CUSTOMER_TO_POSTPON = "Клиента в пул отложенных";
    public static final String TASK_POSTPON_CHANGE_STATUS = "Сменить статус отложенному";
    public static final String TASK_START_CUSTOMER = "Начать работу с клиентом";
    public static final String TASK_FINISH_CUSTOMER = "Закончить работу с клиентом";
    public static final String TASK_I_AM_LIVE = "Я горец!";
    public static final String TASK_RESTART = "RESTART";
    public static final String TASK_RESTART_MAIN_TABLO = "Рестарт главного твбло";
    public static final String TASK_REFRESH_POSTPONED_POOL = "NEW_POSTPONED_NOW";
    public static final String TASK_SERVER_STATE = "Получить состояние сервера";
    public static final String TASK_SET_SERVICE_FIRE = "Добавить услугу на горячую";
    public static final String TASK_DELETE_SERVICE_FIRE = "Удалить услугу на горячую";    // Наименования отчетов, сдесь писать исключительно маленькими латинскими буквами без пробелов
    public static final String TASK_GET_BOARD_CONFIG = "Получить конфигурацию табло";
    public static final String TASK_SAVE_BOARD_CONFIG = "Сохранить конфигурацию табло";
    public static final String TASK_GET_GRID_OF_WEEK = "Получить недельную предварительную таблицу";
    public static final String TASK_GET_INFO_TREE = "Получить информационное дерево";
    public static final String TASK_GET_RESULTS_LIST = "Получить получение списка возможных результатов";
    public static final String TASK_GET_RESPONSE_LIST = "Получить список отзывов";
    public static final String TASK_SET_RESPONSE_ANSWER = "Оставить отзыв";
    public static final String REPORT_CURRENT_USERS = "current_users";
    public static final String REPORT_CURRENT_SERVICES = "current_services";
    public static final String TASK_GET_CLIENT_AUTHORIZATION = "Идентифицировать клиента";
    public static final String TASK_SET_CUSTOMER_PRIORITY = "Изменить приоритет";
    public static final String TASK_CHANGE_FLEX_PRIORITY = "Изменить гибкий приоритет";
    // Формат отчетов
    public static final String REPORT_FORMAT_HTML = "html";
    public static final String REPORT_FORMAT_RTF = "rtf";
    public static final String REPORT_FORMAT_PDF = "pdf";
    // Якорь для списка аналитических отчетов
    public static final String ANCHOR_REPORT_LIST = "<tr><td><center>#REPORT_LIST_ANCHOR#</center></td></tr>";
    public static final String ANCHOR_DATA_FOR_REPORT = "#DATA_FOR_REPORT#";
    public static final String ANCHOR_ERROR_INPUT_DATA = "#ERROR_INPUT_DATA#";
    public static final String ANCHOR_USERS_FOR_REPORT = "#USERS_LIST_ANCHOR#";
    public static final String ANCHOR_COOCIES = "#COOCIES_ANCHOR#";
    // Задания для пункта регистрации
    public static final String WELCOME_LOCK = "#WELCOME_LOCK#";
    public static final String WELCOME_UNLOCK = "#WELCOME_UNLOCK#";
    public static final String WELCOME_OFF = "#WELCOME_OFF#";
    public static final String WELCOME_REINIT = "#WELCOME_REINIT#";
    /**
     * Формат даты
     */
    public static final String DATE_FORMAT = "dd.MM.yyyy HH:mm:ss";
    /**
     * Формат даты без времени
     */
    public static final String DATE_FORMAT_ONLY = "dd.MM.yyyy";
    /**
     * Формат даты без времени, с годом и месяц прописью
     */
    public static final String DATE_FORMAT_FULL = "dd MMMM yyyy";
    /**
     * Формат даты.
     */
    public final static DateFormat format_HH_mm = new SimpleDateFormat("HH:mm");
    /**
     * Формат даты.
     */
    public final static DateFormat format_HH_mm_ss = new SimpleDateFormat("HH:mm:ss");
    /**
     * Формат даты.
     */
    public final static DateFormat format_dd_MM_yyyy = new SimpleDateFormat(DATE_FORMAT_ONLY);
    /**
     * Формат даты. dd_MMMM_yyyy
     */
    public final static DateFormat format_dd_MMMM_yyyy = new SimpleDateFormat(DATE_FORMAT_FULL);
    /**
     * Формат даты.
     */
    public final static DateFormat format_dd_MM_yyyy_time = new SimpleDateFormat(DATE_FORMAT);
    /**
     * Формат даты.
     */
    public final static DateFormat format_dd_MMMM = new SimpleDateFormat("dd MMMM");
    /**
     * Формат даты./2009-01-26 16:10:41
     */
    public final static DateFormat format_for_rep = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * Формат даты./2009-01-26 16:10
     */
    public final static DateFormat format_for_trans = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    /**
     * Формат даты./2009 январь 26 16:10:41
     */
    public final static DateFormat format_for_label = new SimpleDateFormat("dd MMMM HH.mm.ss");
    /**
     * Временная папка для файлов сохранения состояния для помехоустойчивости
     */
    public static final String TEMP_FOLDER = "temp";
    /**
     * временный файл сохранения состояния для помехоустойчивости
     */
    public static final String TEMP_STATE_FILE = "temp.json";
    /**
     * временный файл сохранения текущей статистики для помехоустойчивости
     */
    public static final String TEMP_STATATISTIC_FILE = "temp_statistic.xml";
    /**
     * Задержка перед возвратом в корень меню при вопросе "Желаете встать в очередь?" когда в очереди более трех человек.
     */
    public static final int DELAY_BACK_TO_ROOT = 10000;
    /**
     * Задержка перед проверкой блокирования пункта регистрации
     */
    public static final int DELAY_CHECK_TO_LOCK = 55000;
    /**
     * Константа возврата в пункт регистрации кол-во клиентов в очереди, в случае если услуга не обрабатывается ни одним пользователем
     */
    public static final int LOCK_INT = 1000000000;
    /**
     * Константа возврата в пункт регистрации кол-во клиентов в очереди, в случае если услуга не оказывается учитывая расписание
     */
    public static final int LOCK_FREE_INT = 1000000011;
    /**
     * Константа возврата в пункт регистрации кол-во клиентов в очереди, в случае если услуга не оказывается учитывая ограничение посещений в день и лимит достигнут
     */
    public static final int LOCK_PER_DAY_INT = 1000000022;
    /**
     * Вопрос о живости
     */
    public static final String HOW_DO_YOU_DO = "do you live?";

    

   

    /**
     * Послать сообщение по UDP
     * @param message текст посылаемого сообщения
     * @param address адрес получателя. Если адрес "255.255.255.255", то рассылка будет широковещательной.
     * @param port порт получателя
     */
    public static void sendUDPMessage(String message, InetAddress address, int port) {
        //QLog.l().logger().trace("Отправка UDP сообшение \"" + message + "\" по адресу \"" + address.getHostAddress() + "\" на порт \"" + port + "\"");
        final DatagramSocket socket;
        final byte mess_b[] = message.getBytes();
        final DatagramPacket packet = new DatagramPacket(mess_b, mess_b.length, address, port);
        try {
            socket = new DatagramSocket();
        } catch (SocketException ex) {
            throw new RuntimeException("Проблемы с сокетом UDP." + ex.getMessage());
        }
        try {
            socket.send(packet);
        } catch (IOException io) {
            throw new RuntimeException("Ошибка отправки сообщения по UDP. " + io.getMessage());
        } finally {
            socket.close();
        }
    }

    /**
     * Послать сообщение по UDP широковещательно
     * @param message текст посылаемого сообщения
     * @param port порт получателя
     */
    public static void sendUDPBroadcast(String message, int port) {
        try {
            sendUDPMessage(message, InetAddress.getByName("255.255.255.255"), port);
        } catch (UnknownHostException ex) {
            throw new RuntimeException("Проблемы с адресом " + ex.getMessage());
        }
    }

    /**
     * Загрузка ресурса из jar-файла
     * @param o - класс, нужен для получения ресурса
     * @param resourceName путь к ресурсу в jar-файле
     * @return массив байт, содержащий ресурс
     * @throws IOException
     */
    public static byte[] readResource(Object o, String resourceName) throws IOException {
        // Выдаем ресурс  "/ru/apertum/qsystem/reports/web/name.jpg"
        final InputStream inStream = o.getClass().getResourceAsStream(resourceName);
        return readInputStream(inStream);
    }

   

    /**
     * Для чтения байт из потока. не применять для потока связанного с сокетом.
     * @param stream из него читаем
     * @return byte[] результат
     * @throws java.io.IOException
     * @see readSocketInputStream(InputStream stream)
     */
    public static byte[] readInputStream(InputStream stream) throws IOException {
        final byte[] result;
        final DataInputStream dis = new DataInputStream(stream);
        result = new byte[stream.available()];
        dis.readFully(result);
        return result;
    }

    /**
     * Округление до нескольких знаков после запятой.
     * @param value
     * @param scale 
     * @return Готовое обрезанное дробное число.
     */
    public static double roundAs(double value, int scale) {
        return new BigDecimal(value).setScale(scale, RoundingMode.UP).doubleValue();
    }

   

    static {
        /**
         * Инициализация
         */
        COEFF_WORD.put(SERVICE_REMAINS, "low");
        COEFF_WORD.put(SERVICE_NORMAL, "basic");
        COEFF_WORD.put(SERVICE_VIP, "vip");

        PRIORITYS_WORD.put(PRIORITY_LOW, "Низкий");
        PRIORITYS_WORD.put(PRIORITY_NORMAL, "Нормальный");
        PRIORITYS_WORD.put(PRIORITY_HI, "Повышенный");
        PRIORITYS_WORD.put(PRIORITY_VIP, "V.I.P");
    }

    

   
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.apertum.qsystem.prereg.core;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;
import javax.swing.tree.TreeNode;
import ru.apertum.qsystem.common.NetCommander;
import ru.apertum.qsystem.common.model.INetProperty;
import ru.apertum.qsystem.server.model.ATreeModel;
import ru.apertum.qsystem.server.model.ISailListener;
import ru.apertum.qsystem.server.model.QService;
import ru.apertum.qsystem.server.model.QServiceTree;

/**
 *
 * @author Evgeniy Egorov
 */
public class ServiceList {

    /**
     *
     */
    public static final INetProperty netProperty = new INetProperty() {
        @Override
        public Integer getPort() {
            return Integer.parseInt(System.getProperty("QSYSTEM_SERVER_PORT"));
        }

        @Override
        public InetAddress getAddress() {
            try {
                return InetAddress.getByName(System.getProperty("QSYSTEM_SERVER_ADDR"));
            } catch (UnknownHostException ex) {
                throw new RuntimeException("Wrong address of server: " + System.getProperty("QSYSTEM_SERVER_ADDR"));
            }
        }
    };

    private ServiceList() {
        //final QService service = new QService();//*/NetCommanderAPI.getServiсes(netProperty).getRoot();
        System.out.println("@@@@@@@@@@@@@@@@  " + netProperty.getAddress() + ":" + netProperty.getPort());
        QService service = null;
        try {
            service = NetCommander.getServiсes(netProperty).getRoot();
        } catch (Exception ex) {
            throw new RuntimeException("Bad net conversation: " + ex);
        }

        QServiceTree.sailToStorm(service, new ISailListener() {
            @Override
            public void actionPerformed(TreeNode service) {
                if (service.isLeaf() && ((QService) service).getStatus().intValue() == 1) {
                    list.add((QService) service);
                }
            }
        });

    }
    final LinkedList<QService> list = new LinkedList<>();

    public LinkedList<QService> getServiceList() {
        return list;
    }

    public static ServiceList getInstance() {
        return ServiceListHolder.INSTANCE;
    }

    private static class ServiceListHolder {

        private static final ServiceList INSTANCE = new ServiceList();
    }

    private static final class ServiceTreeModel extends ATreeModel<QService> {

        public static LinkedList<QService> list;

        public ServiceTreeModel() {
            super();
        }

        @Override
        protected LinkedList<QService> load() {
            return list;
        }
    }
}

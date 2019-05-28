package edu.ufp.inf.sd.rmi.dropbox.server;

import edu.ufp.inf.sd.rmi.dropbox.server.DropboxServerImpl;
import edu.ufp.inf.sd.rmi.dropbox.server.DropboxServerRI;
import edu.ufp.inf.sd.rmi.dropbox.server.DropboxServer;
import edu.ufp.inf.sd.rmi.util.rmisetup.SetupContextRMI;

import java.net.InetAddress;
import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DropboxServer {



    /*public static String serviceName = "rmi://localhost:1099/DropboxService";

    public DropboxServer(String hostIP) {
        System.out.println("CURRENT WORKING DIRECTORY:" + System.getProperty("user.dir"));
        try {
            // Create and install a security manager
            if (System.getSecurityManager() == null) {
                System.out.println("DropboxServer - Constructor(): set security manager");
                System.setSecurityManager(new SecurityManager());
            }
            // Get referencefor Registry
            InetAddress inetAddr = InetAddress.getLocalHost();
            //String serviceName = "rmi://localhost:1099/DropboxService";
            String hostName = inetAddr.getHostName();
            String hostAddress = inetAddr.getHostAddress();

            DropboxServer.serviceName = (hostIP == null ? "rmi://" + hostAddress + ":1099/DropboxService" : "rmi://" + hostIP + ":1099/DropboxService");
            System.out.println("DropboxServer - Constructor(): Local host is " + hostName + " at IP address " + hostAddress);
            System.out.println("DropboxServer - Constructor(): get registry on " + hostAddress + " - default port 1099");
            //Registry registry = LocateRegistry.getRegistry();
            Registry registry = LocateRegistry.getRegistry(inetAddr.getHostAddress(), 1099);
            if (registry != null) {

                String[] srvList = registry.list();
                System.out.println("DropboxServer - Constructor(): list of servervices svrList.length = " + srvList.length);

                for (int i = 0; i < srvList.length; i++) {
                    System.out.println("DropboxServer - Constructor(): service svrLis[" + i + "] = " + srvList[i]);
                }

                System.out.println("DropboxServer - Constructor(): try register service " + serviceName + "...");
                DropboxServerRI dbServerRI = (DropboxServerRI) new DropboxServerImpl();

                try {
                    registry.rebind(DropboxServer.serviceName, dbServerRI);
                    System.out.println("DropboxServer - Constructor(): service bound and running!");
                } catch (AccessException e) {
                    Logger.getLogger(DropboxServerImpl.class.getName()).log(Level.SEVERE, null, e);
                }

            } else {
                System.out.println("DropboxServer - Constructor(): create registry on port 1099");
                registry = LocateRegistry.createRegistry(1099);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DropboxServerImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; args != null && i < args.length; i++) {
            System.out.println("DropboxServer - main(): args[" + i + "] = " + args[i]);

        }
        DropboxServer hws = new DropboxServer((args != null && args.length > 0 ? args[0] : null));
    }*/


    private SetupContextRMI contextRMI;
    /**
     * Remote interface that will hold reference to the Servant impl
     */
    private DropboxServerRI DropboxRI;

    public static void main(String[] args) {
        if (args != null && args.length < 3) {
            System.err.println("usage: java [options] edu.ufp.sd.Dropbox.server.DropboxServer <rmi_registry_ip> <rmi_registry_port> <service_name>");
            System.exit(-1);
        } else {
            //1. ============ Create Servant ============
            DropboxServer hws = new DropboxServer(args);
            //2. ============ Rebind servant on rmiregistry ============
            hws.rebindService();
        }
    }

    /**
     * @param args
     */
    public DropboxServer(String args[]) {
        try {
            //============ List and Set args ============
            printArgs(args);
            String registryIP = args[0];
            String registryPort = args[1];
            String serviceName = args[2];

            //============ Create a context for RMI setup ============
            contextRMI = new SetupContextRMI(this.getClass(), registryIP, registryPort, new String[]{serviceName});
        } catch (RemoteException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }
    }

    private void rebindService() {
        try {
            //Get proxy to rmiregistry
            Registry registry = contextRMI.getRegistry();
            //Bind service on rmiregistry and wait for calls
            if (registry != null) {
                //============ Create Servant ============
                DropboxRI= new DropboxServerImpl();
              /*  DropboxRI.makeDir("PEDRO", "file1");
                DropboxRI.makeDir("PEDRO", "file2");
                DropboxRI.makeDir("PEDRO", "file3");
                DropboxRI.makeDir("PEDRO/file3", "file7");
                DropboxRI.makeDir("ANTONIO", "file4");
                DropboxRI.makeDir("ANTONIO", "file5");
                DropboxRI.makeDir("ANTONIO", "file6");*/
                // DropboxRI.removeDir("PEDRO/file3", "file7");
                // DropboxRI.removeDir("PEDRO", "file3");
                // DropboxRI.renameDir("PEDRO", "file1", "new");
                //DropboxRI.moveDir("PEDRO", "file2","ANTONIO", "file4");


                //Get service url (including servicename)
                String serviceUrl = contextRMI.getServicesUrl(0);
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "going to rebind service @ {0}", serviceUrl);

                //============ Rebind servant ============
                //Naming.bind(serviceUrl, DropboxRI);
                registry.rebind(serviceUrl, DropboxRI);
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "service bound and running. :)");
            } else {
                //System.out.println("DropboxServer - Constructor(): create registry on port 1099");
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "registry not bound (check IPs). :(");
                //registry = LocateRegistry.createRegistry(1099);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void printArgs(String args[]) {
        for (int i = 0; args != null && i < args.length; i++) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "args[{0}] = {1}", new Object[]{i, args[i]});
        }
    }
}
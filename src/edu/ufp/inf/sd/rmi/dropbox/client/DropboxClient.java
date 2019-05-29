package edu.ufp.inf.sd.rmi.dropbox.client;

import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.ufp.inf.sd.rmi.dropbox.server.*;

public class DropboxClient {

    //public static String serviceName = "rmi://localhost:1099/DropboxService";
    public DropboxClient(String[] args) {
        if (args == null) {
            System.out.println("DropboxClient - Constructor(): Args null");
            System.exit(0);
        }
        System.out.println("teste para o push");
        try {
            //Check args for receiving hostname
            String registryHostname = "localhost"; //May be an IP or hostname
            int registryPort = 1099; //Default port is 1099
            String serviceHostname = "localhost"; //May be an IP or hostname
            int servicePort = 1099; //Default port is 1099
            String serviceName = "rmi://localhost:1099/DropboxService";

            if (args.length < 2) {
                System.err.println("usage: java [options] edu.ufp.sd.dropbox.client.DropboxClient <server_rmi_hostname/ip> <server_rmi_port>");
                System.exit(1);
            } else {
                registryHostname = args[0];
                serviceHostname = args[0];
                servicePort = Integer.parseInt(args[1]);
                serviceName = "rmi://" + args[0] + ":1099/DropboxService";
            }
            // Create and install a security manager
            if (System.getSecurityManager() == null) {
                System.out.println("DropboxClient - Constructor(): set security manager");
                System.setSecurityManager(new SecurityManager());
            }

            //Get proxy to Registry service
            Registry registry = LocateRegistry.getRegistry(registryHostname, registryPort);
            if (registry == null) {
                System.out.println("DropboxClient - Constructor(): registry is null!!");
            } else {
                String[] rmiServersList = registry.list();
                System.out.println("DropboxClient - Constructor(): rmiServersList.length = " + rmiServersList.length);
                for (int i = 0; i < rmiServersList.length; i++) {
                    System.out.println("DropboxClient - Constructor(): rmiServersList[" + i + "] = " + rmiServersList[i]);
                }

                //String serviceName = "rmi://"+inetAddr.getHostAddress()+":1099/DropboxService";
                //String serviceName = System.getProperty("edu.ufp.sd.boulderdash.servicename");
                System.out.println("DropboxClient - Constructor(): going to lookup service " + serviceName + "...");

                //Get proxy to Dropbox service
                DropboxServerRI bdsRI = (DropboxServerRI) registry.lookup(serviceName);
                DropboxClientImpl dbImpl = new DropboxClientImpl(bdsRI);

                /**
                 * Registo
                 * */
                
                /*
                dbImpl.triggeredRegister("pedro", "1236");
                dbImpl.triggeredRegister("antonio", "11111");
                dbImpl.triggeredLogin("pedro", "1236");
                dbImpl.triggeredAddGroupName("PEDRO", "pedro");
                */
                
                //dbImpl.triggeredLogin("antonio", "22222");
                //dbImpl.triggeredLogin("pedro", "1234");
                //dbImpl.triggeredLogin("antonio", "11111");

                //Call DropboxServer remote service
                System.out.println("DropboxClient - Constructor(): after calling service " + serviceName + "...");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(DropboxClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(DropboxClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; args != null && i < args.length; i++) {
            System.out.println("DropboxClient - main(): args[" + i + "] = " + args[i]);
        }

        DropboxClient db = new DropboxClient((args != null && args.length > 0 ? args : null));
    }

}

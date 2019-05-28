package edu.ufp.inf.sd.rmi.dropbox.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DropboxClientRI extends Remote {
    public String getClientUsername() throws RemoteException;

}

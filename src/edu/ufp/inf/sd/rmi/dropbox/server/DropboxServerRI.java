package edu.ufp.inf.sd.rmi.dropbox.server;

import edu.ufp.inf.sd.rmi.dropbox.client.DropboxClientImpl;
import edu.ufp.inf.sd.rmi.dropbox.client.DropboxClientRI;
import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface DropboxServerRI extends Remote {

    public int register(DropboxClientRI client, String username, String password) throws RemoteException;

    public void makeDir(String groupName, String dir) throws RemoteException;
    public void removeDir(String groupName, String dir) throws RemoteException;
    public void renameDir(String groupName, String oldDir, String newName) throws RemoteException;
    public void moveDir(String groupName, String dir,String groupNameDestino, String newDir) throws RemoteException;
    public File[] listDir(String groupName, String dir) throws RemoteException;
    public void upload(String groupName, String dir, String arq) throws RemoteException;
    public int login(DropboxClientRI client, String username, String password)throws RemoteException;
    public int addGroupName(DropboxClientRI client, String username, String groupName)throws RemoteException;
    public int joinGroup(DropboxClientRI client, String username, String groupName) throws RemoteException;
public int unjoinGroup( String username, String groupName) throws RemoteException;   
    /* public int logout(String username) throws RemoteException;
    public void attachUserInGroup(DropboxClientRI client, String groupName) throws RemoteException;
    public void detachUserOfGroup(DropboxClientRI client, String groupName) throws RemoteException;

*/


}

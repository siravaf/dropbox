package edu.ufp.inf.sd.rmi.dropbox.client;

import edu.ufp.inf.sd.rmi.dropbox.server.DropboxServerImpl;
import edu.ufp.inf.sd.rmi.dropbox.server.DropboxServerRI;
import edu.ufp.inf.sd.rmi.dropbox.server.State;
import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DropboxClientImpl implements DropboxClientRI {

    
    private Object lastState;

    private DropboxServerRI dbserverRI;
    private DropboxClientUserGUI dbcImplLoginUI = new DropboxClientUserGUI(this);

    private DropboxClientGroupGUI dropboxClientgui;
    public static String PATH = "/Projects/Dropbox/data/DropboxOperations/";

    private String username;
    private String password;
    private String groupName;
    private boolean loggedin;

    public DropboxClientImpl(DropboxServerRI dbserverRI) throws RemoteException {
        exportObjectMethod();
        this.dbserverRI = dbserverRI;
    }

    private void exportObjectMethod() {
        try {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            System.out.println("DropboxClientImpl: " + e.getMessage());
        }
    }

    public void triggeredRegister(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
        System.out.println("---triggeredRegister---");

        try {
            int register = getDbserverRI().register(this, username, password);

            System.out.println("int register " + register);
            if (register != 0) {
                System.out.println("You are now registed, please login!");
            }
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void triggerJoinGroup(String username, String groupName) throws RemoteException {
        System.out.println("Client -> groupName " + groupName);
        int joinGroupName = getDbserverRI().joinGroup(this, username, groupName);
        if (joinGroupName == 0) {
            System.out.println("já está junto");
        } else {
            System.out.println("junto com sucesso");
        }
    }

    public void triggeredAddGroupName(String groupName, String username) {
        this.setUsername(username);
        this.setGroupName(groupName);
        try {

            int registerGroupName = getDbserverRI().addGroupName(this, username, groupName);
            if (registerGroupName == 0) {
                System.out.println("ERRO! Já existe esse group name");
            }
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void triggeredLogin(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
        try {
            int login = getDbserverRI().login(this, username, password);

            System.out.println("int login " + login);
            if (login != 0) {
                System.out.println("You are now logged in!");
                this.setLoggedin(true);
                dbcImplLoginUI.setVisible(false);
                dropboxClientgui = new DropboxClientGroupGUI(this);
            }
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getClientUsername() throws RemoteException {
        return this.username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }

    /**
     * @return the dbserverRI
     */
    public DropboxServerRI getDbserverRI() {
        return dbserverRI;
    }

    /**
     * @param dbserverRI the dbserverRI to set
     */
    public void setDbserverRI(DropboxServerRI dbserverRI) {
        this.dbserverRI = dbserverRI;
    }

    @Override
    public void update() throws RemoteException {
        this.lastState = this.dbserverRI.getState();
        

        if (lastState instanceof State.NewGroup) {
            System.out.println("MinesweperClientImpl - update(): State = NewRoom ");
            State.NewGroup nr = (State.NewGroup) lastState;
            if (nr.isRemoveAll()) {
               // dropboxClientgui.removeAllGroup();
            } else {
                dropboxClientgui.addNewGroup(nr);
            }
        }
        System.out.println("DropboxClientImpl - update():");
        System.out.println("A atualizar Group Name");

        
    }
    
}

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
    public static String PATH = "/Projects/dropbox/data/DropboxOperations/";

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

            }
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
    }

    public int triggeredOpenGroup(String dir, String username) throws RemoteException {
        this.setUsername(username);
        int openGroup = getDbserverRI().openGroup(this, dir, username);
        return openGroup;
    }

    public void triggeredLogout(String username) {
        System.out.println("triggeredLogout");
        try {
            dbserverRI.logout(username);
            this.dbcImplLoginUI.setVisible(true);
        } catch (RemoteException e) {
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

    public Object getLastState() {
        return lastState;
    }

    public void setLastState(Object lastState) {
        this.lastState = lastState;
    }

    @Override
    public void update() throws RemoteException {
        this.lastState = this.dbserverRI.getState();

        System.out.println("------- this.lastState ----- " + this.lastState.toString());

        if (lastState instanceof State.NewGroup) {

            System.out.println("DropboxClientImpl - update(): State = NewGroup ");
            State.NewGroup nr = (State.NewGroup) lastState;
            dropboxClientgui.addNewGroup(nr);
            dropboxClientgui.updateAllGroups();

            /* if (nr.isRemoveAll()) {
                dropboxClientgui.removeAllGroups();
            } else {*/
            //}
        }
        if (lastState instanceof State.NewDir) {

            System.out.println("DropboxClientImpl - update(): State = NewDIR ");
            State.NewDir nr = (State.NewDir) lastState;

            if (nr.isRemoveAll()) {
                System.out.println("-----is ermoval ");
                dropboxClientgui.getFolderGUI().removeAllDir();
                dropboxClientgui.getFolderGUI().updateAllDirs();
            } else {
                dropboxClientgui.getFolderGUI().addNewDir(nr);
                dropboxClientgui.getFolderGUI().updateAllDirs();

            }
        }

    }

}

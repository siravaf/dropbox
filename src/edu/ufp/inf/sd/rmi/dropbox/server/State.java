package edu.ufp.inf.sd.rmi.dropbox.server;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class State implements Serializable {

    public class ConnectedClients implements Serializable {

        private int clients;

        public ConnectedClients(int clients) {
            this.clients = clients;
        }

        public int getClients() {
            return clients;
        }

        public void setClients(int clients) {
            this.clients = clients;
        }

    }

    public class Disconnect implements Serializable {

        private int type;
        private String message;

        public Disconnect(int type, String message) {
            this.type = type;
            this.message = message;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public class NewGroup implements Serializable {

        private boolean removeAll;
        private String groupName;

        public NewGroup(boolean removeAll, String groupName) {
            this.removeAll = removeAll;
            this.groupName = groupName;
        }

        public String getGroupName() {
            return groupName;
        }

        public boolean isRemoveAll() {
            return removeAll;
        }
    }

    public class NewDir implements Serializable {

        private boolean removeAll;
        private String dirName;

        public NewDir(boolean removeAll, String dirName) {
            this.removeAll = removeAll;
            this.dirName = dirName;
        }

        public String getDirName() {
            return dirName;
        }

        public boolean isRemoveAll() {
            return removeAll;
        }
    }

    public class GenericState implements Serializable {

        private String type;

        public GenericState(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }
    }
}

package edu.ufp.inf.sd.rmi.dropbox.server;

import edu.ufp.inf.sd.rmi.dropbox.client.DropboxClientImpl;
import edu.ufp.inf.sd.rmi.dropbox.client.DropboxClientRI;
import edu.ufp.inf.sd.rmi.dropbox.models.SessionManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.io.File;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.Scanner;

public class DropboxServerImpl extends UnicastRemoteObject implements DropboxServerRI {

    protected ArrayList<DropboxClientRI> clients = new ArrayList<>();
    protected ArrayList<String> groupNameArray = new ArrayList<>();
    protected ArrayList<String> dirArray = new ArrayList<>();

    private Object state;

    public static String PATH_USERS = "/Projects/dropbox/data/users/";
    public static String PATH_GROUP = "/Projects/dropbox/data/groups/";
    public static String PATH = "/Projects/dropbox/data/DropboxOperations/";

    protected DropboxServerImpl() throws RemoteException {
        super();
        String home = System.getProperty("user.home");
        home = home + PATH;
        File file = new File(home);

        File afile[] = file.listFiles();
        int i = 0;
        for (int j = afile.length; i < j; i++) {
            File arquivos = afile[i];
            groupNameArray.add(arquivos.getName());
        }
    }

    @Override
    public int register(DropboxClientRI client, String username, String password) {
        String home = System.getProperty("user.home");
        String user_path = home + PATH_USERS + username + ".txt";

        File f = new File(user_path);
        BufferedWriter bw = null;
        if (f.exists()) {
            return 0;
        }
        try {
            bw = new BufferedWriter(new FileWriter(new File(user_path)));
            bw.write(password);
        } catch (IOException e) {
            Logger.getLogger(DropboxServerImpl.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                Logger.getLogger(DropboxServerImpl.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return 1;
    }

    @Override
    public int logout(String username) throws RemoteException {
        for (DropboxClientRI user : this.clients) {
            if (user.getClientUsername().compareTo(username) == 0) {
                clients.remove(user);
                return 1;
            }
        }
        return 0;
    }

    /**
     * System.out.println("MinesweperServerImpl - logout(): " + username); for
     * (MinesweperClientRI user : this.clients) { if
     * (user.getClientUsername().compareTo(username) == 0) {
     * clients.remove(user); return 1; } } return 0;
     */
    @Override

    public int login(DropboxClientRI client, String username, String password) throws RemoteException {

        if (clientAlreadyLoggedin(username)) {
            System.out.println("DropboxServerImpl - login(): " + username + " is already logged in.");
            return 0;
        }
        String home = System.getProperty("user.home");

        String user_path = home + PATH_USERS + username + ".txt";
        System.out.println("DropboxServerImpl - var userpath: " + user_path);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(user_path)));
            String file_password = br.readLine();
            if (file_password.compareTo(password) == 0) {
                System.out.println("DropboxServerImpl - login(): " + username + " is now logged in.");
                clients.add(client);

                return 1;
            } else {
                System.out.println("DropboxServerImpl - login(): " + username + " has failed the password.");
                return 0;
            }

        } catch (FileNotFoundException ex) {
            return 0;
        } catch (IOException ex) {
            return 0;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(DropboxServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public int joinGroup(DropboxClientRI client, String username, String groupName) throws RemoteException {
        String home = System.getProperty("user.home");
        String groupName_path = home + PATH_GROUP + groupName + ".txt";
        File file = new File(groupName_path);
        BufferedWriter bw = null;

        if (file.exists()) {
            try {

                boolean exist = false;
                BufferedReader reader;
                reader = new BufferedReader(new FileReader(groupName_path));

                String line;
                while ((line = reader.readLine()) != null) {
                    if (null != line && line.equals(username)) {
                        exist = true;
                        break;
                    } else {
                        exist = false;
                    }
                }
                if (exist == false) {
                    Writer output;
                    output = new BufferedWriter(new FileWriter(file, true));  //clears file every time
                    output.append("\n");
                    output.append(username);
                    output.close();
                }
                reader.close();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(DropboxServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DropboxServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    @Override
    public int unjoinGroup(String username, String groupName) throws RemoteException {
        String home = System.getProperty("user.home");
        String groupName_path = home + PATH_GROUP + groupName + ".txt";
        File inputFile = new File(groupName_path);
        File tempFile = new File(home + PATH_GROUP + "temp.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(groupName_path));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;

            while ((line = reader.readLine()) != null) {
                if (null != line && !line.equalsIgnoreCase(username)) {
                    writer.write(line + System.getProperty("line.separator"));
                }
            }
            writer.close();
            reader.close();
            boolean successful;
            successful = tempFile.renameTo(inputFile);

        } catch (IOException ex) {
            Logger.getLogger(DropboxServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    @Override
    public int addGroupName(DropboxClientRI client, String username, String groupName) throws RemoteException {
        String home = System.getProperty("user.home");

        String groupName_path = home + PATH_GROUP + groupName + ".txt";
        File file = new File(groupName_path);
        BufferedWriter bw = null;
        if (file.exists()) {
            return 0;
        }

        try {

            bw = new BufferedWriter(new FileWriter(new File(groupName_path)));
            bw.write(username);

            /**
             * Confirmar se já existe se existir então avisa senao cria a pasta
             * e o ficheiro de acesso
             *
             */
            groupNameArray.add(groupName);
            this.makeDir(groupName, "");

            this.setState(new State().new NewGroup(false, groupName));

        } catch (IOException e) {
            Logger.getLogger(DropboxServerImpl.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                Logger.getLogger(DropboxServerImpl.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return 1;

    }

    private boolean clientAlreadyLoggedin(String username) {
        System.out.println("clientAlreadyLoggedin: " + username);
        try {
            for (DropboxClientRI client : clients) {
                if (client.getClientUsername().compareTo(username) == 0) {
                    return true;
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(DropboxServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void makeDir(String groupName, String dir) throws RemoteException {
        try {

            String home = System.getProperty("user.home");
            home = home + PATH + groupName + "/";

            boolean teste = new File(home, dir).mkdirs();
            if (dir != null) {
                this.dirArray.add(dir);
                this.setState(new State().new NewDir(false, dir));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar o diretorio");
            System.out.println(ex);
        }
    }

    @Override
    public void removeDir(String groupName, String dir) throws RemoteException {
        String home = System.getProperty("user.home");
        home = home + PATH + groupName + "/" + dir;
        File f = new File(home);
        System.out.println(home);
        boolean t = false;
        if ((f.exists()) && (f.isDirectory())) {
            System.out.println("Existe por isso vamos lá apaga-lo");
            t = f.delete();
        }
        System.out.println(t);
    }

    @Override
    public void renameDir(String groupName, String oldDir, String newName) throws RemoteException {
        String home = System.getProperty("user.home");
        String oldHome = home + PATH + groupName + "/" + oldDir;
        String newHome = home + PATH + groupName + "/" + newName;
        File oldfile = new File(oldHome);
        if (!oldfile.exists()) {
            System.out.println("File or directory does not exist.");
            System.exit(0);
        }
        File newfile = new File(newHome);
        boolean Rename = oldfile.renameTo(newfile);
        if (!Rename) {
            System.out.println("File or directory does not rename successfully.");
            System.exit(0);
        } else {
            System.out.println("File or directory rename is successfully.");
        }
    }

    @Override
    public File[] listDir(String groupName, String dir) throws RemoteException {
        String home = System.getProperty("user.home");

        if (groupName.equals("DropboxOperations")) {
            home = home + PATH;
        } else {
            home = home + PATH + groupName + "/" + dir + "/";
        }

        File file = new File(home);
        File afile[] = file.listFiles();
        int i = 0;
        for (int j = afile.length; i < j; i++) {
            File arquivos = afile[i];
            //System.out.println(afile[i].getName());
        }
        return afile;
    }

    @Override
    public void upload(String groupName, String dir, String arq) throws RemoteException {
        String home = System.getProperty("user.home");
        home = home + PATH + groupName + "/" + dir + "/" + arq + ".pdf";
        File novoArquivo = new File(home);
    }

    @Override
    public void moveDir(String groupName, String dir, String groupNameDestino, String newDir) throws RemoteException {
        String home = System.getProperty("user.home");
        home = home + PATH + groupName + "/" + dir;

        String destino = System.getProperty("user.home");
        destino = destino + PATH + groupNameDestino + "/" + newDir;
        File arquivo = new File(home);

        if (!arquivo.exists()) {
            System.out.println("Arquivo não encontrado");
        } else {
            File diretorioDestino = new File(destino);
            boolean sucesso = arquivo.renameTo(new File(diretorioDestino, arquivo.getName()));

            if (sucesso) {
                System.out.println("Arquivo movido para '" + diretorioDestino.getAbsolutePath() + "'");
            } else {
                System.out.println("Erro ao mover arquivo '" + arquivo.getAbsolutePath() + "' para '"
                        + diretorioDestino.getAbsolutePath() + "'");
            }
        }
    }

    //ver conflito
    public void notifyAllObservers() throws RemoteException {
        System.out.println("DropboxServerImpl - notifyAllObservers()");
        for (DropboxClientRI client : clients) {
            client.update();
        }
    }

    /**
     * @return the state
     */
    @Override
    public Object getState() {
        return state;
    }

    /**
     * @param state the state to set
     * @throws java.rmi.RemoteException
     */
    @Override
    public void setState(Object state) throws RemoteException {
        this.state = state;
        
        System.out.println("DropboxServerImpl - setState(): " + state.getClass().getName());

        for (DropboxClientRI client : clients) {
            System.out.println(client.getClientUsername());

            if (!clients.isEmpty()) {
                notifyAllObservers();
            }
        }
    }

    @Override
    public String[] fetchAvaliableGroups() throws RemoteException {

        String[] itemsArr = new String[groupNameArray.size()];
        if (!groupNameArray.isEmpty()) {

            for (int i = 0; i < groupNameArray.size(); i++) {
                itemsArr[i] = groupNameArray.get(i);
            }
        }

        return itemsArr;
    }

    @Override
    public String[] fetchAvaliableDir() throws RemoteException {

        String[] itemsArr = new String[dirArray.size()];
        if (!dirArray.isEmpty()) {

            for (int i = 0; i < dirArray.size(); i++) {
                itemsArr[i] = dirArray.get(i);
            }
        }

        return itemsArr;
    }

}

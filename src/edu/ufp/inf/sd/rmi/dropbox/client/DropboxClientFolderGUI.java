/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.sd.rmi.dropbox.client;

import edu.ufp.inf.sd.rmi.dropbox.server.State;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author antonioferreira
 */
public class DropboxClientFolderGUI extends javax.swing.JFrame implements WindowListener {

    private final DropboxClientImpl dbclientImpl;
    private final DropboxClientGroupGUI dbgui;
    private DefaultListModel<String> dirList = new DefaultListModel<>();
    String groupName;

    /**
     * Creates new form DropboxClientFolderGUI
     */
    /**
     * Creates new form DropboxClientFolderGUI
     *
     * @param dbclientImpl
     */
    public DropboxClientFolderGUI(DropboxClientImpl dbclientImpl, String groupName, DropboxClientGroupGUI dbgui) throws RemoteException {
        this.dbclientImpl = dbclientImpl;
        this.groupName = groupName;
        this.dbgui = dbgui;
        initComponents();
        this.setVisible(true);
        this.jLabelNameGroup.setText(groupName);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(this);
        File[] afile = dbclientImpl.getDbserverRI().listDir(groupName, "");

        //  JOptionPane.showConfirmDialog(this.dbgui, afile);
        DefaultListModel listModel = new DefaultListModel();
        int i = 0;
        for (int j = afile.length; i < j; i++) {
            listModel.addElement(afile[i].getName());
        }

        jListGroupFolders.setModel(listModel);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextNewFolder = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButtonCreateFolder = new javax.swing.JButton();
        jButtonMoveFolder = new javax.swing.JButton();
        jButtonRenameFolder = new javax.swing.JButton();
        jButtonDeleteFolder = new javax.swing.JButton();
        jButtonExitFolder = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListGroupFolders = new javax.swing.JList<>();
        jButtonExitGroup = new javax.swing.JButton();
        jButtonOpen = new javax.swing.JButton();
        jLabelNameGroup = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextNewFolder.setText("Name Folder");
        jTextNewFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNewFolderActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonCreateFolder.setText("Create");
        jButtonCreateFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateFolderActionPerformed(evt);
            }
        });

        jButtonMoveFolder.setText("Move to");
        jButtonMoveFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMoveFolderActionPerformed(evt);
            }
        });

        jButtonRenameFolder.setText("Rename");
        jButtonRenameFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRenameFolderActionPerformed(evt);
            }
        });

        jButtonDeleteFolder.setText("Delete dir");
        jButtonDeleteFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteFolderActionPerformed(evt);
            }
        });

        jButtonExitFolder.setText("Back dir");
        jButtonExitFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitFolderActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jListGroupFolders);

        jButtonExitGroup.setText("Exit Group");
        jButtonExitGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitGroupActionPerformed(evt);
            }
        });

        jButtonOpen.setText("Open dir");
        jButtonOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(jLabelNameGroup)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButtonMoveFolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jButtonExitGroup, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButtonOpen)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonExitFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonDeleteFolder))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextNewFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCreateFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonRenameFolder)
                                .addGap(0, 162, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabelNameGroup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextNewFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCreateFolder)
                    .addComponent(jButtonRenameFolder))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonMoveFolder))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonOpen)
                    .addComponent(jButtonExitFolder)
                    .addComponent(jButtonDeleteFolder))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonExitGroup)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextNewFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNewFolderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNewFolderActionPerformed

    private void jButtonCreateFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateFolderActionPerformed
        try {

            File[] afile;

            dbclientImpl.getDbserverRI().makeDir(groupName, jTextNewFolder.getText());

            DefaultListModel listModel = new DefaultListModel();
            int i = 0;
            afile = dbclientImpl.getDbserverRI().listDir(groupName, "");
            for (int j = afile.length; i < j; i++) {
                listModel.addElement(afile[i].getName());
            }
            jListGroupFolders.setModel(listModel);
        } catch (RemoteException ex) {
            Logger.getLogger(DropboxClientFolderGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonCreateFolderActionPerformed

    private void jButtonExitGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitGroupActionPerformed
        setVisible(false);
        dbgui.setVisible(true);


    }//GEN-LAST:event_jButtonExitGroupActionPerformed

    private void jButtonRenameFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRenameFolderActionPerformed
        try {
            dbclientImpl.getDbserverRI().renameDir(groupName, jListGroupFolders.getSelectedValue(), jTextNewFolder.getText());

            File[] afile;
            DefaultListModel listModel = new DefaultListModel();
            int i = 0;
            afile = dbclientImpl.getDbserverRI().listDir(groupName, "");
            for (int j = afile.length; i < j; i++) {
                listModel.addElement(afile[i].getName());
            }
            jListGroupFolders.setModel(listModel);
        } catch (RemoteException ex) {
            Logger.getLogger(DropboxClientFolderGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonRenameFolderActionPerformed

    private void jButtonMoveFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMoveFolderActionPerformed

    }//GEN-LAST:event_jButtonMoveFolderActionPerformed

    private void jButtonDeleteFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteFolderActionPerformed
        try {
            dbclientImpl.getDbserverRI().removeDir(groupName, jListGroupFolders.getSelectedValue());
            File[] afile;
            DefaultListModel listModel = new DefaultListModel();
            int i = 0;
            afile = dbclientImpl.getDbserverRI().listDir(groupName, "");
            for (int j = afile.length; i < j; i++) {
                listModel.addElement(afile[i].getName());
            }
            jListGroupFolders.setModel(listModel);

        } catch (RemoteException ex) {
            Logger.getLogger(DropboxClientFolderGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonDeleteFolderActionPerformed

    private void jButtonOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpenActionPerformed
        try {
            File[] afile;
            DefaultListModel listModel = new DefaultListModel();
            int i = 0;

            afile = dbclientImpl.getDbserverRI().listDir(groupName, jListGroupFolders.getSelectedValue());
            groupName = groupName + "/" + jListGroupFolders.getSelectedValue();
            for (int j = afile.length; i < j; i++) {
                listModel.addElement(afile[i].getName());
            }
            jListGroupFolders.setModel(listModel);

        } catch (RemoteException ex) {
            Logger.getLogger(DropboxClientFolderGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonOpenActionPerformed

    private void jButtonExitFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitFolderActionPerformed

        String[] array = groupName.split("/");

        groupName = array[0];

        File[] afile;
        DefaultListModel listModel = new DefaultListModel();
        int i = 0;

        try {
            afile = dbclientImpl.getDbserverRI().listDir(groupName, "");
            for (int j = afile.length; i < j; i++) {
                listModel.addElement(afile[i].getName());
            }
            jListGroupFolders.setModel(listModel);
        } catch (RemoteException ex) {
            Logger.getLogger(DropboxClientFolderGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonExitFolderActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCreateFolder;
    private javax.swing.JButton jButtonDeleteFolder;
    private javax.swing.JButton jButtonExitFolder;
    private javax.swing.JButton jButtonExitGroup;
    private javax.swing.JButton jButtonMoveFolder;
    private javax.swing.JButton jButtonOpen;
    private javax.swing.JButton jButtonRenameFolder;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabelNameGroup;
    private javax.swing.JList<String> jListGroupFolders;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextNewFolder;
    // End of variables declaration//GEN-END:variables

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    public void addNewDir(State.NewDir nr) {
        this.dirList.addElement(nr.getDirName());

    }

    public void removeAllDir() {
        this.dirList.removeAllElements();
    }

    public void updateAllDirs() {
        System.out.println("updateAllDirs()");
        this.removeAllDir();
        try {
            String[] dirs = this.dbclientImpl.getDbserverRI().fetchAvaliableDir();
            if (dirs.length != 0) {

                for (int i = 0; i < dirs.length; i++) {
                    this.dirList.addElement(dirs[i]);
                }
                this.jListGroupFolders.setModel(this.dirList);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(DropboxClientGroupGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

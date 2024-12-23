/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forme;

import domen.Administrator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import konstante.Konstante;
import modeli.ModelTabeleUlogovani;
import niti.ServerskaNit;

/**
 *
 * @author Mihajlo
 */
public class ServerskaForma extends javax.swing.JFrame {

    /**
     * Creates new form ServerskaForma
     */
    ServerskaNit sn;
    int maxBrojAdministratora = 0;
    ModelTabeleUlogovani mtu;
    Thread nitTabele;
    public ServerskaForma() {
        try{
            initComponents();
            setLocationRelativeTo(null);
            Properties parametriServera = new Properties();
            FileInputStream fis = new FileInputStream(Konstante.LOKACIJA_PARAMETARA_SERVERA);
            parametriServera.load(fis);
            maxBrojAdministratora = Integer.parseInt(parametriServera.getProperty(Konstante.MAX_BROJ_ADMINISTRATORA));
            System.out.println("usao");
            panelAdministratori.setVisible(false);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPokreniServer = new javax.swing.JButton();
        btnZaustaviServer = new javax.swing.JButton();
        panelAdministratori = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUlogovani = new javax.swing.JTable();
        btnIzlogujAdministratora = new javax.swing.JButton();
        btnIzlogujSve = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnPokreniServer.setText("Pokreni Server");
        btnPokreniServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPokreniServerActionPerformed(evt);
            }
        });

        btnZaustaviServer.setText("Zaustavi Server");
        btnZaustaviServer.setEnabled(false);
        btnZaustaviServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZaustaviServerActionPerformed(evt);
            }
        });

        tblUlogovani.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblUlogovani);

        btnIzlogujAdministratora.setText("Odjavi administratora");
        btnIzlogujAdministratora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzlogujAdministratoraActionPerformed(evt);
            }
        });

        btnIzlogujSve.setText("Odjavi sve");
        btnIzlogujSve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzlogujSveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAdministratoriLayout = new javax.swing.GroupLayout(panelAdministratori);
        panelAdministratori.setLayout(panelAdministratoriLayout);
        panelAdministratoriLayout.setHorizontalGroup(
            panelAdministratoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
            .addGroup(panelAdministratoriLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnIzlogujAdministratora)
                .addGap(18, 18, 18)
                .addComponent(btnIzlogujSve))
        );
        panelAdministratoriLayout.setVerticalGroup(
            panelAdministratoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAdministratoriLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAdministratoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIzlogujAdministratora, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIzlogujSve, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelAdministratori, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPokreniServer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
                        .addComponent(btnZaustaviServer)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPokreniServer)
                    .addComponent(btnZaustaviServer))
                .addGap(7, 7, 7)
                .addComponent(panelAdministratori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPokreniServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPokreniServerActionPerformed
        try {    
            sn = new ServerskaNit(maxBrojAdministratora);
            sn.start();
            mtu = new ModelTabeleUlogovani(sn);
            nitTabele = new Thread(mtu);
            nitTabele.start();
            tblUlogovani.setModel(mtu);
            panelAdministratori.setVisible(true);
            System.out.println("Server je pokrenut.");
            btnPokreniServer.setEnabled(false);
            btnZaustaviServer.setEnabled(true);
            //btnIzlogujAdministratora.setEnabled(true);
            //btnIzlogujSve.setEnabled(true);
            btnIzlogujSve.setVisible(false);
            btnIzlogujAdministratora.setVisible(false);
        } catch (Exception ex) {
            Logger.getLogger(ServerskaForma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPokreniServerActionPerformed

    private void btnZaustaviServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZaustaviServerActionPerformed
            if (sn != null) {
            sn.zaustaviServer();
            btnZaustaviServer.setEnabled(false);
            btnPokreniServer.setEnabled(true);
            panelAdministratori.setVisible(false);
            //btnIzlogujAdministratora.setEnabled(false);
            //btnIzlogujSve.setEnabled(false);
        }
    }//GEN-LAST:event_btnZaustaviServerActionPerformed

    private void btnIzlogujAdministratoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzlogujAdministratoraActionPerformed
        int red = tblUlogovani.getSelectedRow();
        if(red>-1){
            try {
                Administrator administrator = mtu.vratiIzabranogAdministratora(red);
                boolean uspesnoIzlogovan = sn.izlogujAdministratora(administrator);
                if(uspesnoIzlogovan){
                    JOptionPane.showMessageDialog(rootPane, "Uspesno ste odjavili administratora: " + administrator);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Odaberite administratora kog zelite da odjavite.");
        }
    }//GEN-LAST:event_btnIzlogujAdministratoraActionPerformed

    private void btnIzlogujSveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzlogujSveActionPerformed
        sn.izlogujSveAdministratore();
        JOptionPane.showMessageDialog(rootPane, "Svi administratori su odjavljeni.");

    }//GEN-LAST:event_btnIzlogujSveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerskaForma().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIzlogujAdministratora;
    private javax.swing.JButton btnIzlogujSve;
    private javax.swing.JButton btnPokreniServer;
    private javax.swing.JButton btnZaustaviServer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelAdministratori;
    private javax.swing.JTable tblUlogovani;
    // End of variables declaration//GEN-END:variables
}
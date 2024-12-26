/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.Administrator;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;
import kontroler.ServerKontroler;
import niti.ServerskaNit;

/**
 *
 * @author Mihajlo
 */
public class ModelTabeleUlogovani extends AbstractTableModel implements Runnable{

    private LinkedList<Administrator> listaUlogovanih;
    private LinkedList<Administrator> lista;
    private ServerskaNit serverNit;
    private String[] kolone = new String[]{"Email", "Status"};

    public ModelTabeleUlogovani(ServerskaNit serverNit) throws Exception {
        this.serverNit = serverNit;
        this.listaUlogovanih = serverNit.vratiUlogovane();
        this.lista = ServerKontroler.getInstanca().vratiSveAdministratratore();
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Administrator administrator = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return administrator.getEmail();
            case 1:
                if(listaUlogovanih.contains(administrator)){
                    return "Prijavljen";
                }
                return "Odjavljen";
            default:
                return "";
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(100);
                LinkedList<Administrator> tempKorisnici = serverNit.vratiUlogovane();
                
                boolean moraRefresh = false;
                if(listaUlogovanih.size() != tempKorisnici.size()){
                    listaUlogovanih = tempKorisnici;
                    moraRefresh = true;
                }else{
                    for(int i = 0; i<listaUlogovanih.size(); i++){
                        Administrator k1 = listaUlogovanih.get(i);
                        Administrator k2 = tempKorisnici.get(i);
                        
                        if(!k1.equals(k2)){
                            listaUlogovanih = tempKorisnici;
                            moraRefresh = true;
                        }
                    }
                }
                
                if(moraRefresh){
                    fireTableDataChanged();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public Administrator vratiIzabranogAdministratora(int red) {
        return lista.get(red);
    }
    
    
}

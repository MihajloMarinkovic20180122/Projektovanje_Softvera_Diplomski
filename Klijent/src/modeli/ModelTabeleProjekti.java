/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.Projekat;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mihajlo
 */
public class ModelTabeleProjekti extends AbstractTableModel {

    LinkedList<Projekat> listaProjekata = new LinkedList<>();
    String[] kolone = {"Naziv","Pocetak Realizacije","Rukovodilac","Prioritet", "Stanje"};

    public void setListaProjekata(LinkedList<Projekat> listaProjekata) {
        this.listaProjekata = listaProjekata;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return listaProjekata.size();
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
        Projekat projekat = listaProjekata.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return projekat.getNazivProjekta();
            case 1:
                return projekat.getPocetakRealizacije();
            case 2:
                return projekat.getRukovodilac();
            case 3:
                return projekat.getPrioritet();
            case 4:
                return projekat.getStanje();
            default:
                return "";
        }
    }

    public Projekat vratiOdabraniProjekat(int red) {
        return listaProjekata.get(red);
    }
    
    public LinkedList<Projekat> vratiListu() {
        return listaProjekata;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.Angazovanje;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mihajlo
 */
public class ModelTabeleAngazovanje extends AbstractTableModel{

    LinkedList<Angazovanje> listaAngazovanja = new LinkedList<>();
    String[] kolone = {"Zaposleni","Projekat","Pocetak Angazovanja","Kraj Angazovanja"};
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
    private String odabirJezika = lokalizacija.JezikMenadzer.getOdabir();

    public void setListaAngazovanje(LinkedList<Angazovanje> listaAngazovanje) {
        this.listaAngazovanja = listaAngazovanje;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return listaAngazovanja.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        switch (odabirJezika) {
            case "srpski - latinica":
                kolone = new String[]{"Zaposleni","Projekat","Pocetak Angazovanja","Kraj Angazovanja"};
                break;
            case "srpski - cirilica":
                kolone = new String[]{"Запослени","Пројекат","Почетак Ангажовања","Крај Ангажовања"};
                break;
            case "english":
                kolone = new String[]{"Employee","Project","Start Date","End Date"};
                break;
            default:
                throw new AssertionError();
        }
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Angazovanje angazovanje = listaAngazovanja.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return angazovanje.getZaposleni();
            case 1:
                return angazovanje.getProjekat();
            case 2:
                if (angazovanje.getPocetakAngazovanja() != null) {
                    return sdf.format(angazovanje.getPocetakAngazovanja());
                }
            case 3:
                if (angazovanje.getKrajAngazovanja() != null) {
                    return sdf.format(angazovanje.getKrajAngazovanja());
                }
            default:
                return "";
        }
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.Zaposleni;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mihajlo
 */
public class ModelTabeleZaposleni extends AbstractTableModel{

    LinkedList<Zaposleni> listaZaposlenih = new LinkedList<>();
    String[] kolone = {"Ime","Prezime","Email", "Radno Mesto"};

    public void setListaZaposlenih(LinkedList<Zaposleni> listaZaposlenih) {
        this.listaZaposlenih = listaZaposlenih;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return listaZaposlenih.size();
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
        Zaposleni zaposleni = listaZaposlenih.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return zaposleni.getIme();
            case 1:
                return zaposleni.getPrezime();
            case 2:
                return zaposleni.getEmail();
            case 3:
                return zaposleni.getRadnoMesto();
            default:
                return "";
        }
    }

    public Zaposleni vratiOdabranogZaposlenog(int red) {
        return listaZaposlenih.get(red);
    }

    public void dodajZaposlenog(Zaposleni zaposleni) {
        listaZaposlenih.add(zaposleni);
        fireTableDataChanged();
    }

    public LinkedList<Zaposleni> vratiListu() {
        return listaZaposlenih;
    }

    public void obrisiZaposlenog(int red) {
        listaZaposlenih.remove(red);
        fireTableDataChanged();
    }

}

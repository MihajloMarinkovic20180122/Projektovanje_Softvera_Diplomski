/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemske.operacije.projekti;

import domen.OpstiDomenskiObjekat;
import domen.Projekat;
import java.util.LinkedList;
import sistemske.operacije.SOOpsteIzvrsenje;

/**
 *
 * @author Mihajlo
 */
public class SOVratiProjektePretraga extends SOOpsteIzvrsenje{

    private LinkedList<OpstiDomenskiObjekat> lista;
    private LinkedList<Projekat> listaProjekata;
    
    @Override
    public boolean proveriOgranicenja(OpstiDomenskiObjekat odo) throws Exception {
        return odo instanceof Projekat;
    }

    @Override
    public boolean izvrsiSO(OpstiDomenskiObjekat odo) throws Exception {
        Projekat projekat = (Projekat) odo;
        boolean signal = false;
        try {
            lista = (LinkedList<OpstiDomenskiObjekat>) dbb.vratiZaVrednost(projekat);
            signal = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return signal;
    }

    public LinkedList<Projekat> getLista() {
        listaProjekata = new LinkedList<>();
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
            listaProjekata.add((Projekat) opstiDomenskiObjekat);
        }
        return listaProjekata;
    }
    
    
}

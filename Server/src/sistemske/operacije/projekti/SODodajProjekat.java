/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemske.operacije.projekti;

import domen.Angazovanje;
import domen.OpstiDomenskiObjekat;
import domen.Projekat;
import domen.Stanje;
import domen.Zaposleni;
import java.util.LinkedList;
import sistemske.operacije.SOOpsteIzvrsenje;

/**
 *
 * @author Mihajlo
 */
public class SODodajProjekat extends SOOpsteIzvrsenje{

    @Override
    public boolean proveriOgranicenja(OpstiDomenskiObjekat odo) throws Exception {

        if (odo instanceof Projekat projekat) {
            if (projekat.getNazivProjekta() == null 
                || projekat.getPocetakRealizacije() == null
                || projekat.getRukovodilac() == null
                || projekat.getPrioritet() == null 
                || projekat.getStanje() == null) {
                return false;   
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean izvrsiSO(OpstiDomenskiObjekat odo) throws Exception {
        try {
            Projekat noviProjekat = (Projekat) odo;
            noviProjekat.setStanje(Stanje.Aktivan);
            int projekatId = dbb.zapamti(noviProjekat);
            if (projekatId > 0) {
                noviProjekat.setProjekatId(projekatId);
                LinkedList<Zaposleni> listaZaposlenih = noviProjekat.getZaposleni();
                for (Zaposleni zaposleni : listaZaposlenih) {
                    Angazovanje angazovanje = new Angazovanje(0, noviProjekat, zaposleni, noviProjekat.getPocetakRealizacije(), null);
                    dbb.zapamti(angazovanje);
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}

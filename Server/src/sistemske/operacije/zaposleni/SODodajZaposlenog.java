/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemske.operacije.zaposleni;

import domen.OpstiDomenskiObjekat;
import domen.RadnoMesto;
import domen.Zaposleni;
import java.util.LinkedList;
import sistemske.operacije.SOOpsteIzvrsenje;

/**
 *
 * @author Mihajlo
 */
public class SODodajZaposlenog extends SOOpsteIzvrsenje{

    @Override
    public boolean proveriOgranicenja(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof Zaposleni zaposleni) {
            if (zaposleni.getIme() == null 
                || zaposleni.getPrezime() == null
                || zaposleni.getEmail() == null
                || zaposleni.getDatumZaposlenja() == null 
                || zaposleni.getOrganizacionaCelina() == null
                || zaposleni.getRadnoMesto()== null) {
                return false;   
            }
            LinkedList<OpstiDomenskiObjekat> listaZaposlenih = dbb.vratiSve(odo);
            if (listaZaposlenih.contains(odo)) {
                return false;  
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean izvrsiSO(OpstiDomenskiObjekat odo) throws Exception {
        Zaposleni zaposleniZaDodati = (Zaposleni) odo;
        int zaposleniId = dbb.zapamti(zaposleniZaDodati);
        boolean dodatZaposleni = false;
        if(zaposleniId > 0){
            dodatZaposleni = true;
        }
        RadnoMesto rm = zaposleniZaDodati.getRadnoMesto();
        rm.setBrojZaposlenih(rm.getBrojZaposlenih() + 1);
        dbb.izmeni(rm);
        
        return dodatZaposleni;
    }
    
}

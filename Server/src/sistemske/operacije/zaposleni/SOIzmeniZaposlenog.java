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
public class SOIzmeniZaposlenog extends SOOpsteIzvrsenje{

    @Override
    public boolean proveriOgranicenja(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof Zaposleni zaposleni) {
            if (zaposleni.getIme() == null 
                || zaposleni.getPrezime() == null
                || zaposleni.getEmail() == null
                || zaposleni.getDatumZaposlenja() == null 
                || zaposleni.getOrganizacionaCelina() == null
                || zaposleni.getRadnoMesto() == null) {
                return false;   
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean izvrsiSO(OpstiDomenskiObjekat odo) throws Exception {
        LinkedList<OpstiDomenskiObjekat> zaposleniPreIzmene = dbb.ucitaj(odo);
        Zaposleni zaposleniPodaciPreIzmene = (Zaposleni) zaposleniPreIzmene.get(0);
        Zaposleni zaposleniZaIzmenu = (Zaposleni) odo;
        boolean izmenjenZaposleni = dbb.izmeni(odo);
        
        RadnoMesto rmPre = zaposleniPodaciPreIzmene.getRadnoMesto();
        RadnoMesto rm = zaposleniZaIzmenu.getRadnoMesto();
        
        if(!rmPre.equals(rm)){
            rmPre.setBrojZaposlenih(rmPre.getBrojZaposlenih() - 1);
            dbb.izmeni(rmPre);

            rm.setBrojZaposlenih(rm.getBrojZaposlenih() + 1);
            dbb.izmeni(rm);
        } else {
            
        }
        
        return izmenjenZaposleni;
    }
    
}

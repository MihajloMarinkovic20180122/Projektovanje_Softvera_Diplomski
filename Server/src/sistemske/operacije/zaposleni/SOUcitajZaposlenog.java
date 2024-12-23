/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemske.operacije.zaposleni;

import domen.OpstiDomenskiObjekat;
import domen.Zaposleni;
import sistemske.operacije.SOOpsteIzvrsenje;

/**
 *
 * @author Mihajlo
 */
public class SOUcitajZaposlenog extends SOOpsteIzvrsenje{

    Zaposleni ucitaniZaposleni;
    
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
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean izvrsiSO(OpstiDomenskiObjekat odo) throws Exception {
        ucitaniZaposleni = (Zaposleni) dbb.ucitaj(odo).get(0);
        return true;
    }

    public Zaposleni getUcitaniZaposleni() {
        return ucitaniZaposleni;
    }
    
}

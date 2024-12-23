/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemske.operacije.zaposleni;

import domen.OpstiDomenskiObjekat;
import domen.Zaposleni;
import java.util.LinkedList;
import sistemske.operacije.SOOpsteIzvrsenje;

/**
 *
 * @author Mihajlo
 */
public class SOVratiSveZaposlene extends SOOpsteIzvrsenje{

    private LinkedList<OpstiDomenskiObjekat> lista;
    private LinkedList<Zaposleni> listaZaposlenih;
    
    @Override
    public boolean proveriOgranicenja(OpstiDomenskiObjekat odo) throws Exception {
        return odo instanceof Zaposleni;
    }

    @Override
    public boolean izvrsiSO(OpstiDomenskiObjekat odo) throws Exception {
        boolean signal = false;
        
        try {
            lista = dbb.vratiSve(new Zaposleni());
            signal = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return signal;
    }

    public LinkedList<Zaposleni> getLista() {
        listaZaposlenih = new LinkedList<>();
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
            listaZaposlenih.add((Zaposleni) opstiDomenskiObjekat);
        }
        return listaZaposlenih;
    }
    
    
    
}

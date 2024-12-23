/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemske.operacije.angazovanja;

import domen.Angazovanje;
import domen.OpstiDomenskiObjekat;
import domen.Zaposleni;
import java.util.LinkedList;
import sistemske.operacije.SOOpsteIzvrsenje;

/**
 *
 * @author Korisnik
 */
public class SOVratiAngazovanjaZaposlenog extends SOOpsteIzvrsenje{

    private LinkedList<OpstiDomenskiObjekat> lista = new LinkedList<>();
    private LinkedList<Angazovanje> listaAngazovanja;
    
    @Override
    public boolean proveriOgranicenja(OpstiDomenskiObjekat odo) throws Exception {
        return odo instanceof Zaposleni;
    }

    @Override
    public boolean izvrsiSO(OpstiDomenskiObjekat odo) throws Exception {
        Zaposleni zaposleni =  (Zaposleni) odo;
        LinkedList<OpstiDomenskiObjekat> listaAngazovanjaOpsta = dbb.vratiSve(new Angazovanje());
        boolean signal = false;
        try {
            for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaAngazovanjaOpsta) {
            Angazovanje a =  (Angazovanje) opstiDomenskiObjekat;
            if(a.getZaposleni().equals(zaposleni)){
                lista.add(opstiDomenskiObjekat);
            }
        }
            signal = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return signal;
    }
    
    public LinkedList<Angazovanje> getLista() {
        listaAngazovanja = new LinkedList<>();
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
            listaAngazovanja.add((Angazovanje) opstiDomenskiObjekat);
        }
        return listaAngazovanja;
    }
    
}

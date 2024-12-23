/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemske.operacije.login.radnamesta;

import domen.OpstiDomenskiObjekat;
import domen.RadnoMesto;
import java.util.LinkedList;
import sistemske.operacije.SOOpsteIzvrsenje;

/**
 *
 * @author Mihajlo
 */
public class SOVratiRadnaMesta extends SOOpsteIzvrsenje{
    
    private LinkedList<OpstiDomenskiObjekat> lista;
    private LinkedList<RadnoMesto> listaRadnihMesta;

    @Override
    public boolean proveriOgranicenja(OpstiDomenskiObjekat odo) throws Exception {
        return odo instanceof RadnoMesto;
    }

    @Override
    public boolean izvrsiSO(OpstiDomenskiObjekat odo) throws Exception {
        boolean signal = false;
        
        try {
            lista = dbb.ucitaj(odo);
            signal = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return signal;
    }

    public LinkedList<RadnoMesto> getLista() {
        listaRadnihMesta = new LinkedList<>();
        if(lista != null){
            for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
            listaRadnihMesta.add((RadnoMesto) opstiDomenskiObjekat);
            }
            return listaRadnihMesta;
        }
        return null;
    }
    
    
    
}

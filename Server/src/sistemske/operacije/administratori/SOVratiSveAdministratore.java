/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemske.operacije.administratori;

import domen.Administrator;
import domen.OpstiDomenskiObjekat;
import java.util.LinkedList;
import sistemske.operacije.SOOpsteIzvrsenje;

/**
 *
 * @author Mihajlo
 */
public class SOVratiSveAdministratore extends SOOpsteIzvrsenje{

    private LinkedList<OpstiDomenskiObjekat> lista;
    private LinkedList<Administrator> listaAdministratora;

    @Override
    public boolean proveriOgranicenja(OpstiDomenskiObjekat odo) throws Exception {
        return odo instanceof Administrator;
    }

    @Override
    public boolean izvrsiSO(OpstiDomenskiObjekat odo) throws Exception {
        boolean signal = false;
        
        try {
            lista = dbb.vratiSve(odo);
            signal = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return signal;
    }

    public LinkedList<Administrator> getLista() {
        listaAdministratora = new LinkedList<>();
        if(lista != null){
            for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
            listaAdministratora.add((Administrator) opstiDomenskiObjekat);
            }
            return listaAdministratora;
        }
        return null;
    }
    
}

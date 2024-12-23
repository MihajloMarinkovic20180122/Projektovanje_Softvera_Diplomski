/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemske.operacije.login.organizacioneceline;

import domen.OpstiDomenskiObjekat;
import domen.OrganizacionaCelina;
import java.util.LinkedList;
import sistemske.operacije.SOOpsteIzvrsenje;

/**
 *
 * @author Mihajlo
 */
public class SOVratiOrganizacioneCeline extends SOOpsteIzvrsenje{

    private LinkedList<OpstiDomenskiObjekat> lista;
    private LinkedList<OrganizacionaCelina> listaOrganizacionihCelina;
    
    @Override
    public boolean proveriOgranicenja(OpstiDomenskiObjekat odo) throws Exception {
        return odo instanceof OrganizacionaCelina;
    }

    @Override
    public boolean izvrsiSO(OpstiDomenskiObjekat odo) throws Exception {
        boolean signal = false;
        
        try {
            lista = dbb.vratiSve(new OrganizacionaCelina());
            signal = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return signal;
    }

    public LinkedList<OrganizacionaCelina> getLista() {
        listaOrganizacionihCelina = new LinkedList<>();
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
            listaOrganizacionihCelina.add((OrganizacionaCelina) opstiDomenskiObjekat);
        }
        return listaOrganizacionihCelina;
    }
    
    
    
}

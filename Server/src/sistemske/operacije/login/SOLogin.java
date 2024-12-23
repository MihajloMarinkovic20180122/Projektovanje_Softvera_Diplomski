/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemske.operacije.login;

import domen.Administrator;
import domen.OpstiDomenskiObjekat;
import java.util.LinkedList;
import sistemske.operacije.SOOpsteIzvrsenje;

/**
 *
 * @author Mihajlo
 */
public class SOLogin extends SOOpsteIzvrsenje{
    Administrator ulogovani;

    @Override
    public boolean proveriOgranicenja(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof Administrator administrator) {
            if (administrator.getEmail() == null || administrator.getLozinka()== null) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean izvrsiSO(OpstiDomenskiObjekat odo) throws Exception {
        Administrator login = (Administrator) odo;
        LinkedList<OpstiDomenskiObjekat> listaAdministratora = dbb.vratiSve(odo);
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaAdministratora) {
            Administrator administrator = (Administrator) opstiDomenskiObjekat;
            if((administrator.getEmail() == null ? login.getEmail() == null : administrator.getEmail().equals(login.getEmail())) && (administrator.getLozinka() == null ? login.getLozinka() == null : administrator.getLozinka().equals(login.getLozinka()))){
                ulogovani = administrator;
                return true;
            }
        }
        return true;
    }
    
     public Administrator getUlogovani() {
        return ulogovani;
    }
    
}

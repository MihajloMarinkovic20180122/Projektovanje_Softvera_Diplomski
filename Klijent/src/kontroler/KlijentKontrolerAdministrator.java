/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Administrator;
import transfer.Operacije;

/**
 *
 * @author Mihajlo
 */
public class KlijentKontrolerAdministrator extends OpstiKlijentskiKontroler{
    
    private static KlijentKontrolerAdministrator instanca;

    private KlijentKontrolerAdministrator() throws Exception {
    }

    public static KlijentKontrolerAdministrator getInstanca() throws Exception {
        if(instanca == null){
            instanca = new KlijentKontrolerAdministrator();
        }
        return instanca;
    }

    public void logout() throws Exception {
        String koristiJSON = transfer.Transfer.getKoristiJSON();
        if(koristiJSON.equals("true")){
            posaljiZahtevJSON(Operacije.LOGOUT, null, Administrator.class);
        } else {
            posaljiZahtev(Operacije.LOGOUT, null);
        }
    }

    
}

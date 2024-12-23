/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Administrator;
import java.io.IOException;
import transfer.Operacije;

/**
 *
 * @author Mihajlo
 */
public class KlijentKontrolerLogin extends OpstiKlijentskiKontroler{
    
    private static KlijentKontrolerLogin instanca;

    private KlijentKontrolerLogin() throws Exception{
    }

    public static KlijentKontrolerLogin getInstanca() throws Exception {
        if(instanca == null){
            instanca = new KlijentKontrolerLogin();
        }
        return instanca;
    }
    
    
    public Administrator login(Administrator administrator) throws Exception {
        return (Administrator) posaljiZahtev(Operacije.LOGIN, administrator);
    }
    
    
    
}

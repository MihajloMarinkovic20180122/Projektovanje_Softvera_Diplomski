/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Angazovanje;
import java.util.LinkedList;
import transfer.Operacije;

/**
 *
 * @author Mihajlo
 */
public class KlijentKontrolerAngazovanje extends OpstiKlijentskiKontroler{
    private static KlijentKontrolerAngazovanje instanca;

    private KlijentKontrolerAngazovanje() throws Exception {
    }

    public static KlijentKontrolerAngazovanje getInstanca() throws Exception {
        if(instanca == null){
            instanca = new KlijentKontrolerAngazovanje();
        }
        return instanca;
    }
    
    public void dodajAngazovanje(Angazovanje angazovanje) throws Exception {
        posaljiZahtev(Operacije.DODAJ_ANGAZOVANJE, angazovanje);
    }

    public LinkedList<Angazovanje> vratiAngazovanja() throws Exception {
        return (LinkedList<Angazovanje>) posaljiZahtev(Operacije.VRATI_ANGAZOVANJA, null);
    }
    
    
}

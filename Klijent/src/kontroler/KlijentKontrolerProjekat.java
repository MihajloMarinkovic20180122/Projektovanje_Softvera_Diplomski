/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Projekat;
import java.util.LinkedList;
import transfer.Operacije;

/**
 *
 * @author Mihajlo
 */
public class KlijentKontrolerProjekat extends OpstiKlijentskiKontroler{
    private static KlijentKontrolerProjekat instanca;

    private KlijentKontrolerProjekat() throws Exception {
    }

    public static KlijentKontrolerProjekat getInstanca() throws Exception {
        if(instanca == null){
            instanca = new KlijentKontrolerProjekat();
        }
        return instanca;
    }
    
    public void dodajProjekat(Projekat projekat) throws Exception {
        posaljiZahtev(Operacije.DODAJ_PROJEKAT, projekat, Projekat.class);
    }
    
     public LinkedList<Projekat> vratiProjekte() throws Exception {
        return posaljiZahtevZaListu(Operacije.VRATI_PROJEKTE, null, Projekat.class);
    }

    public LinkedList<Projekat> pronadjiPaVratiProjekte(String pretraga) throws Exception {
        Projekat projekat = new Projekat();
        projekat.setVrednostZaPretragu(pretraga);
        
        return posaljiZahtevZaListu(Operacije.VRATI_PROJEKTE_PRETRAGA, projekat, Projekat.class);
    }

    public void obrisiProjekat(Projekat projekat) throws Exception {
        posaljiZahtev(Operacije.OBRISI_PROJEKAT, projekat, Projekat.class);
    }

    public Projekat ucitajProjekat(Projekat odabraniProjekat) throws Exception {
        return posaljiZahtev(Operacije.UCITAJ_PROJEKAT, odabraniProjekat, Projekat.class);
    }

    public void izmeniProjekat(Projekat izmenjeniProjekat) throws Exception {
        posaljiZahtev(Operacije.IZMENI_PODATKE_PROJEKTA, izmenjeniProjekat, Projekat.class);
    }
    
}

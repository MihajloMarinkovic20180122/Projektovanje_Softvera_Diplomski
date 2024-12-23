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
        posaljiZahtev(Operacije.DODAJ_PROJEKAT, projekat);
    }
    
     public LinkedList<Projekat> vratiProjekte() throws Exception {
        return (LinkedList<Projekat>) posaljiZahtev(Operacije.VRATI_PROJEKTE, null);
    }

    public LinkedList<Projekat> pronadjiPaVratiProjekte(String pretraga) throws Exception {
        Projekat projekat = new Projekat();
        projekat.setVrednostZaPretragu(pretraga);
        return (LinkedList<Projekat>) posaljiZahtev(Operacije.VRATI_PROJEKTE_PRETRAGA, projekat);
    }

    public void obrisiProjekat(Projekat projekat) throws Exception {
        posaljiZahtev(Operacije.OBRISI_PROJEKAT, projekat);
    }

    public Projekat ucitajProjekat(Projekat odabraniProjekat) throws Exception {
        return (Projekat) posaljiZahtev(Operacije.UCITAJ_PROJEKAT, odabraniProjekat);
    }

    public void izmeniProjekat(Projekat izmenjeniProjekat) throws Exception {
        posaljiZahtev(Operacije.IZMENI_PODATKE_PROJEKTA, izmenjeniProjekat);
    }
    
}

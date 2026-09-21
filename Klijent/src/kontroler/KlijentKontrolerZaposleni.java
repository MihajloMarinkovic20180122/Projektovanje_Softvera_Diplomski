/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Angazovanje;
import domen.OrganizacionaCelina;
import domen.RadnoMesto;
import domen.Zaposleni;
import java.util.LinkedList;
import transfer.Operacije;

/**
 *
 * @author Mihajlo
 */
public class KlijentKontrolerZaposleni extends OpstiKlijentskiKontroler{
    private static KlijentKontrolerZaposleni instanca;

    private KlijentKontrolerZaposleni() throws Exception{
    }

    public static KlijentKontrolerZaposleni getInstanca() throws Exception {
        if(instanca == null){
            instanca = new KlijentKontrolerZaposleni();
        }
        return instanca;
    }
    
    public LinkedList<OrganizacionaCelina> vratiOrganizacioneCeline() throws Exception {
        return posaljiZahtevZaListu(Operacije.VRATI_ORGANIZACIONE_CELINE, null, OrganizacionaCelina.class);
    }

    public LinkedList<RadnoMesto> vratiRadnaMesta(OrganizacionaCelina organizacionaCelina) throws Exception {
        return posaljiZahtevZaListu(Operacije.VRATI_RADNA_MESTA, organizacionaCelina, RadnoMesto.class);
    }

    public void dodajZaposlenog(Zaposleni zaposleni) throws Exception {
        posaljiZahtev(Operacije.DODAJ_ZAPOSLENOG, zaposleni, Zaposleni.class);
    }

    public LinkedList<Zaposleni> vratiZaposlene(boolean prikaziObrisane) throws Exception {
        Zaposleni zaposleni = new Zaposleni();
        zaposleni.setPrikaziObrisane(prikaziObrisane);
        return posaljiZahtevZaListu(Operacije.VRATI_ZAPOSLENE, zaposleni, Zaposleni.class);
    }
    
    public LinkedList<Zaposleni> pronadjiPaVratiZaposlene(String pretraga, boolean prikaziObrisane) throws Exception {
        Zaposleni zaposleni = new Zaposleni();
        zaposleni.setVrednostZaPretragu(pretraga);
        zaposleni.setPrikaziObrisane(prikaziObrisane);
        
        return posaljiZahtevZaListu(Operacije.VRATI_ZAPOSLENE_PRETRAGA, zaposleni, Zaposleni.class);
    }

    public void obrisiZaposlenog(Zaposleni zaposleni) throws Exception {
        posaljiZahtev(Operacije.OBRISI_ZAPOSLENOG, zaposleni, Zaposleni.class);
    }

    public Zaposleni ucitajZaposlenog(Zaposleni odabraniZaposleni) throws Exception {
        return posaljiZahtev(Operacije.UCITAJ_ZAPOSLENOG, odabraniZaposleni, Zaposleni.class);
    }

    public void izmeniZaposlenog(Zaposleni izmenjenZaposleni) throws Exception {
        posaljiZahtev(Operacije.IZMENI_PODATKE_ZAPOSLENOG, izmenjenZaposleni, Zaposleni.class);
    }

    public LinkedList<Angazovanje> vratiAngazovanjaZaposleog(Zaposleni ucitaniZaposleni) throws Exception {
        return posaljiZahtevZaListu(Operacije.VRATI_ANGAZOVANJA_ZAPOSLENOG, ucitaniZaposleni, Angazovanje.class);
    }
    
}

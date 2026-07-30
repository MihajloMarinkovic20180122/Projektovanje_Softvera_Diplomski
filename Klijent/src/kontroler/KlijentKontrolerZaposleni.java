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
        String koristiJSON = transfer.Transfer.getKoristiJSON();
        if(koristiJSON.equals("true")){
            return (LinkedList<OrganizacionaCelina>) posaljiZahtevZaListuJSON(Operacije.VRATI_ORGANIZACIONE_CELINE, null, OrganizacionaCelina.class);
        } else {
            return (LinkedList<OrganizacionaCelina>) posaljiZahtev(Operacije.VRATI_ORGANIZACIONE_CELINE, null);
        }
    }

    public LinkedList<RadnoMesto> vratiRadnaMesta(OrganizacionaCelina organizacionaCelina) throws Exception {
        String koristiJSON = transfer.Transfer.getKoristiJSON();
        if(koristiJSON.equals("true")){
            return (LinkedList<RadnoMesto>) posaljiZahtevZaListuJSON(Operacije.VRATI_RADNA_MESTA, organizacionaCelina, RadnoMesto.class);
        } else {
            return (LinkedList<RadnoMesto>) posaljiZahtev(Operacije.VRATI_RADNA_MESTA, organizacionaCelina);
        }
    }

    public void dodajZaposlenog(Zaposleni zaposleni) throws Exception {
        String koristiJSON = transfer.Transfer.getKoristiJSON();
        if(koristiJSON.equals("true")){
            posaljiZahtevJSON(Operacije.DODAJ_ZAPOSLENOG, zaposleni, Zaposleni.class);
        } else {
            posaljiZahtev(Operacije.DODAJ_ZAPOSLENOG, zaposleni);
        }
    }

    public LinkedList<Zaposleni> vratiZaposlene() throws Exception {
        String koristiJSON = transfer.Transfer.getKoristiJSON();
        if(koristiJSON.equals("true")){
            return (LinkedList<Zaposleni>) posaljiZahtevZaListuJSON(Operacije.VRATI_ZAPOSLENE, null, Zaposleni.class);
        } else {
            return (LinkedList<Zaposleni>) posaljiZahtev(Operacije.VRATI_ZAPOSLENE, null);
        }
    }
    
    public LinkedList<Zaposleni> pronadjiPaVratiZaposlene(String pretraga) throws Exception {
        Zaposleni zaposleni = new Zaposleni();
        zaposleni.setVrednostZaPretragu(pretraga);
        
        String koristiJSON = transfer.Transfer.getKoristiJSON();
        if(koristiJSON.equals("true")){
            return (LinkedList<Zaposleni>) posaljiZahtevZaListuJSON(Operacije.VRATI_ZAPOSLENE_PRETRAGA, zaposleni, Zaposleni.class);
        } else {
            return (LinkedList<Zaposleni>) posaljiZahtev(Operacije.VRATI_ZAPOSLENE_PRETRAGA, zaposleni);
        }
    }

    public void obrisiZaposlenog(Zaposleni zaposleni) throws Exception {
        String koristiJSON = transfer.Transfer.getKoristiJSON();
        if(koristiJSON.equals("true")){
            posaljiZahtevJSON(Operacije.OBRISI_ZAPOSLENOG, zaposleni, Zaposleni.class);
        } else {
            posaljiZahtev(Operacije.OBRISI_ZAPOSLENOG, zaposleni);
        }
    }

    public Zaposleni ucitajZaposlenog(Zaposleni odabraniZaposleni) throws Exception {
        String koristiJSON = transfer.Transfer.getKoristiJSON();
        if(koristiJSON.equals("true")){
            return (Zaposleni) posaljiZahtevJSON(Operacije.UCITAJ_ZAPOSLENOG, odabraniZaposleni, Zaposleni.class);
        } else {
            return (Zaposleni) posaljiZahtev(Operacije.UCITAJ_ZAPOSLENOG, odabraniZaposleni);
        }
    }

    public void izmeniZaposlenog(Zaposleni izmenjenZaposleni) throws Exception {
        String koristiJSON = transfer.Transfer.getKoristiJSON();
        if(koristiJSON.equals("true")){
            posaljiZahtevJSON(Operacije.IZMENI_PODATKE_ZAPOSLENOG, izmenjenZaposleni, Zaposleni.class);
        } else {
            posaljiZahtev(Operacije.IZMENI_PODATKE_ZAPOSLENOG, izmenjenZaposleni);
        }
    }

    public LinkedList<Angazovanje> vratiAngazovanjaZaposleog(Zaposleni ucitaniZaposleni) throws Exception {
        String koristiJSON = transfer.Transfer.getKoristiJSON();
        if(koristiJSON.equals("true")){
            return (LinkedList<Angazovanje>) posaljiZahtevZaListuJSON(Operacije.VRATI_ANGAZOVANJA_ZAPOSLENOG, ucitaniZaposleni, Angazovanje.class);
        } else {
            return (LinkedList<Angazovanje>) posaljiZahtev(Operacije.VRATI_ANGAZOVANJA_ZAPOSLENOG, ucitaniZaposleni);
        }
    }

    
    
}

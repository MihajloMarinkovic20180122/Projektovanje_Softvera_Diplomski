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
        return (LinkedList<OrganizacionaCelina>) posaljiZahtev(Operacije.VRATI_ORGANIZACIONE_CELINE, null);
    }

    public LinkedList<RadnoMesto> vratiRadnaMesta(OrganizacionaCelina organizacionaCelina) throws Exception {
        return (LinkedList<RadnoMesto>) posaljiZahtev(Operacije.VRATI_RADNA_MESTA, organizacionaCelina);
    }

    public void dodajZaposlenog(Zaposleni zaposleni) throws Exception {
        posaljiZahtev(Operacije.DODAJ_ZAPOSLENOG, zaposleni);
    }

    public LinkedList<Zaposleni> vratiZaposlene() throws Exception {
        return (LinkedList<Zaposleni>) posaljiZahtev(Operacije.VRATI_ZAPOSLENE, null);
    }
    
    public LinkedList<Zaposleni> pronadjiPaVratiZaposlene(String pretraga) throws Exception {
        Zaposleni zaposleni = new Zaposleni();
        zaposleni.setVrednostZaPretragu(pretraga);
        return (LinkedList<Zaposleni>) posaljiZahtev(Operacije.VRATI_ZAPOSLENE_PRETRAGA, zaposleni);
    }

    public void obrisiZaposlenog(Zaposleni zaposleni) throws Exception {
        posaljiZahtev(Operacije.OBRISI_ZAPOSLENOG, zaposleni);
    }

    public Zaposleni ucitajZaposlenog(Zaposleni odabraniZaposleni) throws Exception {
        return (Zaposleni) posaljiZahtev(Operacije.UCITAJ_ZAPOSLENOG, odabraniZaposleni);
    }

    public void izmeniZaposlenog(Zaposleni izmenjenZaposleni) throws Exception {
        posaljiZahtev(Operacije.IZMENI_PODATKE_ZAPOSLENOG, izmenjenZaposleni);
    }

    public LinkedList<Angazovanje> vratiAngazovanjaZaposleog(Zaposleni ucitaniZaposleni) throws Exception {
        return (LinkedList<Angazovanje>) posaljiZahtev(Operacije.VRATI_ANGAZOVANJA_ZAPOSLENOG, ucitaniZaposleni);
    }

    
    
}

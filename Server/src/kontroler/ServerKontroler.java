/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import baza.DBBroker;
import domen.Administrator;
import domen.Angazovanje;
import domen.OrganizacionaCelina;
import domen.Projekat;
import domen.RadnoMesto;
import domen.Zaposleni;
import java.sql.SQLException;
import java.util.LinkedList;
import sistemske.operacije.administratori.SOVratiSveAdministratore;
import sistemske.operacije.angazovanja.SOVratiAngazovanjaZaposlenog;
import sistemske.operacije.login.SOLogin;
import sistemske.operacije.login.organizacioneceline.SOVratiOrganizacioneCeline;
import sistemske.operacije.login.radnamesta.SOVratiRadnaMesta;
import sistemske.operacije.projekti.SODodajProjekat;
import sistemske.operacije.projekti.SOIzmeniProjekat;
import sistemske.operacije.projekti.SOObrisiProjekat;
import sistemske.operacije.projekti.SOUcitajProjekat;
import sistemske.operacije.projekti.SOVratiProjektePretraga;
import sistemske.operacije.projekti.SOVratiSveProjekte;
import sistemske.operacije.zaposleni.SODodajZaposlenog;
import sistemske.operacije.zaposleni.SOIzmeniZaposlenog;
import sistemske.operacije.zaposleni.SOObrisiZaposlenog;
import sistemske.operacije.zaposleni.SOUcitajZaposlenog;
import sistemske.operacije.zaposleni.SOVratiSveZaposlene;
import sistemske.operacije.zaposleni.SOVratiZaposlenePretraga;

/**
 *
 * @author Mihajlo
 */
public class ServerKontroler {
    private static ServerKontroler instanca;

    private ServerKontroler() {
    }

    public static ServerKontroler getInstanca() {
        if(instanca == null){
            instanca = new ServerKontroler();
        }
        return instanca;
    }
    
    public LinkedList<Administrator> vratiSveAdministratratore() throws Exception {
        SOVratiSveAdministratore so = new SOVratiSveAdministratore();
        so.sOOpsteIzvrsenje(new Administrator());
        return so.getLista();
    }

    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.sOOpsteIzvrsenje(administrator);
        return so.getUlogovani();
    }

    public LinkedList<OrganizacionaCelina> vratiOrganizacioneCeline() throws Exception {
        SOVratiOrganizacioneCeline so = new SOVratiOrganizacioneCeline();
        so.sOOpsteIzvrsenje(new OrganizacionaCelina());
        return so.getLista();
    }

    public LinkedList<RadnoMesto> vratiRadnaMesta(OrganizacionaCelina organizacionaCelina) throws Exception {
        SOVratiRadnaMesta so = new SOVratiRadnaMesta();
        RadnoMesto rm = new RadnoMesto();
        rm.setOrganizacionaCelina(organizacionaCelina);
        so.sOOpsteIzvrsenje(rm);
        return so.getLista();
    }

    public boolean dodajZaposlenog(Zaposleni zaposleni) throws Exception {
        SODodajZaposlenog so = new SODodajZaposlenog();
        return so.sOOpsteIzvrsenje(zaposleni);
    }

    public LinkedList<Zaposleni> vratiZaposlene() throws Exception {
        SOVratiSveZaposlene so = new SOVratiSveZaposlene();
        so.sOOpsteIzvrsenje(new Zaposleni());
        return so.getLista();
    }
    
    public Zaposleni ucitajZaposlenog(Zaposleni zaposleniZaUcitavanje) throws SQLException {
        SOUcitajZaposlenog so = new SOUcitajZaposlenog();
        so.sOOpsteIzvrsenje(zaposleniZaUcitavanje);
        return so.getUcitaniZaposleni();
    }
    
    public boolean obrisiZaposlenog(Zaposleni zaposleniZaObrisati) throws Exception {
        SOObrisiZaposlenog so = new SOObrisiZaposlenog();
        return so.sOOpsteIzvrsenje(zaposleniZaObrisati);
    }
    
    public LinkedList<Zaposleni> vratiZaposlenePretraga(Zaposleni pretragaZaposleni) throws Exception {
        SOVratiZaposlenePretraga so = new SOVratiZaposlenePretraga();
        so.sOOpsteIzvrsenje(pretragaZaposleni);
        return so.getLista();
    }
    
     public boolean izmeniPodatkeZaposlenog(Zaposleni izmenjeniZaposleni) throws SQLException {
         SOIzmeniZaposlenog so = new SOIzmeniZaposlenog();
         return so.sOOpsteIzvrsenje(izmenjeniZaposleni);
    }
    

    public boolean dodajProjekat(Projekat projekat) throws Exception {
        SODodajProjekat so = new SODodajProjekat();
        return so.sOOpsteIzvrsenje(projekat);
    }

    public LinkedList<Projekat> vratiProjekte() throws Exception {
        SOVratiSveProjekte so = new SOVratiSveProjekte();
        so.sOOpsteIzvrsenje(new Projekat());
        return so.getLista();
    }

    public LinkedList<Projekat> vratiProjektePretraga(Projekat pretragaProjekti) throws Exception {
        SOVratiProjektePretraga so = new SOVratiProjektePretraga();
        so.sOOpsteIzvrsenje(pretragaProjekti);
        return so.getLista();
    }
    
    public Projekat ucitajProjekat(Projekat projekatZaUcitavanje) throws SQLException {
        SOUcitajProjekat so = new SOUcitajProjekat();
        so.sOOpsteIzvrsenje(projekatZaUcitavanje);
        return so.getUcitaniProjekat();
    }
    
    public boolean izmeniPodatkeProjekta(Projekat izmenjeniProjekat) throws SQLException {
        SOIzmeniProjekat so = new SOIzmeniProjekat();
        return so.sOOpsteIzvrsenje(izmenjeniProjekat);
    }
    

    public boolean obrisiProjekat(Projekat projekatZaObrisati) throws Exception {
        SOObrisiProjekat so = new SOObrisiProjekat();
        return so.sOOpsteIzvrsenje(projekatZaObrisati);
    }

    public LinkedList<Angazovanje> vratiAngazovanjaZaZaposlenog(Zaposleni zaposleniZaVratitiAngazovanja) throws SQLException {
        SOVratiAngazovanjaZaposlenog so = new SOVratiAngazovanjaZaposlenog();
        so.sOOpsteIzvrsenje(zaposleniZaVratitiAngazovanja);
        return so.getLista();
    }
    
}

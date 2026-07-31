package niti;

import com.fasterxml.jackson.databind.ObjectMapper;
import domen.Administrator;
import domen.Angazovanje;
import domen.OrganizacionaCelina;
import domen.Projekat;
import domen.RadnoMesto;
import domen.Zaposleni;
import java.net.Socket;
import java.util.LinkedList;
import kontroler.ServerKontroler;
import strategijaTransfera.ServerskaStrategijaFactory;
import strategijaTransfera.ServerskaStrategijaKomunikacije;
import transfer.KlijentskiZahtev;
import transfer.Operacije;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Mihajlo
 */
public class ObradaKlijentskihZahtevaNit extends Thread{

    Socket s;
    ServerskaNit sn;
    Administrator administrator;
    private final ObjectMapper mapper = new ObjectMapper();
    protected ServerskaStrategijaKomunikacije strategija;
    
    public ObradaKlijentskihZahtevaNit(Socket s, ServerskaNit sn) throws Exception {
        this.s = s;
        this.sn = sn;
        String format = transfer.Transfer.getFormat();
        strategija = ServerskaStrategijaFactory.kreirajServerskuStrategiju(s, format);
    }
    
    @Override
    public void run() {
        try {
            while (s != null && !s.isClosed()) {
                    KlijentskiZahtev kz = strategija.primiZahtev();
                    ServerskiOdgovor so;
                    try {
                        so = obradiZahtev(kz);
                    } catch (Exception e) {
                        so = new ServerskiOdgovor();
                        so.setGreska(e);
                    }
                    strategija.posaljiOdgovor(so);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        administrator = null;
    }

    private ServerskiOdgovor obradiZahtev(KlijentskiZahtev kz) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        try{
            switch (kz.getOperacija()) {
                case Operacije.LOGIN:
                    Administrator administratorPokusaj = mapper.convertValue(kz.getParametar(), Administrator.class);
                    sn.mozeDaSeUloguje(administratorPokusaj);
                    administrator = ServerKontroler.getInstanca().login(administratorPokusaj);
                    if(administrator != null){
                        so.setOdgovor(administrator);
                    }
                    else {
                        throw new SecurityException("Pogresni kredencijali. Ponovite unos.");
                    }
                    break;
                case Operacije.LOGOUT:
                    administrator = null;
                    break;
                case Operacije.VRATI_ORGANIZACIONE_CELINE:
                    LinkedList<OrganizacionaCelina> listaOrganizacionihCelina = ServerKontroler.getInstanca().vratiOrganizacioneCeline();
                    if(listaOrganizacionihCelina == null){
                        throw new Exception("Doslo je do greske pri ucitavanju Organizacionih Celina.");
                    } else {
                        so.setOdgovor(listaOrganizacionihCelina);
                    }
                    break;
                case Operacije.VRATI_RADNA_MESTA:
                    OrganizacionaCelina organizacionaCelina = mapper.convertValue(kz.getParametar(), OrganizacionaCelina.class);
                    LinkedList<RadnoMesto> listaRadnihMesta = ServerKontroler.getInstanca().vratiRadnaMesta(organizacionaCelina);
                    if(listaRadnihMesta == null){
                        throw new Exception("Doslo je do greske pri ucitavanju Radnih Mesta.");
                    } else {
                        so.setOdgovor(listaRadnihMesta);
                    }
                    break;
                case Operacije.DODAJ_ZAPOSLENOG:
                    Zaposleni zaposleni = mapper.convertValue(kz.getParametar(), Zaposleni.class);
                    boolean uspesnoSacuvanZaposleni = ServerKontroler.getInstanca().dodajZaposlenog(zaposleni);
                    if(!uspesnoSacuvanZaposleni){
                        //throw new Exception("Zaposleni sa tim Email vec postoji.");
                        throw new Exception("Sistem ne može da zapamti zaposlenog.");
                    }
                    break;
                case Operacije.VRATI_ZAPOSLENE:
                    LinkedList<Zaposleni> listaZaposlenih = ServerKontroler.getInstanca().vratiZaposlene();
                    if(listaZaposlenih == null){
                        throw new Exception("Doslo je do greske pri ucitavanju svih zaposlenih.");
                    } else {
                        so.setOdgovor(listaZaposlenih);
                    }
                    break;
                case Operacije.UCITAJ_ZAPOSLENOG:
                    Zaposleni zaposleniZaUcitavanje = mapper.convertValue(kz.getParametar(), Zaposleni.class);
                    Zaposleni ucitaniZaposleni = ServerKontroler.getInstanca().ucitajZaposlenog(zaposleniZaUcitavanje);
                    if(ucitaniZaposleni == null){
                        throw new Exception("Sistem ne može da učita zaposlenog.");
                    } else {
                        so.setOdgovor(ucitaniZaposleni);
                    }
                    break;
                case Operacije.OBRISI_ZAPOSLENOG:
                    Zaposleni zaposleniZaObrisati = mapper.convertValue(kz.getParametar(), Zaposleni.class);
                    boolean uspesnoObrisanZaposleni = ServerKontroler.getInstanca().obrisiZaposlenog(zaposleniZaObrisati);
                    if(!uspesnoObrisanZaposleni){
                        throw new Exception("Doslo je do greske prilikom brisanja zaposlenog!");
                    }
                    break;
                case Operacije.VRATI_ZAPOSLENE_PRETRAGA:
                    Zaposleni pretragaZaposleni = mapper.convertValue(kz.getParametar(), Zaposleni.class);
                    LinkedList<Zaposleni> listaZaposlenihPretraga = ServerKontroler.getInstanca().vratiZaposlenePretraga(pretragaZaposleni);
                    if(listaZaposlenihPretraga == null){
                        throw new Exception("Doslo je do greske pri pretrazi zaposlenih.");
                    } else {
                        so.setOdgovor(listaZaposlenihPretraga);
                    }
                    break;
                case Operacije.IZMENI_PODATKE_ZAPOSLENOG:
                    Zaposleni izmenjeniZaposleni = mapper.convertValue(kz.getParametar(), Zaposleni.class);
                    boolean uspesnoIzmenjenZaposleni = ServerKontroler.getInstanca().izmeniPodatkeZaposlenog(izmenjeniZaposleni);
                    if(!uspesnoIzmenjenZaposleni){
                        throw new Exception("Doslo je do greske prilikom izmene podataka zaposlenog!");
                    }
                    break;
                case Operacije.DODAJ_PROJEKAT:
                    Projekat projekat = mapper.convertValue(kz.getParametar(), Projekat.class);
                    boolean uspesnoSacuvanProjekat = ServerKontroler.getInstanca().dodajProjekat(projekat);
                    if(!uspesnoSacuvanProjekat){
                        throw new Exception("Sistem ne može da zapamti projekat.");
                    }
                    break;
                case Operacije.VRATI_PROJEKTE:
                    LinkedList<Projekat> listaProjekata = ServerKontroler.getInstanca().vratiProjekte();
                    if(listaProjekata == null){
                        throw new Exception("Doslo je do greske pri ucitavanju svih projekata.");
                    } else {
                        so.setOdgovor(listaProjekata);
                    }
                    break;
                case Operacije.VRATI_PROJEKTE_PRETRAGA:
                    Projekat pretragaProjekti = mapper.convertValue(kz.getParametar(), Projekat.class);
                    LinkedList<Projekat> listaProjekataPretraga = ServerKontroler.getInstanca().vratiProjektePretraga(pretragaProjekti);
                    if(listaProjekataPretraga == null){
                        throw new Exception("Doslo je do greske pri pretrazi projekata.");
                    } else {
                        so.setOdgovor(listaProjekataPretraga);
                    }
                    break;
                case Operacije.UCITAJ_PROJEKAT:
                    Projekat projekatZaUcitavanje = mapper.convertValue(kz.getParametar(), Projekat.class);
                    Projekat ucitaniProjekat = ServerKontroler.getInstanca().ucitajProjekat(projekatZaUcitavanje);
                    if(ucitaniProjekat == null){
                        throw new Exception("Sistem ne može da učita projekat.");
                    } else {
                        so.setOdgovor(ucitaniProjekat);
                    }
                    break;
                case Operacije.IZMENI_PODATKE_PROJEKTA:
                    Projekat izmenjeniProjekat = mapper.convertValue(kz.getParametar(), Projekat.class);
                    boolean uspesnoIzmenjenProjekat = ServerKontroler.getInstanca().izmeniPodatkeProjekta(izmenjeniProjekat);
                    if(!uspesnoIzmenjenProjekat){
                        throw new Exception("Sistem ne može da zapamti projekat.");
                    }
                    break;
                case Operacije.OBRISI_PROJEKAT:
                    Projekat projekatZaObrisati = mapper.convertValue(kz.getParametar(), Projekat.class);
                    boolean uspesnoObrisanProjekat = ServerKontroler.getInstanca().obrisiProjekat(projekatZaObrisati);
                    if(!uspesnoObrisanProjekat){
                        throw new Exception("Sistem ne može da obriše projekat.");
                    }
                    break;
                case Operacije.VRATI_ANGAZOVANJA_ZAPOSLENOG:
                    Zaposleni zaposleniZaVratitiAngazovanja = mapper.convertValue(kz.getParametar(), Zaposleni.class);
                    LinkedList<Angazovanje> listaAngazovanjaZaZaposlenog = ServerKontroler.getInstanca().vratiAngazovanjaZaZaposlenog(zaposleniZaVratitiAngazovanja);
                    if(listaAngazovanjaZaZaposlenog == null){
                        throw new Exception("Doslo je do greske pri ucitavanju angazovanja za zaposlenog.");
                    } else {
                        so.setOdgovor(listaAngazovanjaZaZaposlenog);
                    }
                    break;
                case Operacije.VRATI_ANGAZOVANJA:
                    LinkedList<Angazovanje> listaAngazovanja = ServerKontroler.getInstanca().vratiAngazovanja();
                    if(listaAngazovanja == null){
                        throw new Exception("Doslo je do greske pri ucitavanju svih angazovanja.");
                    } else {
                        so.setOdgovor(listaAngazovanja);
                    }
                    break;
                default:
                    throw new AssertionError();
            }
        } catch (Exception ex){
            so.setGreska(ex);
        }
        return so;
    }

    public void zatvoriSoket(){
        try {
            if (s != null && !s.isClosed()) {
                administrator = null;
                s.close();
            }
        } catch (Exception ex) {
        }
    }

    public Administrator getAdministrator() {
        return administrator;
    }
    
    
}

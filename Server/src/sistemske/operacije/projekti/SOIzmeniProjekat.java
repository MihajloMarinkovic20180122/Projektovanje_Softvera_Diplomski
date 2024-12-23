/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemske.operacije.projekti;

import domen.Angazovanje;
import domen.OpstiDomenskiObjekat;
import domen.Projekat;
import domen.Stanje;
import domen.Zaposleni;
import java.util.Date;
import java.util.LinkedList;
import sistemske.operacije.SOOpsteIzvrsenje;

/**
 *
 * @author Mihajlo
 */
public class SOIzmeniProjekat extends SOOpsteIzvrsenje{

    @Override
    public boolean proveriOgranicenja(OpstiDomenskiObjekat odo) throws Exception {

        if (odo instanceof Projekat projekat) {
            if (projekat.getNazivProjekta() == null 
                || projekat.getPocetakRealizacije() == null
                || projekat.getRukovodilac() == null
                || projekat.getPrioritet() == null 
                || projekat.getStanje() == null) {
                return false;   
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean izvrsiSO(OpstiDomenskiObjekat odo) throws Exception {
        Projekat izmenjeniProjekat = (Projekat) odo;
        LinkedList<OpstiDomenskiObjekat> listaAngazovanjaOpsta = dbb.vratiSve(new Angazovanje());
        LinkedList<Angazovanje> listaAngazovanja = new LinkedList<>();
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaAngazovanjaOpsta) {
            listaAngazovanja.add((Angazovanje) opstiDomenskiObjekat);
        }
        LinkedList<Angazovanje> listaAngazovanjaZaProjekat = new LinkedList<>();
        for (Angazovanje angazovanje1 : listaAngazovanja) {
            if(angazovanje1.getProjekat().getProjekatId() == izmenjeniProjekat.getProjekatId()){
                listaAngazovanjaZaProjekat.add(angazovanje1);
            }
        }
        boolean projekatIzmenjen = dbb.izmeni(odo);
        boolean angazovanjaIzmenjena = true;
        if(projekatIzmenjen){
            LinkedList<Zaposleni> listaZaposlenihNaProjektu = new LinkedList<>();
                for (Angazovanje angazovanje : listaAngazovanjaZaProjekat) {
                    Zaposleni z = new Zaposleni();
                    z.setZaposleniId(angazovanje.getZaposleni().getZaposleniId());
                    z.setIme(angazovanje.getZaposleni().getIme());
                    z.setPrezime(angazovanje.getZaposleni().getPrezime());
                    z.setEmail(angazovanje.getZaposleni().getEmail());
                    z.setDatumZaposlenja(angazovanje.getZaposleni().getDatumZaposlenja());
                    z.setOrganizacionaCelina(angazovanje.getZaposleni().getOrganizacionaCelina());
                    z.setRadnoMesto(angazovanje.getZaposleni().getRadnoMesto());
                    if(izmenjeniProjekat.getStanje() == Stanje.Otkazan || izmenjeniProjekat.getStanje() == Stanje.Realizovan){
                        dbb.izmeni(angazovanje);
                    }
                    if(!izmenjeniProjekat.getZaposleni().contains(z)){
                        angazovanjaIzmenjena = dbb.obrisi(angazovanje);
                    }else{
                        listaZaposlenihNaProjektu.add(z);
                    }
            }
                for (Zaposleni zaposleni : izmenjeniProjekat.getZaposleni()) {
                    if(!listaZaposlenihNaProjektu.contains(zaposleni)){
                        Angazovanje angazovanje = new Angazovanje();
                        angazovanje.setAngazovanjeId(0);
                        angazovanje.setProjekat(izmenjeniProjekat);
                        angazovanje.setZaposleni(zaposleni);
                        angazovanje.setPocetakAngazovanja(izmenjeniProjekat.getPocetakRealizacije());
                        if(izmenjeniProjekat.getStanje() == Stanje.Otkazan || izmenjeniProjekat.getStanje() == Stanje.Realizovan){
                            angazovanje.setKrajAngazovanja(new Date());
                        }
                        int angazovanjeId = dbb.zapamti(angazovanje);
                        if (angazovanjeId > 0) {
                            angazovanjaIzmenjena = true;
                        }
                    }
                
            }
          
        }
        return projekatIzmenjen && angazovanjaIzmenjena;
        
    }
    
}

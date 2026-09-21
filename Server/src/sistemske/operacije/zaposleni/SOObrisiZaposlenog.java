/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemske.operacije.zaposleni;

import domen.Angazovanje;
import domen.OpstiDomenskiObjekat;
import domen.Projekat;
import domen.RadnoMesto;
import domen.Zaposleni;
import java.util.LinkedList;
import sistemske.operacije.SOOpsteIzvrsenje;

/**
 *
 * @author Mihajlo
 */
public class SOObrisiZaposlenog extends SOOpsteIzvrsenje{

    @Override
    public boolean proveriOgranicenja(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof Zaposleni zaposleni) {
            if (zaposleni.getIme() == null 
                || zaposleni.getPrezime() == null
                || zaposleni.getEmail() == null
                || zaposleni.getDatumZaposlenja() == null 
                || zaposleni.getOrganizacionaCelina() == null
                || zaposleni.getRadnoMesto()== null) {
                return false;   
            }
            LinkedList<OpstiDomenskiObjekat> listaZaposlenih = dbb.vratiSve(odo);
            if (!listaZaposlenih.contains(odo)) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean izvrsiSO(OpstiDomenskiObjekat odo) throws Exception {
        Zaposleni zaposleniZaObrisati = (Zaposleni) odo;
        //boolean obrisanZaposleni = dbb.obrisi(odo);
        
        LinkedList<OpstiDomenskiObjekat> listaAngazovanjaOpsta = dbb.vratiSve(new Angazovanje());
        LinkedList<Angazovanje> listaAngazovanja = new LinkedList<>();
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaAngazovanjaOpsta) {
            listaAngazovanja.add((Angazovanje) opstiDomenskiObjekat);
        }
        LinkedList<Angazovanje> listaAngazovanjaZaZaposlenog = new LinkedList<>();
        for (Angazovanje angazovanje1 : listaAngazovanja) {
            if(angazovanje1.getZaposleni().getZaposleniId()== zaposleniZaObrisati.getZaposleniId() && !angazovanje1.getDaLiJeObrisan()){
                listaAngazovanjaZaZaposlenog.add(angazovanje1);
            }
        }
        
        LinkedList<OpstiDomenskiObjekat> listaProjekataOpsta = dbb.vratiSve(new Projekat());
        LinkedList<Projekat> listaProjekata = new LinkedList<>();
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaProjekataOpsta) {
            listaProjekata.add((Projekat) opstiDomenskiObjekat);
        }
        LinkedList<Projekat> listaProjekataRukovodilac = new LinkedList<>();
        for (Projekat projekat1 : listaProjekata) {
            if(projekat1.getRukovodilac().getZaposleniId() == zaposleniZaObrisati.getZaposleniId() && !projekat1.getDaLiJeObrisan()){
                listaProjekataRukovodilac.add(projekat1);
            }
        }
        
        if(listaAngazovanjaZaZaposlenog.isEmpty() && listaProjekataRukovodilac.isEmpty()){
            zaposleniZaObrisati.setDaLiJeObrisan(true);
            boolean obrisanZaposleni = dbb.izmeni(odo);
            RadnoMesto rm = zaposleniZaObrisati.getRadnoMesto();
            rm.setBrojZaposlenih(rm.getBrojZaposlenih() - 1);
            dbb.izmeni(rm);

            return obrisanZaposleni;
        } else{
            return false;
        }
        
        
    }
    
}

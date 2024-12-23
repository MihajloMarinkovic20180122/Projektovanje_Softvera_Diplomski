/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemske.operacije.projekti;

import domen.Angazovanje;
import domen.OpstiDomenskiObjekat;
import domen.Projekat;
import java.util.LinkedList;
import sistemske.operacije.SOOpsteIzvrsenje;

/**
 *
 * @author Mihajlo
 */
public class SOObrisiProjekat extends SOOpsteIzvrsenje{

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
        Projekat projekatZaBrisanje = (Projekat) odo;

        boolean angazovanjaObrisana = true;
        
        LinkedList<OpstiDomenskiObjekat> listaAngazovanjaOpsta = dbb.vratiSve(new Angazovanje());
        LinkedList<Angazovanje> listaAngazovanja = new LinkedList<>();
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaAngazovanjaOpsta) {
            listaAngazovanja.add((Angazovanje) opstiDomenskiObjekat);
        }
        LinkedList<Angazovanje> listaAngazovanjaZaProjekat = new LinkedList<>();
        for (Angazovanje angazovanje1 : listaAngazovanja) {
            if(angazovanje1.getProjekat().getProjekatId() == projekatZaBrisanje.getProjekatId()){
                listaAngazovanjaZaProjekat.add(angazovanje1);
            }
        }
        
        for (Angazovanje angazovanje : listaAngazovanjaZaProjekat) {
            angazovanjaObrisana = dbb.obrisi(angazovanje);
        }
        boolean projekatObrisan = dbb.obrisi(odo);
        
        return projekatObrisan && angazovanjaObrisana;
    }
    
}

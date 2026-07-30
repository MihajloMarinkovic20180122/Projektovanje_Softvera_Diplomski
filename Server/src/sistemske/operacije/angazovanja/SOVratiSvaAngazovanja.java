package sistemske.operacije.angazovanja;

import domen.Angazovanje;
import domen.OpstiDomenskiObjekat;
import java.util.LinkedList;
import sistemske.operacije.SOOpsteIzvrsenje;

/**
 *
 * @author Mihajlo
 */
public class SOVratiSvaAngazovanja extends SOOpsteIzvrsenje{
    private LinkedList<OpstiDomenskiObjekat> lista;
    private LinkedList<Angazovanje> listaAngazovanja;
    
    @Override
    public boolean proveriOgranicenja(OpstiDomenskiObjekat odo) throws Exception {
        return odo instanceof Angazovanje;
    }

    @Override
    public boolean izvrsiSO(OpstiDomenskiObjekat odo) throws Exception {
        boolean signal = false;
        
        try {
            lista = dbb.vratiSve(new Angazovanje());
            signal = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return signal;
    }

    public LinkedList<Angazovanje> getLista() {
        listaAngazovanja = new LinkedList<>();
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
            listaAngazovanja.add((Angazovanje) opstiDomenskiObjekat);
        }
        return listaAngazovanja;
    }
}

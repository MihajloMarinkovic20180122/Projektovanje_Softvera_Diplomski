/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import java.net.Socket;
import java.util.LinkedList;
import konstante.Konstante;
import strategijaTransfera.KlijentskaStrategijaFactory;
import strategijaTransfera.KlijentskaStrategijaKomunikacije;
import transfer.KlijentskiZahtev;
/**
 *
 * @author Mihajlo
 */
public class OpstiKlijentskiKontroler {

    protected Socket s;
    int brojPorta = Konstante.PORT_SERVERA;
    String adresa = Konstante.ADRESA_SERVERA;
    protected KlijentskaStrategijaKomunikacije strategija;
    
    public OpstiKlijentskiKontroler() throws Exception {
        s = new Socket(adresa, brojPorta);
        String format = transfer.Transfer.getFormat();
        strategija = KlijentskaStrategijaFactory.kreirajKlijentskuStrategiju(s, format);
    }
    
    protected <T> T posaljiZahtev(int operacija, Object parametar, Class<T> klasaOdgovora) throws Exception {

        KlijentskiZahtev kz = new KlijentskiZahtev(operacija, parametar);
        return strategija.posaljiZahtev(kz, klasaOdgovora);
    }

    protected <T> LinkedList<T> posaljiZahtevZaListu(int operacija, Object parametar, Class<T> klasaElementa) throws Exception {

        KlijentskiZahtev kz = new KlijentskiZahtev(operacija, parametar);
        return strategija.posaljiZahtevZaListu(kz, klasaElementa);
    }
    
}

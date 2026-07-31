/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategijaTransfera;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Mihajlo
 */
public class ObjektnaKlijentskaStrategija implements KlijentskaStrategijaKomunikacije{

    private final ObjectOutputStream oos;
    private final ObjectInputStream ois;

    public ObjektnaKlijentskaStrategija(Socket s) throws Exception {
        oos = new ObjectOutputStream(s.getOutputStream());
        oos.flush();

        ois = new ObjectInputStream(s.getInputStream());
    }

    @Override
    public <T> T posaljiZahtev(KlijentskiZahtev kz, Class<T> klasaOdgovora) throws Exception {

        oos.writeObject(kz);
        oos.flush();

        ServerskiOdgovor so = (ServerskiOdgovor) ois.readObject();

        if (so.getGreska() != null) {
            throw so.getGreska();
        }

        if (so.getOdgovor() == null) {
            return null;
        }

        return klasaOdgovora.cast(so.getOdgovor());
    }

    @Override
    public <T> LinkedList<T> posaljiZahtevZaListu(KlijentskiZahtev kz, Class<T> klasaElementa) throws Exception {

        oos.writeObject(kz);
        oos.flush();

        ServerskiOdgovor so = (ServerskiOdgovor) ois.readObject();

        if (so.getGreska() != null) {
            throw so.getGreska();
        }

        if (so.getOdgovor() == null) {
            return new LinkedList<>();
        }

        return (LinkedList<T>) so.getOdgovor();
    }
    
}

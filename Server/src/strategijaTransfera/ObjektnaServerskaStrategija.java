/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategijaTransfera;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Mihajlo
 */
public class ObjektnaServerskaStrategija implements ServerskaStrategijaKomunikacije {

    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public ObjektnaServerskaStrategija(Socket s) throws Exception {
        oos = new ObjectOutputStream(s.getOutputStream());
        oos.flush();

        ois = new ObjectInputStream(s.getInputStream());
    }

    @Override
    public KlijentskiZahtev primiZahtev() throws Exception {

        KlijentskiZahtev kz = (KlijentskiZahtev) ois.readObject();

        return kz;
    }

    @Override
    public void posaljiOdgovor(ServerskiOdgovor so) throws Exception {

        oos.writeObject(so);
        oos.flush();
    }
}

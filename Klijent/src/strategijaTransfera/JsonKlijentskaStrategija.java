/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategijaTransfera;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Mihajlo
 */
public class JsonKlijentskaStrategija implements KlijentskaStrategijaKomunikacije{

    private final DataInputStream dis;
    private final DataOutputStream dos;
    private final ObjectMapper mapper;

    public JsonKlijentskaStrategija(Socket s) throws Exception {
        this.dis = new DataInputStream(s.getInputStream());
        this.dos = new DataOutputStream(s.getOutputStream());
        this.mapper = new ObjectMapper();
    }

    @Override
    public <T> T posaljiZahtev(KlijentskiZahtev kz, Class<T> klasaOdgovora) throws Exception {

        String json = mapper.writeValueAsString(kz);

        dos.writeUTF(json);
        dos.flush();

        String odgovorJson = dis.readUTF();

        ServerskiOdgovor so = mapper.readValue(odgovorJson,ServerskiOdgovor.class);

        if (so.getGreska() != null) {
            throw so.getGreska();
        }

        if (so.getOdgovor() == null) {
            return null;
        }

        return mapper.convertValue(so.getOdgovor(),klasaOdgovora);
    }

    @Override
    public <T> LinkedList<T> posaljiZahtevZaListu(KlijentskiZahtev kz, Class<T> klasaElementa) throws Exception {

        String json = mapper.writeValueAsString(kz);

        dos.writeUTF(json);
        dos.flush();

        String odgovorJson = dis.readUTF();

        ServerskiOdgovor so = mapper.readValue(odgovorJson, ServerskiOdgovor.class);

        if (so.getGreska() != null) {
            throw so.getGreska();
        }

        if (so.getOdgovor() == null) {
            return new LinkedList<>();
        }

        return mapper.convertValue(so.getOdgovor(),
                                 mapper.getTypeFactory().constructCollectionType(LinkedList.class,klasaElementa));
    }
}

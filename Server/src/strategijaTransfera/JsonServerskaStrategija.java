/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategijaTransfera;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Mihajlo
 */
public class JsonServerskaStrategija implements ServerskaStrategijaKomunikacije{

    private final DataInputStream dis;
    private final DataOutputStream dos;
    private final ObjectMapper mapper;

    public JsonServerskaStrategija(Socket s) throws Exception {
        this.dis = new DataInputStream(s.getInputStream());
        this.dos = new DataOutputStream(s.getOutputStream());
        this.mapper = new ObjectMapper();
    }

    @Override
    public KlijentskiZahtev primiZahtev() throws Exception {

        String json = dis.readUTF();

        KlijentskiZahtev kz = mapper.readValue(json, KlijentskiZahtev.class);

        return kz;
    }

    @Override
    public void posaljiOdgovor(ServerskiOdgovor so) throws Exception {

        String odgovorJson = mapper.writeValueAsString(so);

        dos.writeUTF(odgovorJson);
        dos.flush();
    }
}


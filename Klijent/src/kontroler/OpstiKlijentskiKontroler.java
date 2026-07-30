/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import com.fasterxml.jackson.databind.ObjectMapper;
import domen.Administrator;
import forme.GlavnaForma;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import konstante.Konstante;
import sesija.Sesija;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Mihajlo
 */
public class OpstiKlijentskiKontroler {

    protected Socket s;
    int brojPorta = Konstante.PORT_SERVERA;
    String adresa = Konstante.ADRESA_SERVERA;
    
    public OpstiKlijentskiKontroler() throws Exception {
        s = new Socket(adresa, brojPorta);
    }
    
    protected Object posaljiZahtev(int operacija, Object parametar) throws Exception{
      
            KlijentskiZahtev kz = new KlijentskiZahtev(operacija, parametar);
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(kz);

            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            ServerskiOdgovor so = (ServerskiOdgovor) ois.readObject();
            if(so.getGreska() != null){
                throw so.getGreska();
            } else {
              return so.getOdgovor();
            }      
    }
    
    protected Object posaljiZahtevJSON(int operacija, Object parametar, Class klasa) throws Exception{
            ObjectMapper mapper = new ObjectMapper();
            KlijentskiZahtev kz = new KlijentskiZahtev(operacija, parametar);
            String json = mapper.writeValueAsString(kz);
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF(json);
            dos.flush();

            DataInputStream dis = new DataInputStream(s.getInputStream());
            String odgovorJson = dis.readUTF();
            ServerskiOdgovor so = mapper.readValue(odgovorJson, ServerskiOdgovor.class);

            if(so.getGreska() != null){
                throw so.getGreska();
            } else {
                return mapper.convertValue(so.getOdgovor(), klasa);
            }
    }
    
    protected Object posaljiZahtevZaListuJSON(int operacija, Object parametar, Class klasa) throws Exception {
            ObjectMapper mapper = new ObjectMapper();
            KlijentskiZahtev kz = new KlijentskiZahtev(operacija, parametar);
            String json = mapper.writeValueAsString(kz);
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF(json);
            dos.flush();

            DataInputStream dis = new DataInputStream(s.getInputStream());
            String odgovorJson = dis.readUTF();
            ServerskiOdgovor so = mapper.readValue(odgovorJson, ServerskiOdgovor.class);

            if (so.getGreska() != null) {
                throw so.getGreska();
            }

            return mapper.convertValue(
                so.getOdgovor(), 
                mapper.getTypeFactory().constructCollectionType(LinkedList.class, klasa)
            );
    }
    
}

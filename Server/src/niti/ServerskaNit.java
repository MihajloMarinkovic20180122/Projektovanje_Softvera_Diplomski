/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domen.Administrator;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.Konstante;

/**
 *
 * @author Mihajlo
 */
public class ServerskaNit extends Thread{

    ServerSocket ss;
    int maxBrojAdministratora;
    LinkedList<ObradaKlijentskihZahtevaNit> listaKlijentskihNiti;

    public ServerskaNit(int maxBrojAdministratora) throws IOException {
        Properties parametriServera = new Properties();
        FileInputStream fis = new FileInputStream(Konstante.LOKACIJA_PARAMETARA_SERVERA);
        parametriServera.load(fis);
        int portServera = Integer.parseInt(parametriServera.getProperty(Konstante.PORT_SERVERA_KEY));
        this.ss = new ServerSocket(portServera);
        this.maxBrojAdministratora = maxBrojAdministratora;
        this.listaKlijentskihNiti = new LinkedList<>();
    }
    
    @Override
    public void run() {
        while (ss != null && !ss.isClosed()) {            
            try {
                Socket s = ss.accept();
                ObradaKlijentskihZahtevaNit obradaKlijentskihZahtevaNit = new ObradaKlijentskihZahtevaNit(s, this);
                obradaKlijentskihZahtevaNit.start();
                listaKlijentskihNiti.add(obradaKlijentskihZahtevaNit);
            } catch (SocketException e) {
                System.out.println("Server je zaustavljen.");
                break;
            } catch (IOException ex) {
                Logger.getLogger(ServerskaNit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public int brojUlogovanih(){
        int br = 0;
        for(ObradaKlijentskihZahtevaNit klijentskaNit: listaKlijentskihNiti){
            if(klijentskaNit.getAdministrator()!=null){
                br++;
            }
        }
        return br;
    }
    
    public void mozeDaSeUloguje(Administrator administratorPokusaj) throws Exception{
        for(ObradaKlijentskihZahtevaNit klijentskaNit: listaKlijentskihNiti){
            if(klijentskaNit.getAdministrator()!=null && klijentskaNit.getAdministrator().getEmail().equalsIgnoreCase(administratorPokusaj.getEmail())){
                throw new Exception("Administrator " + klijentskaNit.getAdministrator() + " je vec prijavljen.");
            }
        }
        int brojUlogovanih = brojUlogovanih();
        if(brojUlogovanih >= maxBrojAdministratora){
            throw new Exception("Postignut je maksimalni broj prijavljenih administatora.");
        }
    }
    
    public LinkedList<Administrator> vratiUlogovane(){
        LinkedList<Administrator> ulogovani = new LinkedList<>();
        for(ObradaKlijentskihZahtevaNit klijentskaNit: listaKlijentskihNiti){
            if(klijentskaNit.getAdministrator()!= null){
                ulogovani.add(klijentskaNit.getAdministrator());
            }
        }
        return ulogovani;
    }
    
    public boolean izlogujAdministratora(Administrator administrator) throws Exception {
        for(ObradaKlijentskihZahtevaNit klijentskaNit: listaKlijentskihNiti){
            if(klijentskaNit.getAdministrator()!=null && klijentskaNit.getAdministrator().equals(administrator)){
                klijentskaNit.zatvoriSoket();
                return true;
            }
        }
        throw new Exception("Administrator je vec odjavljen.");
    }
    
    public void zaustaviServer() {
        try {
            for (ObradaKlijentskihZahtevaNit klijentskaNit : listaKlijentskihNiti) {
                klijentskaNit.zatvoriSoket();
            }

            if (ss != null && !ss.isClosed()) {
                ss.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void izlogujSveAdministratore() {
        for(ObradaKlijentskihZahtevaNit klijentskaNit: listaKlijentskihNiti){
            klijentskaNit.zatvoriSoket();
        }
    }
    
    
}

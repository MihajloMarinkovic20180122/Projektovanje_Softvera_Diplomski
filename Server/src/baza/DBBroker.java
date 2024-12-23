/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import domen.Administrator;
import domen.Angazovanje;
import domen.OpstiDomenskiObjekat;
import domen.OrganizacionaCelina;
import domen.Prioritet;
import domen.Projekat;
import domen.RadnoMesto;
import domen.Stanje;
import domen.Zaposleni;
import java.io.FileInputStream;
import java.sql.*;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.Konstante;

/**
 *
 * @author Mihajlo
 */
public class DBBroker {

    Connection konekcija;
    String url;
    String user;
    String password;

    public DBBroker() {
        Properties parametriBaze = new Properties();
        try {
            FileInputStream fis = new FileInputStream(Konstante.LOKACIJA_PARAMETARA_BAZE);
            parametriBaze.load(fis);
            url = parametriBaze.getProperty(Konstante.LOKACIJA_BAZE_KEY);;
            user = parametriBaze.getProperty(Konstante.USERNAME_BAZA_KEY);
            password = parametriBaze.getProperty(Konstante.PASSWORD_BAZA_KEY);
        } catch (Exception ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void otvoriKonekciju() throws SQLException {
        if (konekcija == null || konekcija.isClosed()) {
            konekcija = DriverManager.getConnection(url, user, password);
            konekcija.setAutoCommit(false);
        }
        System.out.println("Konekcija je otvorena");
    }

    public void potvrdiTransakciju() throws SQLException {
        konekcija.commit();
        System.out.println("Commit");
    }

    public void odbaciTransakciju() throws SQLException {
        konekcija.rollback();
        System.out.println("Rollback");
    }

    public void zatvoriKonekciju() throws SQLException {
        konekcija.close();
        System.out.println("Konekcija je zatvorena");
    }
    
    public LinkedList<OpstiDomenskiObjekat> vratiSve(OpstiDomenskiObjekat odo) throws SQLException {
        //throw new Exception("greska.");
        String upit = "SELECT * FROM " + odo.vratiNazivTabele() + " " + odo.alijas()
                + " " + odo.join() + " ORDER BY 1";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ResultSet rs = ps.executeQuery();
        return (LinkedList<OpstiDomenskiObjekat>) odo.vratiSve(rs);
    }
    
    public LinkedList<OpstiDomenskiObjekat> ucitaj(OpstiDomenskiObjekat odo) throws SQLException {
        //throw new Exception("greska.");
        String upit = "SELECT * FROM " + odo.vratiNazivTabele() + " " + odo.alijas()
                + " " + odo.join() + " " + odo.uslov() + " ORDER BY 1";
        System.out.println(upit);
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ResultSet rs = ps.executeQuery();
        return (LinkedList<OpstiDomenskiObjekat>) odo.vratiSve(rs);
    }
    
    public int zapamti(OpstiDomenskiObjekat odo) throws SQLException {
        //throw new Exception("greska.");
        String upit = "INSERT INTO " + odo.vratiNazivTabele() + " "
                + odo.vratiNaziveKolonaTabele()+ " VALUES(" + odo.vratiVrednostiZaKreiranje() + ")";
        PreparedStatement ps = konekcija.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        return tableKeys.getInt(1);
    }
    
    public boolean obrisi(OpstiDomenskiObjekat odo) throws SQLException {
        //throw new Exception("greska.");
        String upit = "DELETE FROM " + odo.vratiNazivTabele()
                + " WHERE " + odo.vratiPrimarniKljuc();
        PreparedStatement ps = konekcija.prepareStatement(upit);
        int brojObrisanihRedova = ps.executeUpdate();
        return brojObrisanihRedova > 0;
    }
    
    public LinkedList<OpstiDomenskiObjekat> vratiZaVrednost(OpstiDomenskiObjekat objekat) throws SQLException {
        String upit = "SELECT * FROM " + objekat.vratiNazivTabele() + " " + objekat.alijas()
                + " " + objekat.join() + " " + objekat.uslovZaPretragu() + " ORDER BY 1";
        System.out.println(upit);
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ResultSet rs = ps.executeQuery(upit);
        return (LinkedList<OpstiDomenskiObjekat>) objekat.vratiSve(rs);
    }
    
    public boolean izmeni(OpstiDomenskiObjekat odo) throws SQLException {
        //throw new Exception("greska.");
        String upit = "UPDATE " + odo.vratiNazivTabele() + " SET "
                + odo.vratiVrednostiZaIzmenu()+ " WHERE " + odo.vratiPrimarniKljuc();
        System.out.println(upit);
        PreparedStatement ps = konekcija.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        int brojIzmenjenihRedova = ps.executeUpdate();
        return brojIzmenjenihRedova > 0;
    }
    
}

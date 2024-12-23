/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.List;
import java.sql.*;
import java.util.LinkedList;

/**
 *
 * @author Mihajlo
 */
public interface OpstiDomenskiObjekat extends Serializable{
    
    String vratiNazivPrimarnogKljuca();
    
    String vratiPrimarniKljuc();
    
    String vratiNazivTabele();
    
    String vratiNaziveKolonaTabele();
    
    String vratiVrednostiZaKreiranje();
    
    String vratiVrednostiZaIzmenu();
    
    String alijas();
    
    String join();
    
    String uslov();
    
    String uslovZaPretragu();
    
    List<OpstiDomenskiObjekat> vratiSve(ResultSet rs) throws SQLException;
}

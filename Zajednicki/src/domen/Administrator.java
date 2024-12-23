/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Mihajlo
 */
public class Administrator implements OpstiDomenskiObjekat{
    private int administratorId;
    private String email;
    private String lozinka;

    public Administrator() {
    }

    public Administrator(int administratorId, String email, String lozinka) {
        this.administratorId = administratorId;
        this.email = email;
        this.lozinka = lozinka;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public int getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(int administratorId) {
        this.administratorId = administratorId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return email;
    }

    @Override
    public String vratiNazivPrimarnogKljuca() {
        return "administratorId";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "administratorId = " + administratorId;
    }

    @Override
    public String vratiNazivTabele() {
        return "administrator";
    }

    @Override
    public String vratiNaziveKolonaTabele() {
        return "administratorId, email, lozinka";
    }

    @Override
    public String vratiVrednostiZaKreiranje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String alijas() {
        return "ad";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public String uslov() {
        return "";
    }

    @Override
    public String uslovZaPretragu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiSve(ResultSet rs) throws SQLException {
        LinkedList<OpstiDomenskiObjekat> listaAdministratora = new LinkedList<>();
        while (rs.next()) {            
            Administrator ad = new Administrator();
            ad.setAdministratorId(rs.getInt("administratorId"));
            ad.setEmail(rs.getString("email"));
            ad.setLozinka(rs.getString("lozinka"));
            listaAdministratora.add(ad);
        }

        return listaAdministratora;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Administrator other = (Administrator) obj;
        return Objects.equals(this.email, other.email);
    }

   
    
    
    
}

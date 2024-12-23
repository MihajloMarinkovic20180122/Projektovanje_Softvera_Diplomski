/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Mihajlo
 */
public class OrganizacionaCelina implements OpstiDomenskiObjekat{
    private int organizacionaCelinaId;
    private String nazivOrganizacioneCeline;

    public OrganizacionaCelina() {
    }

    public OrganizacionaCelina(int organizacionaCelinaId, String nazivOrganizacioneCeline) {
        this.organizacionaCelinaId = organizacionaCelinaId;
        this.nazivOrganizacioneCeline = nazivOrganizacioneCeline;
    }

    public String getNazivOrganizacioneCeline() {
        return nazivOrganizacioneCeline;
    }

    public void setNazivOrganizacioneCeline(String nazivOrganizacioneCeline) {
        this.nazivOrganizacioneCeline = nazivOrganizacioneCeline;
    }

    public int getOrganizacionaCelinaId() {
        return organizacionaCelinaId;
    }

    public void setOrganizacionaCelinaId(int organizacionaCelinaId) {
        this.organizacionaCelinaId = organizacionaCelinaId;
    }

    @Override
    public String toString() {
        return nazivOrganizacioneCeline;
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
        final OrganizacionaCelina other = (OrganizacionaCelina) obj;
        return this.organizacionaCelinaId == other.organizacionaCelinaId;
    }
    
    @Override
    public String vratiNazivPrimarnogKljuca() {
        return "organizacionaCelinaId";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "organizacionaCelinaId=" + organizacionaCelinaId;
    }

    @Override
    public String vratiNazivTabele() {
        return "organizacionacelina";
    }

    @Override
    public String vratiNaziveKolonaTabele() {
        return "(`organizacionaCelinaId`, `nazivOrganizacioneCeline`)";
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
        return "oc";
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
        LinkedList<OpstiDomenskiObjekat> listaOrganizacionihCelina = new LinkedList<>();
        while (rs.next()) {            
            OrganizacionaCelina oc = new OrganizacionaCelina();
            oc.setOrganizacionaCelinaId(rs.getInt("organizacionaCelinaId"));
            oc.setNazivOrganizacioneCeline(rs.getString("nazivOrganizacioneCeline"));
            listaOrganizacionihCelina.add(oc);
        }
        
        rs.close();
        return listaOrganizacionihCelina;
    }
    
    
    
    
}

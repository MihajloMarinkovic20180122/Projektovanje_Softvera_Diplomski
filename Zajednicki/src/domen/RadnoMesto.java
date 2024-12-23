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
public class RadnoMesto implements OpstiDomenskiObjekat{
    
    private OrganizacionaCelina organizacionaCelina;
    private int radnoMestoId;
    private String nazivRadnogMesta;
    private int brojZaposlenih;

    public RadnoMesto() {
    }

    public RadnoMesto(OrganizacionaCelina organizacionaCelina, int radnoMestoId, String nazivRadnogMesta, int brojZaposlenih) {
        this.organizacionaCelina = organizacionaCelina;
        this.radnoMestoId = radnoMestoId;
        this.nazivRadnogMesta = nazivRadnogMesta;
        this.brojZaposlenih = brojZaposlenih;
    }

    public OrganizacionaCelina getOrganizacionaCelina() {
        return organizacionaCelina;
    }

    public void setOrganizacionaCelina(OrganizacionaCelina organizacionaCelina) {
        this.organizacionaCelina = organizacionaCelina;
    }

    public int getRadnoMestoId() {
        return radnoMestoId;
    }

    public void setRadnoMestoId(int radnoMestoId) {
        this.radnoMestoId = radnoMestoId;
    }

    public String getNazivRadnogMesta() {
        return nazivRadnogMesta;
    }

    public void setNazivRadnogMesta(String nazivRadnogMesta) {
        this.nazivRadnogMesta = nazivRadnogMesta;
    }

    public int getBrojZaposlenih() {
        return brojZaposlenih;
    }

    public void setBrojZaposlenih(int brojZaposlenih) {
        this.brojZaposlenih = brojZaposlenih;
    }    

    @Override
    public String toString() {
        return nazivRadnogMesta;
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
        final RadnoMesto other = (RadnoMesto) obj;
        return this.radnoMestoId == other.radnoMestoId;
    }

    @Override
    public String vratiNazivPrimarnogKljuca() {
        return "organizacionaCelinaId, radnoMestoId";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "radnoMestoId=" + radnoMestoId;
    }

    @Override
    public String vratiNazivTabele() {
        return "radnomesto";
    }

    @Override
    public String vratiNaziveKolonaTabele() {
         return "(`organizacionaCelinaId`, `radnoMestoId`, `nazivRadnogMesta`, `brojZaposlenih`)";
    }

    @Override
    public String vratiVrednostiZaKreiranje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "brojZaposlenih=" + brojZaposlenih;
    }

    @Override
    public String alijas() {
        return "rm";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public String uslov() {
        return "WHERE organizacionaCelinaId ='" + organizacionaCelina.getOrganizacionaCelinaId() + "'";
    }

    @Override
    public String uslovZaPretragu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiSve(ResultSet rs) throws SQLException {
        LinkedList<OpstiDomenskiObjekat> listaRadnihMesta = new LinkedList<>();
        //zasto nema rs.getInt("organizacionaCelinaId"), 
        while (rs.next()) {

            OrganizacionaCelina organizacionaCelina = new OrganizacionaCelina();
            int organizacionaCelinaRM = rs.getInt("organizacionaCelinaId");
            organizacionaCelina.setOrganizacionaCelinaId(organizacionaCelinaRM);

            int radnoMestoId = rs.getInt("radnoMestoId");
            String nazivRadnogMesta = rs.getString("nazivRadnogMesta");
            int brojZaposlenih = rs.getInt("brojZaposlenih");

            RadnoMesto radnoMesto = new RadnoMesto(organizacionaCelina, radnoMestoId, nazivRadnogMesta, brojZaposlenih);
            listaRadnihMesta.add(radnoMesto);
        }

        return listaRadnihMesta;
    }

    

    

    
    
    
    
}

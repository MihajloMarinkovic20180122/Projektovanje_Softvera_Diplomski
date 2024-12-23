/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Mihajlo
 */
public class Zaposleni implements OpstiDomenskiObjekat{
    
    private int zaposleniId;
    private String ime;
    private String prezime;
    private String email;
    private Date datumZaposlenja;
    private OrganizacionaCelina organizacionaCelina;
    private RadnoMesto radnoMesto;
    String vrednostZaPretragu;

    public Zaposleni() {
    }

    public Zaposleni(int zaposleniId, String ime, String prezime, String email, Date datumZaposlenja, OrganizacionaCelina organizacionaCelina, RadnoMesto radnoMesto, String vrednostZaPretragu) {
        this.zaposleniId = zaposleniId;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.datumZaposlenja = datumZaposlenja;
        this.organizacionaCelina = organizacionaCelina;
        this.radnoMesto = radnoMesto;
        this.vrednostZaPretragu = vrednostZaPretragu;
    }

    public int getZaposleniId() {
        return zaposleniId;
    }

    public void setZaposleniId(int zaposleniId) {
        this.zaposleniId = zaposleniId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDatumZaposlenja() {
        return datumZaposlenja;
    }

    public void setDatumZaposlenja(Date datumZaposlenja) {
        this.datumZaposlenja = datumZaposlenja;
    }

    public OrganizacionaCelina getOrganizacionaCelina() {
        return organizacionaCelina;
    }

    public void setOrganizacionaCelina(OrganizacionaCelina organizacionaCelina) {
        this.organizacionaCelina = organizacionaCelina;
    }

    public RadnoMesto getRadnoMesto() {
        return radnoMesto;
    }

    public void setRadnoMesto(RadnoMesto radnoMesto) {
        this.radnoMesto = radnoMesto;
    }

    public String getVrednostZaPretragu() {
        return vrednostZaPretragu;
    }

    public void setVrednostZaPretragu(String vrednostZaPretragu) {
        this.vrednostZaPretragu = vrednostZaPretragu;
    }
    
    @Override
    public String toString() {
        return ime + " " + prezime;
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
        final Zaposleni other = (Zaposleni) obj;
        return Objects.equals(this.email, other.email);
    }
    
    @Override
    public String vratiNazivPrimarnogKljuca() {
        return "zaposleniId";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "zaposleniId = " + zaposleniId;
    }

    @Override
    public String vratiNazivTabele() {
        return "zaposleni";
    }

    @Override
    public String vratiNaziveKolonaTabele() {
        return "(ime, prezime, email, datumZaposlenja, organizacionaCelinaId, radnoMestoId)";
    }

    @Override
    public String vratiVrednostiZaKreiranje() {
        return "'" + ime + "','" + prezime + "','" + email + "','" + new java.sql.Date(datumZaposlenja.getTime()) + "'," + organizacionaCelina.getOrganizacionaCelinaId() + "," + radnoMesto.getRadnoMestoId();
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='" + ime + "', prezime='" + prezime + "', email='" + email + "', datumZaposlenja='" + new java.sql.Date(datumZaposlenja.getTime()) + "', organizacionaCelinaId=" + organizacionaCelina.getOrganizacionaCelinaId() + ", radnoMestoId=" + radnoMesto.getRadnoMestoId();
    }

    @Override
    public String alijas() {
        return "z";
    }

    @Override
    public String join() {
        return "JOIN organizacionaCelina oc ON z.organizacionaCelinaId = oc.organizacionaCelinaId "
             + "JOIN radnoMesto rm ON z.radnoMestoId = rm.radnoMestoId";
    }

    @Override
    public String uslov() {
        return "WHERE z.zaposleniId = "+zaposleniId;
    }

    @Override
    public String uslovZaPretragu() {
        return "WHERE z.ime LIKE'%" + this.vrednostZaPretragu +
               "%' OR z.prezime LIKE'%" + this.vrednostZaPretragu +
               "%' OR z.email LIKE'%" + this.vrednostZaPretragu +
               "%' OR oc.nazivOrganizacioneCeline LIKE'%" + this.vrednostZaPretragu +
               "%' OR rm.nazivRadnogMesta LIKE'%" + this.vrednostZaPretragu +
               "%'";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiSve(ResultSet rs) throws SQLException {
        LinkedList<OpstiDomenskiObjekat> listaZaposlenih = new LinkedList<>();
        while (rs.next()) {            
            Zaposleni z = new Zaposleni();
            z.setZaposleniId(rs.getInt("zaposleniId"));
            z.setIme(rs.getString("ime"));
            z.setPrezime(rs.getString("prezime"));
            z.setEmail(rs.getString("email"));
            z.setDatumZaposlenja(rs.getDate("datumZaposlenja"));

            OrganizacionaCelina oc = new OrganizacionaCelina();
            oc.setOrganizacionaCelinaId(rs.getInt("organizacionaCelinaId"));
            oc.setNazivOrganizacioneCeline(rs.getString("nazivOrganizacioneCeline"));
            z.setOrganizacionaCelina(oc);

            RadnoMesto rm = new RadnoMesto();
            rm.setRadnoMestoId(rs.getInt("radnoMestoId"));
            rm.setNazivRadnogMesta(rs.getString("nazivRadnogMesta"));
            rm.setBrojZaposlenih(rs.getInt("brojZaposlenih"));
            z.setRadnoMesto(rm);

            listaZaposlenih.add(z);
        }
        
        rs.close();
        return listaZaposlenih;
    }
    
}

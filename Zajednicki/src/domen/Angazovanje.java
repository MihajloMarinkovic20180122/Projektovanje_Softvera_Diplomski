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

/**
 *
 * @author Mihajlo
 */
public class Angazovanje implements OpstiDomenskiObjekat{
    private int angazovanjeId;
    private Projekat projekat;
    private Zaposleni zaposleni;
    private Date pocetakAngazovanja;
    private Date krajAngazovanja;

    public Angazovanje() {
    }

    public Angazovanje(int angazovanjeId, Projekat projekat, Zaposleni zaposleni, Date pocetakAngazovanja, Date krajAngazovanja) {
        this.angazovanjeId = angazovanjeId;
        this.projekat = projekat;
        this.zaposleni = zaposleni;
        this.pocetakAngazovanja = pocetakAngazovanja;
        this.krajAngazovanja = krajAngazovanja;
    }

    public Date getKrajAngazovanja() {
        return krajAngazovanja;
    }

    public void setKrajAngazovanja(Date krajAngazovanja) {
        this.krajAngazovanja = krajAngazovanja;
    }

    public int getAngazovanjeId() {
        return angazovanjeId;
    }

    public void setAngazovanjeId(int angazovanjeId) {
        this.angazovanjeId = angazovanjeId;
    }

    public Projekat getProjekat() {
        return projekat;
    }

    public void setProjekat(Projekat projekat) {
        this.projekat = projekat;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Date getPocetakAngazovanja() {
        return pocetakAngazovanja;
    }

    public void setPocetakAngazovanja(Date pocetakAngazovanja) {
        this.pocetakAngazovanja = pocetakAngazovanja;
    }

    @Override
    public String vratiNazivPrimarnogKljuca() {
        return "angazovanjeId";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "angazovanjeId=" + angazovanjeId;
    }

    @Override
    public String vratiNazivTabele() {
        return "angazovanje";
    }

    @Override
    public String vratiNaziveKolonaTabele() {
        return "(zaposleniId, projekatId, pocetakAngazovanja, krajAngazovanja)";
    }

    @Override
    public String vratiVrednostiZaKreiranje() {
        if(krajAngazovanja != null){
            return "" + zaposleni.getZaposleniId() + "," + projekat.getProjekatId() + ",'" + new java.sql.Date(pocetakAngazovanja.getTime()) + "','" + new java.sql.Date(krajAngazovanja.getTime()) + "'";
        } else {
            return "" + zaposleni.getZaposleniId() + "," + projekat.getProjekatId() + ",'" + new java.sql.Date(pocetakAngazovanja.getTime()) + "'," + null;
        }
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "krajAngazovanja='" + new java.sql.Date(new Date().getTime()) + "'";
    }

    @Override
    public String alijas() {
        return "a";
    }

    @Override
    public String join() {
        return "JOIN zaposleni z ON a.zaposleniId = z.zaposleniId "
             + "JOIN organizacionacelina oc ON z.organizacionaCelinaId = oc.organizacionaCelinaId "
             + "JOIN radnomesto rm ON z.radnoMestoId = rm.radnoMestoId "
             + "JOIN projekat p ON a.projekatId = p.projekatId";
    }

    @Override
    public String uslov() {
        return "WHERE a.projekatId = " + projekat.getProjekatId();
    }

    @Override
    public String uslovZaPretragu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiSve(ResultSet rs) throws SQLException {
        LinkedList<OpstiDomenskiObjekat> listaAngazovanja = new LinkedList<>();
        while (rs.next()) {            
            Angazovanje a = new Angazovanje();
            a.setAngazovanjeId(rs.getInt("angazovanjeId"));

            Projekat p = new Projekat();
                p.setProjekatId(rs.getInt("projekatId"));
                p.setNazivProjekta(rs.getString("nazivProjekta"));
                p.setPocetakRealizacije(rs.getDate("pocetakRealizacije"));
                p.setPrioritet(Prioritet.valueOf(rs.getString("prioritet")));
                p.setStanje(Stanje.valueOf(rs.getString("stanje")));

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
                z.setRadnoMesto(rm);

                p.setRukovodilac(z);


            a.setProjekat(p);

            Zaposleni z1 = new Zaposleni();
                z1.setZaposleniId(rs.getInt("zaposleniId"));
                z1.setIme(rs.getString("ime"));
                z1.setPrezime(rs.getString("prezime"));
                z1.setEmail(rs.getString("email"));
                z1.setDatumZaposlenja(rs.getDate("datumZaposlenja"));

                OrganizacionaCelina oc1 = new OrganizacionaCelina();
                oc1.setOrganizacionaCelinaId(rs.getInt("organizacionaCelinaId"));
                oc1.setNazivOrganizacioneCeline(rs.getString("nazivOrganizacioneCeline"));
                z1.setOrganizacionaCelina(oc);

                RadnoMesto rm1 = new RadnoMesto();
                rm1.setRadnoMestoId(rs.getInt("radnoMestoId"));
                rm1.setNazivRadnogMesta(rs.getString("nazivRadnogMesta"));
                z1.setRadnoMesto(rm);

            a.setZaposleni(z1);

            a.setPocetakAngazovanja(rs.getDate("pocetakAngazovanja"));
            a.setKrajAngazovanja(rs.getDate("krajAngazovanja"));

            listaAngazovanja.add(a);
        }
        
        rs.close();
        return  listaAngazovanja;
    }
    
    
}

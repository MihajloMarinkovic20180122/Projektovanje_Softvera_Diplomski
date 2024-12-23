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
public class Projekat implements OpstiDomenskiObjekat{
    private int projekatId;
    private String nazivProjekta;
    private Date pocetakRealizacije;
    private Zaposleni rukovodilac;
    private Prioritet prioritet;
    private Stanje stanje;
    private LinkedList<Zaposleni> zaposleni;
    String vrednostZaPretragu;

    public Projekat() {
    }

    public Projekat(int projekatId, String nazivProjekta, Date pocetakRealizacije, Zaposleni rukovodilac, Prioritet prioritet, Stanje stanje, LinkedList<Zaposleni> zaposleni, String vrednostZaPretragu) {
        this.projekatId = projekatId;
        this.nazivProjekta = nazivProjekta;
        this.pocetakRealizacije = pocetakRealizacije;
        this.rukovodilac = rukovodilac;
        this.prioritet = prioritet;
        this.stanje = stanje;
        this.zaposleni = zaposleni;
        this.vrednostZaPretragu = vrednostZaPretragu;
    }

    public LinkedList<Zaposleni> getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(LinkedList<Zaposleni> zaposleni) {
        this.zaposleni = zaposleni;
    }

    public int getProjekatId() {
        return projekatId;
    }

    public void setProjekatId(int projekatId) {
        this.projekatId = projekatId;
    }

    public String getNazivProjekta() {
        return nazivProjekta;
    }

    public void setNazivProjekta(String nazivProjekta) {
        this.nazivProjekta = nazivProjekta;
    }

    public Date getPocetakRealizacije() {
        return pocetakRealizacije;
    }

    public void setPocetakRealizacije(Date pocetakRealizacije) {
        this.pocetakRealizacije = pocetakRealizacije;
    }

    public Zaposleni getRukovodilac() {
        return rukovodilac;
    }

    public void setRukovodilac(Zaposleni rukovodilac) {
        this.rukovodilac = rukovodilac;
    }

    public Prioritet getPrioritet() {
        return prioritet;
    }

    public void setPrioritet(Prioritet prioritet) {
        this.prioritet = prioritet;
    }

    public Stanje getStanje() {
        return stanje;
    }

    public void setStanje(Stanje stanje) {
        this.stanje = stanje;
    }

    public String getVrednostZaPretragu() {
        return vrednostZaPretragu;
    }

    public void setVrednostZaPretragu(String vrednostZaPretragu) {
        this.vrednostZaPretragu = vrednostZaPretragu;
    }

    @Override
    public String toString() {
        return nazivProjekta;
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
        final Projekat other = (Projekat) obj;
        return this.projekatId == other.projekatId;
    }
    
    @Override
    public String vratiNazivPrimarnogKljuca() {
        return "projekatId";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "projekatId=" + projekatId;
    }

    @Override
    public String vratiNazivTabele() {
        return "projekat";
    }

    //fali za listu
    @Override
    public String vratiNaziveKolonaTabele() {
        return "(nazivProjekta, pocetakRealizacije, rukovodilacId, prioritet, stanje)";
    }

    @Override
    public String vratiVrednostiZaKreiranje() {
        return "'" + nazivProjekta + "','" + new java.sql.Date(pocetakRealizacije.getTime()) + "'," + rukovodilac.getZaposleniId() + ",'" + prioritet + "','" + stanje + "'";
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "nazivProjekta='" + nazivProjekta + "', pocetakRealizacije='" + new java.sql.Date(pocetakRealizacije.getTime()) + "', rukovodilacId=" + rukovodilac.getZaposleniId() + ", prioritet='" + prioritet + "', stanje='" + stanje + "'";
    }

    @Override
    public String alijas() {
        return "p";
    }

    @Override
    public String join() {
        return "JOIN zaposleni z ON p.rukovodilacId = z.zaposleniID "
             + "JOIN organizacionacelina oc ON z.organizacionaCelinaId = oc.organizacionaCelinaId "
             + "JOIN radnomesto rm ON z.radnoMestoId = rm.radnoMestoId";
    }

    @Override
    public String uslov() {
        return "WHERE p.projekatId = "+projekatId;
    }

    @Override
    public String uslovZaPretragu() {
        return "WHERE p.nazivProjekta LIKE'%" + this.vrednostZaPretragu +
               "%' OR z.ime LIKE'%" + this.vrednostZaPretragu +
               "%' OR z.prezime LIKE'%" + this.vrednostZaPretragu +
               "%' OR p.prioritet LIKE'%" + this.vrednostZaPretragu +
               "%' OR p.stanje LIKE'%" + this.vrednostZaPretragu +
               "%'";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiSve(ResultSet rs) throws SQLException {
        LinkedList<OpstiDomenskiObjekat> listaProjekata = new LinkedList<>();
        while (rs.next()) {            
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
            
            listaProjekata.add(p);
        }
        
        rs.close();
        return listaProjekata;
    }

    

    

    
    
    
    
}

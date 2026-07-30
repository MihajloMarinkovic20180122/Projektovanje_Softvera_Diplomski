package sistemske.operacije.zaposleni;

import domen.OrganizacionaCelina;
import domen.Projekat;
import domen.RadnoMesto;
import domen.Zaposleni;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Mihajlo
 */
public class SODodajZaposlenogTest {
    private SODodajZaposlenog soDodajZaposlenog;
    private Zaposleni zaposleni;

    @Before
    public void pripremiPodatke() {
        soDodajZaposlenog = new SODodajZaposlenog();
        zaposleni = new Zaposleni();
        zaposleni.setIme("Petar");
        zaposleni.setPrezime("Petrovic");
        zaposleni.setEmail("pera@gmail.com");

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date datum = sdf.parse("17.07.2026");
            zaposleni.setDatumZaposlenja(datum);
        } catch (Exception ex) {
            Logger.getLogger(SODodajZaposlenogTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        OrganizacionaCelina oc = new OrganizacionaCelina();
        oc.setOrganizacionaCelinaId(1);

        RadnoMesto rm = new RadnoMesto();
        rm.setRadnoMestoId(1);
        rm.setOrganizacionaCelina(oc);

        zaposleni.setOrganizacionaCelina(oc);
        zaposleni.setRadnoMesto(rm);
    }

    @After
    public void ocistiPodatke() throws Exception {

        soDodajZaposlenog.dbb.otvoriKonekciju();
        List listaZaposlenih = soDodajZaposlenog.dbb.vratiSve(new Zaposleni());
        int trazeniIndeks = -1;
        for (int i = 0; i < listaZaposlenih.size(); i++) {
            Zaposleni z = (Zaposleni) listaZaposlenih.get(i);
            if ("pera@gmail.com".equals(z.getEmail())) {
                trazeniIndeks = i;
            }
        }
        if(trazeniIndeks != -1){
            Zaposleni dodatiZaposleni = (Zaposleni) listaZaposlenih.get(trazeniIndeks);
            zaposleni.setZaposleniId(dodatiZaposleni.getZaposleniId());
            soDodajZaposlenog.dbb.obrisi(zaposleni);
        }
        soDodajZaposlenog.dbb.potvrdiTransakciju();
        soDodajZaposlenog.dbb.zatvoriKonekciju();
    }

    @Test
    public void testValidniPodaci() throws Exception {

        assertEquals(true, soDodajZaposlenog.sOOpsteIzvrsenje(zaposleni));
    }

    @Test
    public void testPogresnaKlasa() throws Exception {
        
        Projekat projekat = new Projekat();
        assertEquals(false, soDodajZaposlenog.sOOpsteIzvrsenje(projekat));
    }

    @Test
    public void testNedostajeIme() throws Exception {

        zaposleni.setIme(null);
        assertEquals(false,soDodajZaposlenog.sOOpsteIzvrsenje(zaposleni));
    }

    @Test
    public void testNedostajePrezime() throws Exception {

        zaposleni.setPrezime(null);
        assertEquals(false,soDodajZaposlenog.sOOpsteIzvrsenje(zaposleni));
    }

    @Test
    public void testNedostajeEmail() throws Exception {

        zaposleni.setEmail(null);
        assertEquals(false,soDodajZaposlenog.sOOpsteIzvrsenje(zaposleni));
    }

    @Test
    public void testNedostajeDatumZaposlenja() throws Exception {

        zaposleni.setDatumZaposlenja(null);
        assertEquals(false,soDodajZaposlenog.sOOpsteIzvrsenje(zaposleni));
    }

    @Test
    public void testNedostajeOrganizacionaCelina() throws Exception {

        zaposleni.setOrganizacionaCelina(null);
        assertEquals(false, soDodajZaposlenog.sOOpsteIzvrsenje(zaposleni));
    }

    @Test
    public void testNedostajeRadnoMesto() throws Exception {

        zaposleni.setRadnoMesto(null);
        assertEquals(false,soDodajZaposlenog.sOOpsteIzvrsenje(zaposleni));
    }
}

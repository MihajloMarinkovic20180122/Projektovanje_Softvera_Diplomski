/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Mihajlo
 */
public class ServerskiOdgovor implements Serializable{
    private Object odgovor;
    private Exception greska;
    private String poruka;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(Object odgovor, Exception greska, String poruka) {
        this.odgovor = odgovor;
        this.greska = greska;
        this.poruka = poruka;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public Exception getGreska() {
        return greska;
    }

    public void setGreska(Exception greska) {
        this.greska = greska;
    }

}

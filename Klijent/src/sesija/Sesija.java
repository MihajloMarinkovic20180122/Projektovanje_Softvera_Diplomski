/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesija;

import domen.Administrator;

/**
 *
 * @author Mihajlo
 */
public class Sesija {
    private static Sesija instanca;
    private Administrator ulogovani;

    private Sesija() {
    }

    public static Sesija getInstanca() {
        if(instanca == null){
            instanca = new Sesija();
        }
        return instanca;
    }

    public Administrator getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Administrator ulogovani) {
        this.ulogovani = ulogovani;
    }
    
}

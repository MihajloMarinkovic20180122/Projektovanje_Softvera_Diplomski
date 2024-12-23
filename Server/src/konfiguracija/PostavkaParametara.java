/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.FileOutputStream;
import java.util.Properties;
import konstante.Konstante;


/**
 *
 * @author Mihajlo
 */
public class PostavkaParametara {

    public static void main(String[] args) {
        {
            postaviParametreBaze();
            postaviParametreServera();
        }

    }

    private static void postaviParametreBaze() {
        Properties prop = new Properties();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(Konstante.LOKACIJA_PARAMETARA_BAZE);
            prop.setProperty(Konstante.LOKACIJA_BAZE_KEY, "jdbc:mysql://localhost:3306/projektovanje_softvera_seminarski");
            prop.setProperty(Konstante.USERNAME_BAZA_KEY, "root");
            prop.setProperty(Konstante.PASSWORD_BAZA_KEY, "");
            prop.store(fos, null);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static void postaviParametreServera() {
        Properties prop = new Properties();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(Konstante.LOKACIJA_PARAMETARA_SERVERA);
            prop.setProperty(Konstante.PORT_SERVERA_KEY, "10001");
            prop.setProperty(Konstante.MAX_BROJ_ADMINISTRATORA, "2");
            prop.store(fos, null);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}

    

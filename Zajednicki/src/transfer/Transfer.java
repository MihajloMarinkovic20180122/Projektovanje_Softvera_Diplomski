/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 *
 * @author Mihajlo
 */
public class Transfer {
    
    private static String koristiJSON;

    static {
        Properties parametriTransfera = new Properties();
        
        try (InputStream is = Transfer.class.getClassLoader().getResourceAsStream("transfer/transfer.properties")) {
            if (is != null) {
                parametriTransfera.load(is);
                koristiJSON = parametriTransfera.getProperty("koristiJSON");
            } else {
                System.err.println("Fajl transfer.properties nije pronađen u paketu!");
                koristiJSON = "false";
            }
        } catch (IOException e) {
            System.err.println("Greška pri čitanju transfer.properties fajla!");
            e.printStackTrace();
            koristiJSON = "false"; 
        }
    }
    
    public static String getKoristiJSON() {
        return koristiJSON;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Mihajlo
 */
public class Transfer {
    
    private static String format;

    static {
        Properties parametriTransfera = new Properties();
        
        try (InputStream is = Transfer.class.getClassLoader().getResourceAsStream("transfer/transfer.properties")) {
            if (is != null) {
                parametriTransfera.load(is);
                format = parametriTransfera.getProperty("format");
            } else {
                System.err.println("Fajl transfer.properties nije pronađen u paketu!");
                format = "false";
            }
        } catch (IOException e) {
            System.err.println("Greška pri čitanju transfer.properties fajla!");
            e.printStackTrace();
            format = "false"; 
        }
    }
    
    public static String getFormat() {
        return format;
    }
    
}

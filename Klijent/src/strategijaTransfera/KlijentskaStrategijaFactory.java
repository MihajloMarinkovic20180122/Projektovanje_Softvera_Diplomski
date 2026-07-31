/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategijaTransfera;

import java.net.Socket;

/**
 *
 * @author Mihajlo
 */
public class KlijentskaStrategijaFactory {
    private KlijentskaStrategijaFactory() {
    }

    public static KlijentskaStrategijaKomunikacije kreirajKlijentskuStrategiju(Socket s, String format) throws Exception {

        switch (format) {
            case "json":
                return new JsonKlijentskaStrategija(s);
            case "object":
                return new ObjektnaKlijentskaStrategija(s);
            default:
                throw new IllegalArgumentException("Nepoznat format komunikacije: " + format);
        }
        
    }
}

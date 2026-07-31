/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package strategijaTransfera;

import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Mihajlo
 */
public interface ServerskaStrategijaKomunikacije {
    KlijentskiZahtev primiZahtev() throws Exception;

    void posaljiOdgovor(ServerskiOdgovor so) throws Exception;
}

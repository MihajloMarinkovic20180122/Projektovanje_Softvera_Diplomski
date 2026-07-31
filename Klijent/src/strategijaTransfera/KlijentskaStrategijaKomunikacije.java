/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package strategijaTransfera;

import java.util.LinkedList;
import transfer.KlijentskiZahtev;

/**
 *
 * @author Mihajlo
 */
public interface KlijentskaStrategijaKomunikacije {
    <T> T posaljiZahtev(KlijentskiZahtev kz, Class<T> klasaOdgovora) throws Exception;

    <T> LinkedList<T> posaljiZahtevZaListu(KlijentskiZahtev kz, Class<T> klasaElementa) throws Exception;
}

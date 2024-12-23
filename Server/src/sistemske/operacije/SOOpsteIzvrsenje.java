/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemske.operacije;

import baza.DBBroker;
import domen.OpstiDomenskiObjekat;
import java.sql.SQLException;

/**
 *
 * @author Mihajlo
 */
public abstract class SOOpsteIzvrsenje {
    public DBBroker dbb = new DBBroker();
    synchronized public boolean sOOpsteIzvrsenje(OpstiDomenskiObjekat odo) throws SQLException{
        boolean signal = false;
        try {
            dbb.otvoriKonekciju();
            boolean ogranicenjaIspunjena = proveriOgranicenja(odo);
            if (ogranicenjaIspunjena) {
                signal = izvrsiSO(odo);
                if (signal == true) {
                    dbb.potvrdiTransakciju();
                }
            }
            return signal;
        } catch (Exception ex) {
            dbb.odbaciTransakciju();
        } finally {
            dbb.zatvoriKonekciju();
        }
        return signal;
    }

    abstract public boolean proveriOgranicenja(OpstiDomenskiObjekat odo) throws Exception;

    abstract public boolean izvrsiSO(OpstiDomenskiObjekat odo) throws Exception;

    
}

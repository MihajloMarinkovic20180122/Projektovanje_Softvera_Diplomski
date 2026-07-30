package lokalizacija;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Mihajlo
 */
public class JezikMenadzer {
    
    private static String odabir;
    
    private static ResourceBundle bundle =
            ResourceBundle.getBundle("lokalizacija.jezik", Locale.of("sr"));

    public static void setLocale(Locale locale) {
        bundle = ResourceBundle.getBundle("lokalizacija.jezik", locale);
    }

    public static String get(String key) {
        return bundle.getString(key);
    }

    public static String getOdabir() {
        return odabir;
    }

    public static void setOdabir(String odabir) {
        JezikMenadzer.odabir = odabir;
    }    
    
}

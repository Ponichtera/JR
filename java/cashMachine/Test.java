package cashMachine;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.ResourceBundle;

public class Test {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        Path path = Paths.get("cashMachine.resources.");
//         final String RESOURCE_PATH = "java.cashMachine.resources.";
         ResourceBundle validCreditCards =  ResourceBundle.getBundle(path + "verifiedCards.properties" );
       // D:\Java\src\main\java\cashMachine\resources\verifiedCards.properties
        //cashMachine/resources/verifiedCards.properties
    }
}

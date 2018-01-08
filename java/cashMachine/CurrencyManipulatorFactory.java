package cashMachine;



import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {
    private CurrencyManipulatorFactory() {}

    private static Map<String, CurrencyManipulator> currencys = new HashMap<>();

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
            if (!currencys.containsKey(currencyCode))
            currencys.put(currencyCode, new CurrencyManipulator(currencyCode));
        return currencys.get(currencyCode);
    }
    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return currencys.values();
    }
}

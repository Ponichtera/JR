package cashMachine.command;


import cashMachine.ConsoleHelper;
import cashMachine._CashMachineLauncher;
import cashMachine.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards =  ResourceBundle.getBundle(_CashMachineLauncher.RESOURCE_PATH + "verifiedCards" );
    private ResourceBundle res = ResourceBundle.getBundle(_CashMachineLauncher.RESOURCE_PATH + "login_en", Locale.ENGLISH);

    @Override
    public void execute() throws InterruptOperationException {

       ConsoleHelper.writeMessage(res.getString("before"));

        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String card = ConsoleHelper.readString();
            String pin = ConsoleHelper.readString();

            if (validCreditCards.containsKey(card)) {
                if (validCreditCards.getString(card).equals(pin)) {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), card));

                    break;
                } else {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), card));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                    continue;
                }
            } else {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), card));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            }
        }
    }
}

package cashMachine;


import cashMachine.command.CommandExecutor;
import cashMachine.exception.InterruptOperationException;

import java.util.Locale;

public class _CashMachineLauncher {

    public final static String RESOURCE_PATH = _CashMachineLauncher.class.getPackage().getName() + ".resources.";

        public static void main (String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        Operation operation;
        try {
        CommandExecutor.execute(Operation.LOGIN);
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT );
        } catch (InterruptOperationException e) {
            ConsoleHelper.printExitMessage();
        }
            ConsoleHelper.printExitMessage();
    }
}

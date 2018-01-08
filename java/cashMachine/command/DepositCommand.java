package cashMachine.command;


import cashMachine.ConsoleHelper;
import cashMachine.CurrencyManipulator;
import cashMachine.CurrencyManipulatorFactory;
import cashMachine._CashMachineLauncher;
import cashMachine.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {

    private ResourceBundle res = ResourceBundle.getBundle(_CashMachineLauncher.RESOURCE_PATH +
            "deposit_en");

    @Override
    public void execute() throws InterruptOperationException {

        ConsoleHelper.writeMessage(res.getString("before"));

        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory
                .getManipulatorByCurrencyCode(currencyCode);
        String[] denominationAndAmount = ConsoleHelper.getValidTwoDigits(currencyCode);
        int denomination = 0;
        int amount = 0;
        try {
            denomination = Integer.parseInt(denominationAndAmount[0]);
            amount = Integer.parseInt(denominationAndAmount[1]);
        } catch (NumberFormatException e) {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }

        currencyManipulator.addAmount(denomination, amount);
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), denomination*amount, currencyCode));
    }
}

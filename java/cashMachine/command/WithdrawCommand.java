package cashMachine.command;


import cashMachine.ConsoleHelper;
import cashMachine.CurrencyManipulator;
import cashMachine.CurrencyManipulatorFactory;
import cashMachine._CashMachineLauncher;
import cashMachine.exception.InterruptOperationException;
import cashMachine.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command{

    private ResourceBundle res = ResourceBundle.getBundle(_CashMachineLauncher.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {

        ConsoleHelper.writeMessage(res.getString("before"));

        String currencyCode     = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator cm  = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        int expectedAmount;
        Map<Integer, Integer> withdrawAmount;

        while (true) {
            ConsoleHelper.writeMessage("specify.amount");

            try {
                expectedAmount = Integer.parseInt(ConsoleHelper.readString());
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }

            if (cm.isAmountAvailable(expectedAmount))
                break;
            else ConsoleHelper.writeMessage(res.getString("not.enough.money"));
        }
            try {
               withdrawAmount = cm.withdrawAmount(expectedAmount);
               ConsoleHelper.writeMessage(String.format(res.getString("success.format"), expectedAmount));
               for (Map.Entry<Integer, Integer> pair : withdrawAmount.entrySet()) {
                   ConsoleHelper.writeMessage("\t" + pair.getKey() + " - " + pair.getValue() );
               }
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }

        }
    }


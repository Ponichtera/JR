package cashMachine.command;


import cashMachine.ConsoleHelper;
import cashMachine.CurrencyManipulator;
import cashMachine.CurrencyManipulatorFactory;
import cashMachine._CashMachineLauncher;

import java.util.ResourceBundle;

class InfoCommand implements Command{

    private ResourceBundle res =  ResourceBundle.getBundle(_CashMachineLauncher.RESOURCE_PATH + "info_en" );


    @Override
    public void execute() {
        boolean moneyAvailable = false;

        ConsoleHelper.writeMessage(res.getString("before"));
        for (CurrencyManipulator entry : CurrencyManipulatorFactory.getAllCurrencyManipulators())
            if (entry.hasMoney()) {
                if (entry.getTotalAmount() > 0) {
                    ConsoleHelper.writeMessage(entry.getCurrencyCode() + " - " + entry.getTotalAmount());
                    moneyAvailable = true;
                }
            }

        if (!moneyAvailable)
            ConsoleHelper.writeMessage(res.getString("no.money"));
    }
 }

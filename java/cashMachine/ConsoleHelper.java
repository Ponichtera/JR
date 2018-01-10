package cashMachine;


import cashMachine.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(_CashMachineLauncher.RESOURCE_PATH + "common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }

    public static String readString() throws InterruptOperationException {
        String input = "";

        try {
            input = bis.readLine();

            if (input.equalsIgnoreCase("exit"))
                throw new InterruptOperationException();

        } catch (IOException e) {
            writeMessage(res.getString("invalid.data"));
        }

        return input;
    }

    public static String askCurrencyCode() throws InterruptOperationException {

        String input = "";

        try {
            while (input.length() != 3) {
                writeMessage(res.getString("choose.currency.code"));
                input = bis.readLine();
                if (input.equalsIgnoreCase("exit"))
                    throw new InterruptOperationException();
            }
        } catch (IOException e) {
            writeMessage(res.getString("invalid.data"));
        }

        return input.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException{

        String input;
        String[] money;
        int denomination;
        int amount;

        writeMessage(res.getString("choose.denomination.and.count.format"));
        while (true) {
            try {
                input = bis.readLine();
                money = input.split(" ");
                denomination = Integer.parseInt(money[0]);
                amount = Integer.parseInt(money[1]);
            } catch (Exception e) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            if (denomination <= 0 || amount <= 0) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            break;
        }
        return money;
    }

    public static Operation askOperation() throws InterruptOperationException {
        do {
            writeMessage(res.getString("choose.operation"));
            writeMessage("1) " + res.getString("operation.INFO"));
            writeMessage("2) " + res.getString("operation.DEPOSIT"));
            writeMessage("3) " + res.getString("operation.WITHDRAW"));
            writeMessage("4) " + res.getString("operation.EXIT"));

            String input = readString();
            try {
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(input));
            } catch (IllegalArgumentException e) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
        } while (true);
    }

}

package cashMachine.command;


import cashMachine.ConsoleHelper;
import cashMachine._CashMachineLauncher;
import cashMachine.exception.InterruptOperationException;

import java.util.ResourceBundle;

class ExitCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(_CashMachineLauncher.RESOURCE_PATH + "exit_en");
    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));

        if (ConsoleHelper.readString().equals("y"))
            ConsoleHelper.writeMessage(res.getString("thank.message"));
    }
}

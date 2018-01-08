package cashMachine.command;


import cashMachine.Operation;
import cashMachine.exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
private static final Map<Operation, Command> allKnownCommandsMap = new HashMap<>();
   static {
       allKnownCommandsMap.put(Operation.LOGIN,      new LoginCommand());
       allKnownCommandsMap.put(Operation.DEPOSIT,    new DepositCommand());
       allKnownCommandsMap.put(Operation.EXIT,       new ExitCommand());
       allKnownCommandsMap.put(Operation.INFO,       new InfoCommand());
       allKnownCommandsMap.put(Operation.WITHDRAW,   new WithdrawCommand());
   }

   public static final void execute (Operation operation) throws InterruptOperationException {
       if (operation == null ) return;
       if (allKnownCommandsMap.containsKey(operation))
           allKnownCommandsMap.get(operation).execute();
   }

    CommandExecutor() {
    }
}

package cashMachine.command;


import cashMachine.exception.InterruptOperationException;

interface Command {
    public void execute() throws InterruptOperationException;

}

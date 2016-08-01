package main.command;

/**
 * Created by Славик on 17.07.2016.
 */
public enum CommandList {

    DIRECT_USER_INFO(new DirectInfo()),
    SAVE_NEW_INFO(new SaveInfo()),
    FILL_BALANCE(new FillBalance()),
    FOOT_THE_BILL(new FootTheBill()),
    ORDER_SERVICE(new OrderService()),
    SET_SERVICE(new SetService()),
    SHOW_USERS(new ShowSubs()),
    BLOCK_USER(new BlockUser()),
    UNLOCK_USER(new UnlockUser()),
    CREATE_NEW_SUBSCRIBER(new CreateUser()),
    DELETE_SERVICE(new DeleteService()),
    SERVICE_CHANGE(new ServiceChange()),
    SERVICE_REMOVE(new ServiceRemove());

    /**
     * Command object
     */
    private Command command;

    /**
     * Constructor
     * @param command is Command object
     */
    CommandList(Command command) {
        this.command=command;
    }

    /**
     * getter
     * @return Command object
     */
    public Command getCommand() {
            return command;
    }
}

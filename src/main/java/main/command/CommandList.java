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
    SHOW_DEBTORS(new ShowDebtors()),
    BLOCK_USER(new BlockUser()),
    UNLOCK_USER(new UnlockUser()),
            CREATE(new CreateUser());


    private Command command;

    CommandList(Command command) {
        this.command=command;
    }
    public Command getCommand() {
            return command;
    }
}

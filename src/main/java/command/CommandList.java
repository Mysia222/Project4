package command;

/**
 * Created by Славик on 17.07.2016.
 */
public enum CommandList {

    ENTER(new CommandEnter()),
    USER_CREATE(new UserCreate()),
    USER_CABINET(new UserCabinet()),
    USER_ENTER(new UserEnter()),
    USER_DIRECT_SERVICE(new UserDirectService()),
    USER_REMOVE_SERVICE(new UserRemoveService()),
    USER_SET_SERVICE(new UserSetService()),
    USER_FOOT_THE_BILL(new UserFootTheBill()),
    LOGOUT(new UserLogout()),
    USER_DIRECT_INFO(new UserDirectInfo()),


    SHOW_USERS(new ShowSubs()),
    BLOCK_USER(new BlockUser()),
    UNLOCK_USER(new UnlockUser()),
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

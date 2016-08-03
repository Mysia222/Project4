package command;

/**
 * Created by Славик on 17.07.2016.
 */
public enum CommandList {

    USER_CREATE(new UserCreate()),
    USER_CABINET(new UserCabinet()),
    USER_ENTER(new UserEnter()),
    USER_DIRECT_SERVICE(new UserDirectService()),
    USER_REMOVE_SERVICE(new UserRemoveService()),
    USER_SET_SERVICE(new UserSetService()),
    USER_FOOT_THE_BILL(new UserFootTheBill()),
    LOGOUT(new UserLogout()),
    USER_DIRECT_INFO(new UserDirectInfo()),
    SERVICE_SUBSCRIBERS(new ServiceSubscribersList()),
    SERVICE_ADMIN_CABINET(new ServiceAdminCabinet()),
    SERVICE_SUBSCRIBER_BLOCK(new ServiceBlockSubscriber()),
    SERVICE_SUBSCRIBER_UNLOCK(new ServiceUnlockSubscriber()),
    SERVICE_SERVICES(new ServiceServicesList()),
    SERVICE_DELETE_SERVICE(new ServiceDeleteService()),
    SERVICE_CHANGE_SERVICE(new ServiceChangeService()),
    SERVICE_CREATE_NEW(new ServiceCreateNewService()),
    SERVICE_SAVE_SERVICE(new ServiceSaveService());

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

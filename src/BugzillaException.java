import java.util.HashMap;

import com.google.java.contract.Invariant;

@Invariant({
        "true"
})
public class BugzillaException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public enum ErrorType {
        UNKNOWN_ERROR,
        DB_LOAD_ERROR,
        DB_SAVE_ERROR,
        ERROR_MISSING_MESSAGE,
        INVALID_STATE_TRANSITION,

        USERNAME_NULL,
        PASSWORD_NULL,
        LOGIN_FAILED,
        LOGOUT_FAILED,
        USER_ALREADY_REGISTRED,
        INVALID_BUGID,
        USER_ACTION_NOT_PERMITTED,

        TRANSITION_TO_CONFIRMED_STATE_UNSUCCESSFUL,
        TRANSITION_TO_INPROGRESS_STATE_UNSUCCESSFUL,
        TRANSITION_TO_VERIFIED_STATE_UNSUCCESSFUL,

        BUG_WAS_NOT_ASSIGNED_TO_DEVELOPER,
        BUG_IS_STILL_ASSIGNED_TO_DEVELOPER,
        BUG_CANNOT_START_PROGRESS
        //...
    }

    private static void addMessage() {

        msgList.put(ErrorType.UNKNOWN_ERROR, "Unknown error");
        msgList.put(ErrorType.DB_LOAD_ERROR, "Error: Failed to load database");
        msgList.put(ErrorType.DB_SAVE_ERROR, "Error: Failed to save database");
        msgList.put(ErrorType.ERROR_MISSING_MESSAGE, "Error: Missing error message in exception");
        msgList.put(ErrorType.INVALID_STATE_TRANSITION, "Error: Bug state cannot be changed from %s to %s");

        msgList.put(ErrorType.USERNAME_NULL, "Error: Object username is null");
        msgList.put(ErrorType.PASSWORD_NULL, "Error: Object password is null");
        msgList.put(ErrorType.USER_ALREADY_REGISTRED, "User exists with this username");
        msgList.put(ErrorType.INVALID_BUGID, "Error: Invalid bug ID");
        msgList.put(ErrorType.USER_ACTION_NOT_PERMITTED, "User does not have permission for this action");

        msgList.put(ErrorType.LOGOUT_FAILED, "Logout failed");
        msgList.put(ErrorType.LOGIN_FAILED, "Login failed");
        msgList.put(ErrorType.TRANSITION_TO_CONFIRMED_STATE_UNSUCCESSFUL, "Transition to confirmed state was unsuccessful");
        msgList.put(ErrorType.TRANSITION_TO_INPROGRESS_STATE_UNSUCCESSFUL, "Transition to iin progress state was unsuccessful");
        msgList.put(ErrorType.TRANSITION_TO_VERIFIED_STATE_UNSUCCESSFUL, "Transition to verified state was unsuccessful");

        msgList.put(ErrorType.BUG_WAS_NOT_ASSIGNED_TO_DEVELOPER, "Bug was not assigned to developer :(");

        msgList.put(ErrorType.BUG_CANNOT_START_PROGRESS, "Bug cannot start progress");
        msgList.put(ErrorType.BUG_IS_STILL_ASSIGNED_TO_DEVELOPER, "Bug is still assigned to developer");
    }

    public static void init() throws BugzillaException {

        addMessage();

        if (!exInitialized()) {
            throw new BugzillaException(ErrorType.ERROR_MISSING_MESSAGE);
        }
    }

    public static boolean exInitialized() {
        return (msgList.size() == ErrorType.values().length && !msgList.containsValue(null));
    }


    public BugzillaException(ErrorType et) {
        error = et;
        msg = msgList.get(error);
    }

    public ErrorType getError() {
        return error;
    }

    public String getErrorMsg() {
        return msg;
    }

    protected ErrorType error;
    protected String msg;
    protected static HashMap<ErrorType, String> msgList = new HashMap<ErrorType, String>();
}

package lk.ijse.glingler.util;

public class ResponseCode {
    public static final int SUCCESS = 1000;
    public static final int EXCEPTION = 1004;
    public static final int FAILED = 1044;

    public static final int USER_NOT_FOUND = 1005;
    public static final int PROFILE_NOT_FOUND = 1006;

    public static final int INTERNAL_SERVER_ERROR = 500;


    private ResponseCode() {
        throw new IllegalStateException("Response Codes Class");
    }
}

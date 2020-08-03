package lk.ijse.glingler.util;

public class ResponseCode {
    public static final int SUCCESS = 1000;
    public static final int EXCEPTION = 1004;
    public static final int FAILED = 1044;

    private ResponseCode() {
        throw new IllegalStateException("Response Codes Class");
    }
}

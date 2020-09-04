package lk.ijse.glingler.dto;

import lombok.Data;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Data
public class ChatDTO {
    private int chatId;
    private int matchedId;
    private int sendProfileId;
    private String message;
    private long createdTime;
}

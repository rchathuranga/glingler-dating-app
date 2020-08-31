package lk.ijse.glingler.dto;

import lombok.Data;

@Data
public class ChatDTO {
    private int chatId;
    private int matchedId;
    private int sendProfileId;
    private String message;
}

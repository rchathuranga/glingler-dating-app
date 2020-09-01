package lk.ijse.glingler.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChatRequestBean {
    private int userProfileId;
    private int matchProfileId;
    private int matchedId;
    private List<ChatDTO> chats;
}

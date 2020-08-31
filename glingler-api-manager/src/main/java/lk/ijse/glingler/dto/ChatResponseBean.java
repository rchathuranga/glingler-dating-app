package lk.ijse.glingler.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChatResponseBean extends AbstractResponseBean {
    private List<ChatDTO> data;
}

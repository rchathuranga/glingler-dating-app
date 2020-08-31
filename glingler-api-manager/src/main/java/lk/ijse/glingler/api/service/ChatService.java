package lk.ijse.glingler.api.service;

import lk.ijse.glingler.dto.ChatRequestBean;
import lk.ijse.glingler.dto.ChatResponseBean;
import lk.ijse.glingler.dto.ProfileResponseBean;

public interface ChatService {
    public ChatResponseBean getChatProfiles(ChatRequestBean chatRequestBean);
}

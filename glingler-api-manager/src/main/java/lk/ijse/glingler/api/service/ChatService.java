package lk.ijse.glingler.api.service;

import lk.ijse.glingler.dto.ChatRequestBean;
import lk.ijse.glingler.dto.ChatResponseBean;
import lk.ijse.glingler.dto.ProfileResponseBean;

public interface ChatService {
    public ChatResponseBean getChatByProfile(ChatRequestBean chatRequestBean) throws Exception;
    public ChatResponseBean getChatProfile(int userProfileId) throws Exception;
    public ChatResponseBean saveChat(ChatRequestBean chatRequestBean) throws Exception;
}

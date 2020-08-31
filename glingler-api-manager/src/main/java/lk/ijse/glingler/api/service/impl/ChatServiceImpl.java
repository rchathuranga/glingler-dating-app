package lk.ijse.glingler.api.service.impl;

import lk.ijse.glingler.api.repository.ChatRepository;
import lk.ijse.glingler.api.repository.MatchedRepository;
import lk.ijse.glingler.api.service.ChatService;
import lk.ijse.glingler.dto.ChatDTO;
import lk.ijse.glingler.dto.ChatRequestBean;
import lk.ijse.glingler.dto.ChatResponseBean;
import lk.ijse.glingler.dto.ProfileResponseBean;
import lk.ijse.glingler.model.Chat;
import lk.ijse.glingler.model.Matched;
import lk.ijse.glingler.model.Profile;
import lk.ijse.glingler.util.ResponseCode;
import lk.ijse.glingler.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MatchedRepository matchedRepository;

    @Override
    public ChatResponseBean getChatProfiles(ChatRequestBean chatRequestBean) {
        ChatResponseBean responseBean = new ChatResponseBean();
        Profile profile = new Profile();
        Profile matchedProfile = new Profile();
        profile.setProfileId(chatRequestBean.getUserProfileId());
        matchedProfile.setProfileId(chatRequestBean.getMatchProfileId());

        Matched matched = matchedRepository.getAllMatchedByProfileIdOrMatchProfileIdAndProfileIdOrMatchProfileIdAndStatus(profile, matchedProfile, StatusCode.MATCH_REACT_TYPE_MATCHED);
        System.out.println(matched);

        List<Chat> chats = chatRepository.getAllChatsByMatchedId(matched);

        List<ChatDTO> chatDTOS = new ArrayList<>();
        chats.forEach(chat -> {
            ChatDTO chatDTO = new ChatDTO();

            chatDTO.setChatId(chat.getChatId());
            chatDTO.setMatchedId(chat.getMatchedId().getMatchedId());
            chatDTO.setMessage(chat.getMessage());
            chatDTO.setSendProfileId(chat.getChatSendProfileId());

            chatDTOS.add(chatDTO);
        });

        responseBean.setData(chatDTOS);
        responseBean.setResponseCode(ResponseCode.SUCCESS);
        responseBean.setResponseError("");
        return responseBean;
    }
}

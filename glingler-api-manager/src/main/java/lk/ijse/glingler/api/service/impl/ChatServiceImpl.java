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

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MatchedRepository matchedRepository;

    @Override
    public ChatResponseBean getChatByProfile(ChatRequestBean chatRequestBean) throws Exception {
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

    @Override
    public ChatResponseBean getChatProfile(int userProfileId) throws Exception {
        Profile userProfile = new Profile();
        userProfile.setProfileId(userProfileId);

        List<Matched> list = matchedRepository.getAllMatchedByProfileIdOrMatchProfileIdAndStatus(userProfile, userProfile, StatusCode.MATCH_REACT_TYPE_MATCHED);


        return null;
    }

    @Override
    public ChatResponseBean saveChat(ChatRequestBean chatRequestBean) throws Exception{
        ChatResponseBean responseBean = new ChatResponseBean();

        List<Chat> chatList = new ArrayList<>();
        int matchedId = chatRequestBean.getMatchedId();

        List<ChatDTO> chatDTOS = chatRequestBean.getChats();
        for (ChatDTO chatDTO: chatDTOS) {
            Chat chat = new Chat();
            chat.setChatSendProfileId(chatDTO.getSendProfileId());
            Matched matched = new Matched();
            matched.setMatchedId(matchedId);
            chat.setMatchedId(matched);
            chat.setMessage(chatDTO.getMessage());
            chat.setCreatedTime(new Timestamp(chatDTO.getCreatedTime()));
            chatList.add(chat);
        }

        if (chatRepository.saveAll(chatList).size() == chatList.size()) {
            responseBean.setResponseCode(ResponseCode.SUCCESS);
            responseBean.setResponseError("");
        } else {
            responseBean.setResponseCode(ResponseCode.FAILED);
            responseBean.setResponseError("Failed");
        }

        return responseBean;
    }
}

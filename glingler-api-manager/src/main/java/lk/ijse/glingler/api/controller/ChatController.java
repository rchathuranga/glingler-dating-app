package lk.ijse.glingler.api.controller;

import lk.ijse.glingler.api.service.ChatService;
import lk.ijse.glingler.dto.ChatRequestBean;
import lk.ijse.glingler.dto.ChatResponseBean;
import lk.ijse.glingler.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1/{appType}/chat")
@CrossOrigin
public class ChatController {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private ChatService chatService;

    @GetMapping(value = "/msg/{odId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChatResponseBean> getChatProfiles(@PathVariable("appType") String appType,@PathVariable("odId") Integer odId) {
        ChatResponseBean responseBean = new ChatResponseBean();

        Profile userProfile = (Profile) httpServletRequest.getAttribute("userProfiles");
        ChatRequestBean chatRequestBean = new ChatRequestBean();

        chatRequestBean.setUserProfileId(userProfile.getProfileId());
        chatRequestBean.setMatchProfileId(odId);

        responseBean = chatService.getChatProfiles(chatRequestBean);

        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }

}

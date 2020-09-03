package lk.ijse.glingler.api.controller;

import lk.ijse.glingler.api.service.ChatService;
import lk.ijse.glingler.dto.ChatRequestBean;
import lk.ijse.glingler.dto.ChatResponseBean;
import lk.ijse.glingler.model.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/{appType}/chat")
@CrossOrigin
public class ChatController {

    private static final Logger LOGGER = LogManager.getLogger(ChatController.class.getName());

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private ChatService chatService;

    @GetMapping(value = "/msg/{odId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChatResponseBean> getChatProfiles(@PathVariable("appType") String appType, @PathVariable("odId") Integer odId) {
        LOGGER.debug("Enter to Get Chat Profiles in Chat Controller | AppType - {}",appType);
        ChatResponseBean responseBean = new ChatResponseBean();

        Profile userProfile = (Profile) httpServletRequest.getAttribute("userProfiles");
        ChatRequestBean chatRequestBean = new ChatRequestBean();

        chatRequestBean.setUserProfileId(userProfile.getProfileId());
        chatRequestBean.setMatchProfileId(odId);

        try {
            responseBean = chatService.getChatByProfile(chatRequestBean);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }

    @GetMapping(value = "/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChatResponseBean> getChatProfiles(@PathVariable("appType") String appType) {
        ChatResponseBean responseBean = new ChatResponseBean();
        Profile userProfile = (Profile) httpServletRequest.getAttribute("userProfiles");
        try {
            responseBean = chatService.getChatProfile(userProfile.getProfileId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity(responseBean, HttpStatus.OK);
    }

    @PostMapping(value = "/saveChat", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChatResponseBean> saveChats(@PathVariable("appType") String appType, @RequestBody ChatRequestBean chatRequestBean) {
        ChatResponseBean responseBean = new ChatResponseBean();

        responseBean = chatService.saveChat(chatRequestBean);

        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }
}

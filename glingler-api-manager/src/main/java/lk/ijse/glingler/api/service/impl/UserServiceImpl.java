package lk.ijse.glingler.api.service.impl;

import lk.ijse.glingler.dto.UserDTO;
import lk.ijse.glingler.dto.UserResponseBean;
import lk.ijse.glingler.model.CommonUser;
import lk.ijse.glingler.api.repository.UserRepository;
import lk.ijse.glingler.api.service.UserService;
import lk.ijse.glingler.util.ResponseCode;
import lk.ijse.glingler.util.StatusCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LogManager.getLogger(ProfileServiceImpl.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserResponseBean getUserDetailByUserName(String username) throws Exception {
        LOGGER.debug("Enter to Get User Details by Username in User Service | Username : {}", username);
        UserResponseBean responseBean = new UserResponseBean();

        CommonUser commonUser = userRepository.getUserByUsername(username);

        if (commonUser!=null) {
            LOGGER.debug("Loading User Data to DTO");

            UserDTO userDTO = new UserDTO();

            userDTO.setUserId(commonUser.getUserId());
            userDTO.setUsername(commonUser.getUsername());
            userDTO.setStatus(commonUser.getStatus());
            responseBean.setUser(userDTO);

            LOGGER.debug("Process Getting User Details Success");
            responseBean.setResponseCode(ResponseCode.SUCCESS);
            responseBean.setResponseError("");
        }else {

            LOGGER.debug("Process Getting User Details Failed");
            responseBean.setResponseCode(ResponseCode.FAILED);
            responseBean.setResponseError("Process Getting User Details Failed");
        }

        return responseBean;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        GrantedAuthority authority = null;

        CommonUser commonUser = userRepository.getUserByUsername(s);
        if (commonUser != null) {
            if (commonUser.getPasswordStatus().equalsIgnoreCase(StatusCode.STATUS_PASSWORD_ACTIVE)) {
                authority = new SimpleGrantedAuthority("ROLE_ADMIN");
                return new User(commonUser.getUsername(), commonUser.getPassword(), Collections.singletonList(authority));
            } else {
                throw new UsernameNotFoundException("User has been already deactivated by admin");
            }
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}

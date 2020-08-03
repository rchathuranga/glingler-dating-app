package lk.ijse.glingler.service.impl;

import lk.ijse.glingler.dto.UserDTO;
import lk.ijse.glingler.dto.UserResponseBean;
import lk.ijse.glingler.model.CommonUser;
import lk.ijse.glingler.repository.UserRepository;
import lk.ijse.glingler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponseBean getUserDetails() throws Exception {
        CommonUser one = userRepository.getOne(1);

        System.out.println();
        System.out.println("user : " + one);
        System.out.println();

        UserDTO dto = new UserDTO();
        dto.setUserId(one.getUserId());
        dto.setUsername(one.getUsername());

        UserResponseBean responseBean = new UserResponseBean();
        responseBean.setUser(dto);
        System.out.println(responseBean);
        return responseBean;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        GrantedAuthority authority = null;

        CommonUser commonUser = userRepository.getUserByUsername(s);
        System.out.println("commonUser : " + commonUser);
        if (commonUser != null) {
            if (commonUser.getStatus().equalsIgnoreCase("ACT")) {
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

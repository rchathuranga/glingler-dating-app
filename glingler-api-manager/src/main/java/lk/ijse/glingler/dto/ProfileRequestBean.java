package lk.ijse.glingler.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProfileRequestBean {
    private String email;
    private String password;
    private String username;
    private String firstName;
    private String lastName;
    private String bio;
    private String gender;
    private String imageUrl;
}

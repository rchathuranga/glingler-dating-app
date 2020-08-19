package lk.ijse.glingler.dto;

import lombok.Data;

@Data
public class ProfileDTO {
    private String profileId;
    private String firstName;
    private String lastName;
    private String bio;
    private String sex;
    private int age;
    private String birthDay;
    private String imageUrl;
}

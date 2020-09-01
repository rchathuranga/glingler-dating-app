package lk.ijse.glingler.dto;

import lombok.Data;

@Data
public class ProfileDTO {
    private int profileId;
    private String firstName;
    private String lastName;
    private String bio;
    private String sex;
    private int age;
    private String birthDay;
    private String imageUrl;

    private String lookingFor;
    private String location;
    private Integer ageRangeStart;
    private Integer ageRangeEnd;

    private int matchedCount;
    private int matchedIdWithUser;
}

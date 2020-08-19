package lk.ijse.glingler.dto;

import lombok.Data;

@Data
public class SignInResponseBean extends AbstractResponseBean {
    private String token;
    private int userId;
}

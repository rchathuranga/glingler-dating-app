package lk.ijse.glingler.dto;

import lombok.Data;

@Data
public class AbstractResponseBean {
    private int responseCode;
    private String responseError;
}

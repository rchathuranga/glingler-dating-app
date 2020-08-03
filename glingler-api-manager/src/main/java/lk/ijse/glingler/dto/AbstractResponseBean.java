package lk.ijse.glingler.dto;

import lombok.Data;

@Data
public abstract class AbstractResponseBean {
    private int responseCode;
    private String responseError;
}

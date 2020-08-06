package lk.ijse.glingler.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class ExceptionResponseBean extends AbstractResponseBean{
    private String exceptionMessage;
}

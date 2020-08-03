package lk.ijse.glingler.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, includeFieldNames = true)
@Data
public class UserResponseBean extends AbstractResponseBean {
    private UserDTO user;
}

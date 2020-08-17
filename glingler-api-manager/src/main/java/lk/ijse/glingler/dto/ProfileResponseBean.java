package lk.ijse.glingler.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProfileResponseBean extends AbstractResponseBean{

    private int userId;
    private List<ProfileDTO> data;

    @Override
    public String toString() {
        return "ProfileResponseBean{" +
                "data=" + data +
                "} "+ super.toString();
    }
}

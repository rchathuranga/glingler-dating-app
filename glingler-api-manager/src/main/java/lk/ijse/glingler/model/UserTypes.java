package lk.ijse.glingler.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_types")
public class UserTypes {
    private int userTypeId;
    private String userTypeCode;
    private String description;
    private String status;

    @Id
    @Column(name = "user_type_id", nullable = false)
    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    @Basic
    @Column(name = "user_type_code", nullable = true, length = 10)
    public String getUserTypeCode() {
        return userTypeCode;
    }

    public void setUserTypeCode(String userTypeCode) {
        this.userTypeCode = userTypeCode;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 50)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTypes userTypes = (UserTypes) o;
        return userTypeId == userTypes.userTypeId &&
                Objects.equals(userTypeCode, userTypes.userTypeCode) &&
                Objects.equals(description, userTypes.description) &&
                Objects.equals(status, userTypes.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userTypeId, userTypeCode, description, status);
    }
}

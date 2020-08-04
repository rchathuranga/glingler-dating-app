package lk.ijse.glingler.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "system_user_type")
public class SystemUserType {
    private int systemUserTypeId;
    private String systemUserTypeCode;
    private String status;

    @Id
    @Column(name = "system_user_type_id", nullable = false)
    public int getSystemUserTypeId() {
        return systemUserTypeId;
    }

    public void setSystemUserTypeId(int systemUserTypeId) {
        this.systemUserTypeId = systemUserTypeId;
    }

    @Basic
    @Column(name = "system_user_type_code", nullable = true, length = 10)
    public String getSystemUserTypeCode() {
        return systemUserTypeCode;
    }

    public void setSystemUserTypeCode(String systemUserTypeCode) {
        this.systemUserTypeCode = systemUserTypeCode;
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
        SystemUserType that = (SystemUserType) o;
        return systemUserTypeId == that.systemUserTypeId &&
                Objects.equals(systemUserTypeCode, that.systemUserTypeCode) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(systemUserTypeId, systemUserTypeCode, status);
    }
}

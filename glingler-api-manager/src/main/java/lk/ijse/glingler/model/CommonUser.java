package lk.ijse.glingler.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "common_user")
public class CommonUser {
    private int userId;
    private String username;
    private String password;
    private String passwordStatus;
    private Integer loginAttempts;
    private String status;
    private String email;
    private String mobileNo;
    private Byte isVerified;
    private Integer systemUserTypeId;
    private Profile profile;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 50)
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 50)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "password_status", nullable = true, length = 10)
    public String getPasswordStatus() {
        return passwordStatus;
    }
    public void setPasswordStatus(String passwordStatus) {
        this.passwordStatus = passwordStatus;
    }

    @Basic
    @Column(name = "login_attempts", nullable = true)
    public Integer getLoginAttempts() {
        return loginAttempts;
    }
    public void setLoginAttempts(Integer loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 50)
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 50)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "mobile_no", nullable = true, length = 15)
    public String getMobileNo() {
        return mobileNo;
    }
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Basic
    @Column(name = "is_verified", nullable = true)
    public Byte getIsVerified() {
        return isVerified;
    }
    public void setIsVerified(Byte isVerified) {
        this.isVerified = isVerified;
    }

    @Basic
    @Column(name = "system_user_type_id")
    public Integer getSystemUserTypeId() {
        return systemUserTypeId;
    }
    public void setSystemUserTypeId(Integer systemUserTypeId) {
        this.systemUserTypeId = systemUserTypeId;
    }

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    public Profile getProfile() {
        return profile;
    }
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonUser commonUser = (CommonUser) o;
        return userId == commonUser.userId &&
                Objects.equals(username, commonUser.username) &&
                Objects.equals(password, commonUser.password) &&
                Objects.equals(passwordStatus, commonUser.passwordStatus) &&
                Objects.equals(loginAttempts, commonUser.loginAttempts) &&
                Objects.equals(status, commonUser.status) &&
                Objects.equals(email, commonUser.email) &&
                Objects.equals(mobileNo, commonUser.mobileNo) &&
                Objects.equals(isVerified, commonUser.isVerified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, passwordStatus, loginAttempts, status, email, mobileNo, isVerified);
    }

/*    @ManyToOne
    @JoinColumn(name = "system_user_type_id", referencedColumnName = "system_user_type_id")
    public SystemUserType getSystemUserTypeBySystemUserTypeId() {
        return systemUserTypeBySystemUserTypeId;
    }
    public void setSystemUserTypeBySystemUserTypeId(SystemUserType systemUserTypeBySystemUserTypeId) {
        this.systemUserTypeBySystemUserTypeId = systemUserTypeBySystemUserTypeId;
    }*/

    @Override
    public String toString() {
        return "CommonUser{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordStatus='" + passwordStatus + '\'' +
                ", loginAttempts=" + loginAttempts +
                ", status='" + status + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", isVerified=" + isVerified +
                ", systemUserTypeId=" + systemUserTypeId +
                '}';
    }
}

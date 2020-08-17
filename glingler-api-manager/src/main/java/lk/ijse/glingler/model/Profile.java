package lk.ijse.glingler.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "profile")
public class Profile {
    private int profileId;
    private String firstName;
    private String lastName;
    private String bio;
    private String sex;
    private Integer age;
    private Timestamp birthday;
    private String imageUrl;
    private Timestamp createdTime;
    private String status;
    private CommonUser commonUser;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id", nullable = false)
    public int getProfileId() {
        return profileId;
    }
    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 50)
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 50)
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "bio", nullable = true, length = 150)
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }

    @Basic
    @Column(name = "sex", nullable = true, length = 10)
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "birthday", nullable = true)
    public Timestamp getBirthday() {
        return birthday;
    }
    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "image_url", nullable = true, length = -1)
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Basic
    @Column(name = "created_time", nullable = true)
    public Timestamp getCreatedTime() {
        return createdTime;
    }
    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 10)
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    public CommonUser getCommonUser() {
        return commonUser;
    }
    public void setCommonUser(CommonUser commonUser) {
        this.commonUser = commonUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return profileId == profile.profileId &&
                Objects.equals(firstName, profile.firstName) &&
                Objects.equals(lastName, profile.lastName) &&
                Objects.equals(bio, profile.bio) &&
                Objects.equals(sex, profile.sex) &&
                Objects.equals(age, profile.age) &&
                Objects.equals(birthday, profile.birthday) &&
                Objects.equals(imageUrl, profile.imageUrl) &&
                Objects.equals(createdTime, profile.createdTime) &&
                Objects.equals(status, profile.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileId, firstName, lastName, bio, sex, age, birthday, imageUrl, createdTime, status);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "profileId=" + profileId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bio='" + bio + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", imageUrl='" + imageUrl + '\'' +
                ", createdTime=" + createdTime +
                ", status='" + status + '\'' +
                '}';
    }
}

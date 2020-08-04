package lk.ijse.glingler.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "story")
public class Story {
    private int storyId;
    private String imgUrl;
    private String status;
    private Timestamp createTime;
    private Profile profileByProfileId;

    @Id
    @Column(name = "story_id", nullable = false)
    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    @Basic
    @Column(name = "img_url", nullable = true, length = -1)
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Story story = (Story) o;
        return storyId == story.storyId &&
                Objects.equals(imgUrl, story.imgUrl) &&
                Objects.equals(status, story.status) &&
                Objects.equals(createTime, story.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storyId, imgUrl, status, createTime);
    }

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "profile_id")
    public Profile getProfileByProfileId() {
        return profileByProfileId;
    }

    public void setProfileByProfileId(Profile profileByProfileId) {
        this.profileByProfileId = profileByProfileId;
    }
}

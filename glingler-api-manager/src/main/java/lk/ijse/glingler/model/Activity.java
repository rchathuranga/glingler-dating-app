package lk.ijse.glingler.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Activity {
    private int activityId;
    private String content;
    private String imgUrl;
    private Integer reactCount;
    private Timestamp createdTime;
    private String status;

    @Id
    @Column(name = "activity_id", nullable = false)
    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    @Basic
    @Column(name = "content", nullable = true, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    @Column(name = "react_count", nullable = true)
    public Integer getReactCount() {
        return reactCount;
    }

    public void setReactCount(Integer reactCount) {
        this.reactCount = reactCount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return activityId == activity.activityId &&
                Objects.equals(content, activity.content) &&
                Objects.equals(imgUrl, activity.imgUrl) &&
                Objects.equals(reactCount, activity.reactCount) &&
                Objects.equals(createdTime, activity.createdTime) &&
                Objects.equals(status, activity.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityId, content, imgUrl, reactCount, createdTime, status);
    }
}

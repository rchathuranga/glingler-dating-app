package lk.ijse.glingler.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Comment {
    private int commentId;
    private String comment;
    private Timestamp createdTime;
    private String status;
    private Activity activityByActivityId;

    @Id
    @Column(name = "comment_id", nullable = false)
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Basic
    @Column(name = "comment", nullable = true, length = -1)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
        Comment comment1 = (Comment) o;
        return commentId == comment1.commentId &&
                Objects.equals(comment, comment1.comment) &&
                Objects.equals(createdTime, comment1.createdTime) &&
                Objects.equals(status, comment1.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, comment, createdTime, status);
    }

    @ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "activity_id")
    public Activity getActivityByActivityId() {
        return activityByActivityId;
    }

    public void setActivityByActivityId(Activity activityByActivityId) {
        this.activityByActivityId = activityByActivityId;
    }
}

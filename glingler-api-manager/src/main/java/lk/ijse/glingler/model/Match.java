package lk.ijse.glingler.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "`match`")
public class Match {
    private int matchId;
    private Profile profileId;
    private Profile matchProfileId;
    private String status;
    private Timestamp createTime;

    @Id
    @Column(name = "match_id")
    public int getMatchId() {
        return matchId;
    }
    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "profile_id")
    public Profile getProfileId() {
        return profileId;
    }
    public void setProfileId(Profile profileId) {
        this.profileId = profileId;
    }

    @ManyToOne
    @JoinColumn(name = "match_profile_id", referencedColumnName = "profile_id")
    public Profile getMatchProfileId() {
        return matchProfileId;
    }
    public void setMatchProfileId(Profile matchProfileId) {
        this.matchProfileId = matchProfileId;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "create_time")
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
        Match match = (Match) o;
        return matchId == match.matchId &&
                Objects.equals(profileId, match.profileId) &&
                Objects.equals(matchProfileId, match.matchProfileId) &&
                Objects.equals(status, match.status) &&
                Objects.equals(createTime, match.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, profileId, matchProfileId, status, createTime);
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId=" + matchId +
                ", profileId=" + profileId +
                ", matchProfileId=" + matchProfileId +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}

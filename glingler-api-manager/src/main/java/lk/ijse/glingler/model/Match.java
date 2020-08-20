package lk.ijse.glingler.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "match")
public class Match {
    private int matchId;
    private int profileId;
    private int matchProfileId;
    private String status;
    private Timestamp createTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id", nullable = false)
    public int getMatchId() {
        return matchId;
    }
    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    @Basic
    @Column(name = "profile_id", nullable = true, length = 10)
    public int getProfileId() {
        return profileId;
    }
    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    @Basic
    @Column(name = "match_profile_id", nullable = true)
    public int getMatchProfileId() {
        return matchProfileId;
    }
    public void setMatchProfileId(int matchProfileId) {
        this.matchProfileId = matchProfileId;
    }

    @Basic
    @Column(name = "status", nullable = true)
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
        Match match = (Match) o;
        return matchId == match.matchId &&
                Objects.equals(status, match.status) &&
                Objects.equals(createTime, match.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, status, createTime);
    }

}

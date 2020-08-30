package lk.ijse.glingler.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Matched {
    private int matchedId;
    private Profile profileId;
    private Profile matchProfileId;
    private String status;

    @Id
    @Column(name = "matched_id", nullable = false)
    public int getMatchedId() {
        return matchedId;
    }
    public void setMatchedId(int matchedId) {
        this.matchedId = matchedId;
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
    @JoinColumn(name = "match_profile_id", referencedColumnName  = "profile_id")
    public Profile getMatchProfileId() {
        return matchProfileId;
    }
    public void setMatchProfileId(Profile matchProfileId) {
        this.matchProfileId = matchProfileId;
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
        Matched matched = (Matched) o;
        return matchedId == matched.matchedId &&
                Objects.equals(profileId, matched.profileId) &&
                Objects.equals(matchProfileId, matched.matchProfileId) &&
                Objects.equals(status, matched.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchedId, profileId, matchProfileId, status);
    }

    @Override
    public String toString() {
        return "Matched{" +
                "matchedId=" + matchedId +
                ", profileId=" + profileId +
                ", matchProfileId=" + matchProfileId +
                ", status='" + status + '\'' +
                '}';
    }
}

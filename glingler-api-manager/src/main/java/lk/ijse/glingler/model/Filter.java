package lk.ijse.glingler.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "filter")
public class Filter {
    private int filterId;
    private Integer age;
    private String interestedOn;
    private Integer ageRangaStart;
    private Integer ageRangeEnd;
    private BigDecimal distance;
    private String locationLongitude;
    private String locationLatitude;
    private String locationDesc;

    @Id
    @Column(name = "filter_id", nullable = false)
    public int getFilterId() {
        return filterId;
    }

    public void setFilterId(int filterId) {
        this.filterId = filterId;
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
    @Column(name = "interested_on", nullable = true, length = 10)
    public String getInterestedOn() {
        return interestedOn;
    }

    public void setInterestedOn(String interestedOn) {
        this.interestedOn = interestedOn;
    }

    @Basic
    @Column(name = "age_ranga_start", nullable = true)
    public Integer getAgeRangaStart() {
        return ageRangaStart;
    }

    public void setAgeRangaStart(Integer ageRangaStart) {
        this.ageRangaStart = ageRangaStart;
    }

    @Basic
    @Column(name = "age_range_end", nullable = true)
    public Integer getAgeRangeEnd() {
        return ageRangeEnd;
    }

    public void setAgeRangeEnd(Integer ageRangeEnd) {
        this.ageRangeEnd = ageRangeEnd;
    }

    @Basic
    @Column(name = "distance", nullable = true, precision = 5)
    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    @Basic
    @Column(name = "location_longitude", nullable = true, length = 20)
    public String getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(String locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    @Basic
    @Column(name = "location_latitude", nullable = true, length = 20)
    public String getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(String locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    @Basic
    @Column(name = "location_desc", nullable = true, length = 50)
    public String getLocationDesc() {
        return locationDesc;
    }

    public void setLocationDesc(String locationDesc) {
        this.locationDesc = locationDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filter filter = (Filter) o;
        return filterId == filter.filterId &&
                Objects.equals(age, filter.age) &&
                Objects.equals(interestedOn, filter.interestedOn) &&
                Objects.equals(ageRangaStart, filter.ageRangaStart) &&
                Objects.equals(ageRangeEnd, filter.ageRangeEnd) &&
                Objects.equals(distance, filter.distance) &&
                Objects.equals(locationLongitude, filter.locationLongitude) &&
                Objects.equals(locationLatitude, filter.locationLatitude) &&
                Objects.equals(locationDesc, filter.locationDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filterId, age, interestedOn, ageRangaStart, ageRangeEnd, distance, locationLongitude, locationLatitude, locationDesc);
    }
}

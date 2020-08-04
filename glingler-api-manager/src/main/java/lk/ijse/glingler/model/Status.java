package lk.ijse.glingler.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "status")
public class Status {
    private String statusCode;
    private String description;

    @Id
    @Column(name = "status_code", nullable = false, length = 5)
    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 50)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return Objects.equals(statusCode, status.statusCode) &&
                Objects.equals(description, status.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusCode, description);
    }
}

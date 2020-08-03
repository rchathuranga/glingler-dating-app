package lk.ijse.glingler.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "status_category", schema = "glingler", catalog = "")
public class StatusCategory {
    private String statusCategoryCode;
    private String description;

    @Id
    @Column(name = "status_category_code", nullable = false, length = 5)
    public String getStatusCategoryCode() {
        return statusCategoryCode;
    }

    public void setStatusCategoryCode(String statusCategoryCode) {
        this.statusCategoryCode = statusCategoryCode;
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
        StatusCategory that = (StatusCategory) o;
        return Objects.equals(statusCategoryCode, that.statusCategoryCode) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusCategoryCode, description);
    }
}

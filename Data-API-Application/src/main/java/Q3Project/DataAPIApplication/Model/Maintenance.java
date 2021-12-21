package Q3Project.DataAPIApplication.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "maintenance")
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;

    @OneToOne()
    @JoinColumn(updatable = false, insertable = false, name = "treeviewid")
    private Treeview treeview;

    @ManyToOne()
    @JoinColumn(updatable = false, insertable = false, name = "Id")
    private MaintenanceType maintenanceType;

    @Column(name = "dayofrequest")
    private Date dayOfRequest;

    @Column(name = "dayofmaintenance")
    private Date dayOfMaintenance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Maintenance maintenance = (Maintenance) o;
        return Objects.equals(id, maintenance.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

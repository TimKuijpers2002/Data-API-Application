package Q3Project.DataAPIApplication.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "treeview")
public class Treeview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int treeviewid;

    @Column(name = "naam")
    private String name;

    @Column(name = "bouwjaar")
    private String buildYear;

    @Column(name = "actief")
    private int active;

    @Column(name = "melden")
    private int notification;

    @Column(name = "correctief")
    private int corrective;

    @Column(name = "werkopdracht")
    private int task;

    @Column(name = "onderhoud")
    private int maintenance;

    @Column(name = "onderhoudstemplate")
    private int maintenanceTemplate;

    @Column(name = "keuringsplichtig")
    private int inspectionObligation;

    @JoinColumn(name="treeviewid", nullable=false)
    @OneToMany
    private List<Maintenance> maintenances;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Treeview treeview = (Treeview) o;
        return Objects.equals(treeviewid, treeview.treeviewid);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

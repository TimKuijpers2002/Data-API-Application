package Q3Project.DataAPIApplication.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tellerbasis")
public class Tellerbasis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long counterBaseId;

    @Column(name = "naam")
    private String name;

    @Column(name = "omschrijving")
    private String description;

    @Column(name = "optie")
    private int option;

    @Column(name = "actief")
    private int active;

    @Column(name = "afkorting")
    private String abbreviation;

    @Column(name = "max_waarde")
    private double maxValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tellerbasis that = (Tellerbasis) o;
        return Objects.equals(counterBaseId, that.counterBaseId);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

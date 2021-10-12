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
@Table(name = "machine_monitoring_poorten")
public class MachineMonitoringPoorten {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int machineMonitoringPoortenId;

    @Column(name = "board")
    private int board;

    @Column(name = "port")
    private int port;

    @Column(name = "name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MachineMonitoringPoorten that = (MachineMonitoringPoorten) o;
        return Objects.equals(machineMonitoringPoortenId, that.machineMonitoringPoortenId);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

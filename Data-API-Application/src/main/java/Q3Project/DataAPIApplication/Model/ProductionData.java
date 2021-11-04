package Q3Project.DataAPIApplication.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "production_data")
public class ProductionData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long productionDataId;
    @Column(name = "treeview_id")
    private int treeviewId;
    @Column(name = "treeview2_id")
    private int treeview2Id;
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "end_date")
    private String endDate;
    @Column(name = "end_time")
    private String endTime;
    @Column(name = "name")
    private String name;

    @Column(name = "port")
    private int port;
    @Column(name = "board")
    private int board;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductionData productionData = (ProductionData) o;
        return Objects.equals(productionDataId, productionData.productionDataId);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

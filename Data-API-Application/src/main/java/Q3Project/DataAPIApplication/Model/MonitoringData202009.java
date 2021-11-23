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
@Table(name = "monitoring_data_202009")
public class MonitoringData202009 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long monitoringData202009Id;
    @Column(name = "board")
    private int board;

    @Column(name = "port")
    private int port;

    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "shot_time")
    private double shotTime;

    @Column(name = "previous_shot_id")
    private int previousShotId;


    public MonitoringData202009(Date timeStamp, long shottime){
        this.timestamp = timeStamp;
        this.shotTime = shottime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MonitoringData202009 that = (MonitoringData202009) o;
        return Objects.equals(monitoringData202009Id, that.monitoringData202009Id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

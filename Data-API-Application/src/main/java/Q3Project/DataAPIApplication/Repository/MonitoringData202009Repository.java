package Q3Project.DataAPIApplication.Repository;

import Q3Project.DataAPIApplication.Model.MonitoringData202009;
import Q3Project.DataAPIApplication.Model.ReturnableMonitoringData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public interface MonitoringData202009Repository extends JpaRepository<MonitoringData202009, Long> {
    @Query(value="SELECT m FROM MonitoringData202009 m INNER JOIN MachineMonitoringPoorten as MMP ON MMP.name = ?1")
    List<MonitoringData202009> findByName(String name);

    @Query(value="SELECT m FROM MonitoringData202009 m INNER JOIN MachineMonitoringPoorten as MMP ON MMP.name = ?1 WHERE m.board= MMP.board AND m.port = MMP.port AND m.timestamp > ?2 AND m.timestamp < ?3")
    List<MonitoringData202009> findByNameAndDate(String name, Date beginDay, Date endDay);
}

package Q3Project.DataAPIApplication.Repository;

import Q3Project.DataAPIApplication.Model.MonitoringData202009;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public interface MonitoringData202009Repository extends JpaRepository<MonitoringData202009, Long> {
    @Query(value="SELECT m FROM MonitoringData202009 m INNER JOIN MachineMonitoringPoorten as MMP ON MMP.name = :name")
    List<MonitoringData202009> findByName(@Param("name")String name);

    @Query(value = "SELECT m FROM MonitoringData202009 m WHERE m.board = :board AND m.port = :port AND m.timestamp BETWEEN :startdate AND :enddate ")
    List<MonitoringData202009> FindByBoardAndPort(@Param("board")int board, @Param("port")int port, @Param("startdate") Date startdate, @Param("enddate") Date enddate);

    @Query(value="SELECT m FROM MonitoringData202009 m INNER JOIN MachineMonitoringPoorten as MMP ON MMP.name = :name WHERE m.board= MMP.board AND m.port = MMP.port AND m.timestamp BETWEEN :beginDay AND :endDay")
    List<MonitoringData202009> findByNameAndDate(@Param("name")String name, @Param("beginDay")Date beginDay,@Param("endDay") Date endDay);

    @Query(value = "SELECT m FROM MonitoringData202009 m INNER JOIN MachineMonitoringPoorten as MMP ON MMP.name = :name WHERE m.board= MMP.board AND m.port = MMP.port AND m.timestamp <= :timestamp ORDER BY m.timestamp DESC")
    List<MonitoringData202009> findShotBeforeTimestamp(@Param("name")String name, @Param("timestamp")Date timestamp, Pageable pageable);
}
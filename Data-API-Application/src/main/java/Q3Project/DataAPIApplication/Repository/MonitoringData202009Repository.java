package Q3Project.DataAPIApplication.Repository;

import Q3Project.DataAPIApplication.Model.MonitoringData202009;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface MonitoringData202009Repository extends JpaRepository<MonitoringData202009, Long> {
    @Query(value="SELECT MonitoringData202009.shotTime, MonitoringData202009 .timestamp FROM MonitoringData202009 WHERE board= ?1 AND port = ?2")
    List<MonitoringData202009> findByBoardPort(int board, int port);
}

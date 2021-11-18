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
    @Query(value="SELECT m FROM MonitoringData202009 m WHERE m.board= ?1 AND m.port = ?2")
    List<MonitoringData202009> findByBoardPort(int board, int port);
}

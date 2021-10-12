package Q3Project.DataAPIApplication.Repository;

import Q3Project.DataAPIApplication.Model.MonitoringData202009;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoringData202009Repository extends JpaRepository<MonitoringData202009, Long> {

}

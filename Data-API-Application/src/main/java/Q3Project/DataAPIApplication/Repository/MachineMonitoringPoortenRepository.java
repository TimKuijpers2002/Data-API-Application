package Q3Project.DataAPIApplication.Repository;

import Q3Project.DataAPIApplication.Model.MachineMonitoringPoorten;
import Q3Project.DataAPIApplication.Model.MonitoringData202009;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineMonitoringPoortenRepository extends JpaRepository<MachineMonitoringPoorten, Long> {

    @Query(value="SELECT m FROM MachineMonitoringPoorten m WHERE m.name= ?1")
    List<MachineMonitoringPoorten> findByName(String name);
}

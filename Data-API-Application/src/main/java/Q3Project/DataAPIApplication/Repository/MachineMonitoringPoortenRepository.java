package Q3Project.DataAPIApplication.Repository;

import Q3Project.DataAPIApplication.Model.MachineMonitoringPoorten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineMonitoringPoortenRepository extends JpaRepository<MachineMonitoringPoorten, Long> {

}

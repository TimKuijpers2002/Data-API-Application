package Q3Project.DataAPIApplication.Repository;

import Q3Project.DataAPIApplication.Model.MachineMonitoringPoorten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineMonitoringPoortenRepository extends JpaRepository<MachineMonitoringPoorten, Long> {

    @Query(value="SELECT m FROM MachineMonitoringPoorten m WHERE m.name= :name")
    MachineMonitoringPoorten findByName(@Param("name") String name);
}

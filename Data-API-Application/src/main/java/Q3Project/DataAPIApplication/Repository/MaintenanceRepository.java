package Q3Project.DataAPIApplication.Repository;

import Q3Project.DataAPIApplication.Model.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

}

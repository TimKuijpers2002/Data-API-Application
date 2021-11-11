package Q3Project.DataAPIApplication.Repository;

import Q3Project.DataAPIApplication.Model.ProductionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionDataRepository extends JpaRepository<ProductionData, Long> {

}

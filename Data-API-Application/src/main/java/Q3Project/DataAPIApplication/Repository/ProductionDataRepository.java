package Q3Project.DataAPIApplication.Repository;

import Q3Project.DataAPIApplication.Model.ProductionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Date;
import java.util.List;

@Repository
public interface ProductionDataRepository extends JpaRepository<ProductionData, Long> {

    @Query(value="SELECT p FROM ProductionData p WHERE p.board= ?1 AND p.port = ?2 AND p.endDate > ?3 AND p.startDate < ?3 AND p.endTime < ?4 AND p.startTime > ?4")
    List<ProductionData> findByBPAndDate(int board, int port, Date date, Time time);
}

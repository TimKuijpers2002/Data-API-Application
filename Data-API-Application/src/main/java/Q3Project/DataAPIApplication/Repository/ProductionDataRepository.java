package Q3Project.DataAPIApplication.Repository;

import Q3Project.DataAPIApplication.Model.ProductionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionDataRepository extends JpaRepository<ProductionData, Long> {

    @Query(value="SELECT p FROM ProductionData p WHERE p.board = :board AND p.port = :port")
    List<ProductionData> findByBP(@Param("board") int board, @Param("port") int port);

    @Query(value ="SELECT p FROM ProductionData p WHERE p.treeviewId = :treeviewid OR p.treeview2Id = :treeviewid")
    List<ProductionData> findByTreeviewId(@Param("treeviewid") int treeviewid);
}

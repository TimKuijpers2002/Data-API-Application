package Q3Project.DataAPIApplication.Repository;

import Q3Project.DataAPIApplication.Model.ProductionData;
import Q3Project.DataAPIApplication.Model.Treeview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreeviewRepository extends JpaRepository<Treeview, Long> {
    @Query(value="SELECT t FROM Treeview t WHERE t.name = :name")
    Treeview findByName(@Param("name") String name);
}

package Q3Project.DataAPIApplication.Repository;

import Q3Project.DataAPIApplication.Model.Treeview;
import com.sun.source.tree.Tree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeviewRepository extends JpaRepository<Treeview, Long> {
    @Query(value = "SELECT t FROM Treeview t WHERE t.omschrijving = :name")
    Treeview FindByName(@Param("name") String name );

}

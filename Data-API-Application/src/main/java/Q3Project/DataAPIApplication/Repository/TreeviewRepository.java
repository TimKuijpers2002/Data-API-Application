package Q3Project.DataAPIApplication.Repository;

import Q3Project.DataAPIApplication.Model.Treeview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeviewRepository extends JpaRepository<Treeview, Long> {

}

package Q3Project.DataAPIApplication.Repository;

import Q3Project.DataAPIApplication.Model.Tellerbasis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TellerbasisRepository extends JpaRepository<Tellerbasis, Long> {

}

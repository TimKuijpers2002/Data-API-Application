package Q3Project.DataAPIApplication.Repository;

import Q3Project.DataAPIApplication.Model.Tellerstanden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TellerstandenRepository extends JpaRepository<Tellerstanden, Long> {

}

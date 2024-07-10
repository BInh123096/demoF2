package Ungdung.java.demoF2.repositories;
import Ungdung.java.demoF2.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IRoleRepository extends JpaRepository<Role, Long>{ Role findRoleById(Long id);
}
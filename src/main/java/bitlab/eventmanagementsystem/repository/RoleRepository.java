package bitlab.eventmanagementsystem.repository;

import bitlab.eventmanagementsystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.name = 'ROLE_USER'")
    Role findRoleUser();

    Role findByName(String roleName);

}

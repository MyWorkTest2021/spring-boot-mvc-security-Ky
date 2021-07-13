package us.peaksoft.springbootmvcsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import us.peaksoft.springbootmvcsecurity.model.Role;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select role from Role")
    List<String> getRoleNamesToList();

    Role getRoleByRole(String name);
}

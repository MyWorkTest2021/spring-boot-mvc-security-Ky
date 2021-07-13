package us.peaksoft.springbootmvcsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import us.peaksoft.springbootmvcsecurity.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByName(String name);
//   @Query("select email from User where email='kylych@mail.ru'")
    User findUserByEmail(String email);
}

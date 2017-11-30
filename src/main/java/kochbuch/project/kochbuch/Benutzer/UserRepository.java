package kochbuch.project.kochbuch.Benutzer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


interface UserRepository extends JpaRepository<User,Long>
{
    @Query("select u from User u where u.username = :username")
    User findByName(@Param("username") String username);

    @Query("select u from User u where u.role = 'ADMIN'")
    List<User> findAdmins();

}

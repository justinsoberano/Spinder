package userData.users;

import org.springframework.data.jpa.repository.JpaRepository;
//import repositories.Stations.Station;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(int id);
    void deleteById(int id);
    User findByUsername(String u);

    boolean existsById(int id);

}

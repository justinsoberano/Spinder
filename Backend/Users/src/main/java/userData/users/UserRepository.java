package userData.users;

import org.springframework.data.jpa.repository.JpaRepository;
//import repositories.Stations.Station;

/**
 * UserRepository is an interface that extends JpaRepository.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * findById is a method that finds a Station object by its id.
     * @param id is the id of the Station object.
     * @return the Station object with the given id.
     */
    User findById(int id);

    /**
     * deleteById is a method that deletes a Station object by its id.
     * @param id is the id of the Station object.
     */
    void deleteById(int id);

    /**
     * existsByUserName is a method that checks if a Station object exists by its name.
     * @param name is the name of the Station object.
     * @return true if the Station object exists, false otherwise.
     */
    boolean existsByUserName(String name);

    /**
     * deleteByUserName is a method that deletes a Station object by its name.
     * @param name is the name of the Station object.
     */
    void deleteByUserName(String name);

    /**
     * findByUserName is a method that finds a Station object by its name.
     * @param name is the name of the Station object.
     * @return the Station object with the given name.
     */
    User findByUserName(String name);

    /**
     * existsById is a method that checks if a Station object exists by its id.
     * @param id is the id of the Station object.
     * @return true if the Station object exists, false otherwise.
     */
    boolean existsById(int id);

}

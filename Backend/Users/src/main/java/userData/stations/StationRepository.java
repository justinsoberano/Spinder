package userData.stations;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * StationRepository is an interface that extends JpaRepository.
 */
public interface StationRepository extends JpaRepository<Station, Long> {

    /**
     * findById is a method that returns a Station object given an id.
     * @param id is the id of the Station object to be returned.
     * @return a Station object.
     */
    Station findById(int id);

    /**
     * deleteById is a method that deletes a Station object given an id.
     * @param id is the id of the Station object to be deleted.
     */
    void deleteById(int id);

    /**
     * existsById is a method that checks if a Station object exists given an id.
     * @param id is the id of the Station object to be checked.
     * @return a boolean value.
     */
    boolean existsById(int id);

}

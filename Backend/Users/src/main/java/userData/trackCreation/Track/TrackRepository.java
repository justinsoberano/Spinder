package userData.trackCreation.Track;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The TrackRepository interface is used to create a repository for the Track class
 */
public interface TrackRepository extends JpaRepository<Track, Long>{

    /**
     * Finds a track by its id
     * @param id the id of the track
     */
    void deleteById(String id);
}
package mappers.Artist;

import org.springframework.data.jpa.repository.JpaRepository;
import repositories.Users.User;

public interface ArtistRepository extends JpaRepository<Artist, Long>{

}

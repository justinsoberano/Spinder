package repositories.Stations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import mappers.Track.Track;
import repositories.Users.UserRepository;

@RestController
public class StationController {

    @Autowired
    StationRepository stationRepository;
    @PutMapping(path = "/station/next/{id}")
    Track getNextSong(@PathVariable int id){
        Station station = stationRepository.findById(id);
        station.getNextSong();
        return null;
    }

}

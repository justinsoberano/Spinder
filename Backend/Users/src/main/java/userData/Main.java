package userData;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import userData.stations.Station;
import userData.stations.StationRepository;
import userData.trackCreation.Track.TrackRepository;
import userData.users.User;
import userData.users.UserRepository;

@SpringBootApplication
class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    CommandLineRunner initUser(UserRepository userRepository, StationRepository stationRepository,
                               TrackRepository trackRepository) {
        return args -> {
            User user1 = new User(1, "Qusai", "I love music", "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg",
                    "key"); //TODO add key
            Station station1 = new Station(1);
            user1.setStation(station1);
            userRepository.save(user1);
            stationRepository.save(station1);
        };
    }


}

package userData;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import userData.stations.Station;
import userData.stations.StationRepository;
import userData.trackCreation.Track.Track;
import userData.trackCreation.Track.TrackRepository;
import userData.users.User;
import userData.users.UserRepository;

@SpringBootApplication
class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner initUser(UserRepository userRepository, StationRepository stationRepository, TrackRepository trackRepository) {
        return args -> {
            User user1 = new User(1, "Qusai", "I love music", "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg",
                    "key");
            Station station1 = new Station(1);
            Track seed1 = new Track("","","","","","");

            station1.setSeed(seed1);
            user1.setStation(station1);

            trackRepository.save(seed1);
            userRepository.save(user1);
            stationRepository.save(station1);
        };
    }


}

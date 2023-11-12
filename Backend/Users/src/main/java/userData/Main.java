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

/**
 * Main class for the Spring Boot application.
 */
@SpringBootApplication
class Main {

    /**
     * Main method for the Spring Boot application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    /**
     * Initializes the database with a user, station, and seed track.
     * @param userRepository The user repository.
     * @param stationRepository The station repository.
     * @param trackRepository The track repository.
     * @return A command line runner.
     */
    @Bean
    CommandLineRunner initUser(UserRepository userRepository, StationRepository stationRepository, TrackRepository trackRepository) {
        return args -> {
            User user1 = new User(1, "Qusai", "I love music", "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg",
                    "key");
            Station station1 = new Station(1);
            Track seed1 = new Track("","","","","","");

            station1.setSeed(seed1);
            user1.setStation(station1);

            userRepository.save(user1);
            trackRepository.save(seed1);
            stationRepository.save(station1);
        };
    }


}

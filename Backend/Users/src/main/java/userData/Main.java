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

}

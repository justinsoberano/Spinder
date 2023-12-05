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
            User user1 = new User(1, "test", "test_acc", "test_link", "access_key");
            Station station1 = new Station(1);
            Track seed1 = new Track("1","song_name","album_name","artist_name","image_link","preview_url");

            station1.setSeed(seed1);
            user1.setStation(station1);

            userRepository.save(user1);
            trackRepository.save(seed1);
            stationRepository.save(station1);
        };
    }

}

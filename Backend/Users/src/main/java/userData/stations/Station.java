package userData.stations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import userData.trackCreation.Spotify.SpotifyController;
import userData.trackCreation.Track.Track;
import userData.users.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class Station {

    // seed is of type seed

    @Id
    private int id;

    private String nextSongID;

    private String currentSong;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "track_id")
    private Track seed;

    @OneToOne
    @JsonIgnore
    private User station;

    public Station(){

    }

    public Station(int id){
        this.id = id;
    }

    public void setId(int i){
        this.id = i;
    }

    public int getId(){
        return id;
    }

    public void setSeed(Track T) { this.seed = T ; }

    public String getSeed() { return this.seed.getId(); }

    public List<Track> generateTacks(){
        return SpotifyController.getRecommendations(seed.getId());
    }

}

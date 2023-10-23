package userData.stations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import userData.trackCreation.Spotify.SpotifyController;
import userData.trackCreation.Track.Track;
import userData.users.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Entity
public class Station {

    // seed is of type seed

    @Id
    private int id;

    private String nextSongID;

    private String currentSong;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "track_id")
    private List<Track> seeds = new ArrayList<Track>();

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

    public void addSeed(Track T) {
        if(seeds.size() == 5) {
            seeds.remove(0);
        }
        seeds.add(T);
    }

    public void remove() { seeds.remove(0); }

    List<Track> getSeeds(){
        return seeds;
    }

    public List<Track> generateTacks(){
        return SpotifyController.getRecommendations(seeds, 50);
    }

}

package userData.stations;

import trackCreation.Track.Track;

import javax.persistence.*;
import java.util.List;

@Entity
public class Station {

    // seed is of type seed

    @Id
    private int id;

    private String nextSongID;

    private String currentSong;

    @OneToMany
    private List<Track> seeds;

    public void setId(int i){
        this.id = i;
    }

    public int getId(){
        return id;
    }

    public void addSeed(Track T) { this.seeds.add(T); }

    public void removeSeed(Track T) { this.seeds.remove(T); }

}

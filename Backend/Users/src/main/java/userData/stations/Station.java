package userData.stations;

import userData.trackCreation.Track.Track;

import javax.persistence.*;
import java.util.List;

@Entity
public class Station {

    // seed is of type seed

    @Id
    private int id;

    private String nextSongID;

    private String currentSong;

    @ManyToOne
    private Track seed;

    public void setId(int i){
        this.id = i;
    }

    public int getId(){
        return id;
    }

    public void setSeed(Track T) { this.seed = T ; }

    public String getSeed() { return this.seed.getId(); }

}

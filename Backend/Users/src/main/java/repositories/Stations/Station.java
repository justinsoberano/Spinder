package repositories.Stations;

import mappers.Track.Track;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Station {

    // seed is of type seed

    @Id
    private int id;

    private String nextSongID;

    private String currentSong;

    @OneToMany
    private List<Track> seeds = new ArrayList<Track>();
    public void getNextSong(){

    }

    public void setId(int i){
        this.id = i;
    }

    public int getId(){
        return id;
    }



}

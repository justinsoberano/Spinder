package userData.stations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import userData.trackCreation.Spotify.SpotifyController;
import userData.trackCreation.Track.Track;
import userData.users.User;

import javax.persistence.*;
import java.util.List;

/**
 * Station is a class that represents a station.
 */
@Entity
public class Station {

    /**
     * id is the id of the Station object.
     */
    @Id
    private int id;

    /**
     * tempo is the tempo of the Station object.
     */
    private int tempo;

    /**
     * popularity is the popularity of the Station object.
     */
    private int popularity;

    /**
     * volume is the volume of the Station object.
     */
    private int volume;

    /**
     * seed is the seed of the Station object.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "track_id")
    private Track seed;

    /**
     * station is the User object that owns the Station object.
     */
    @OneToOne
    @JsonIgnore
    private User station;

    /**
     * Default constructor for the Station class.
     */
    public Station(){}

    /**
     * Station is a constructor for the Station class.
     * @param id is the id of the Station object.
     */
    public Station(int id){
        this.id = id;
    }

    /**
     * setId is a method that sets the id of the Station object.
     * @param i is the id of the Station object.
     */
    public void setId(int i){
        this.id = i;
    }

    /**
     * getId is a method that returns the id of the Station object.
     * @return an int.
     */
    public int getId(){
        return id;
    }

    /**
     * setSeed is a method that sets the song seed of the Station object.
     * @param T is the Track object that is the seed of the Station object.
     */
    public void setSeed(Track T) { this.seed = T ; }

    /**
     * getSeed is a method that returns the song seed of the Station object.
     * @return the seed of the Station object.
     */
    public Track getSeed() { return this.seed; }

    /**
     * setPopularity sets the popularity of the Station object.
     * @param popularity is the User object that owns the Station object.
     */
    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    /**
     * Sets the tempo of the Station object.
     * @param tempo is the User object that owns the Station object.
     */
    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    /**
     * sets the volume of the Station object.
     * @param volume is the User object that owns the Station object.
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * gets the popularity of the Station object.
     * @return Popularity
     */
    public int getPopularity() {
        return popularity;
    }

    /**
     * gets the tempo of the Station object.
     * @return Tempo
     */
    public int getTempo() {
        return tempo;
    }

    /**
     * gets the volume of the Station object.
     * @return Volume
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Generates a list of tracks based on the seed track and the Station object's attributes.
     * @return a List of Track objects.
     */
    public List<Track> generateTacks(){
        return SpotifyController.getRecommendations(seed.getId(), 50);
    }

    public void addSeed(Track t) {
    }

    public void remove() {
    }
}

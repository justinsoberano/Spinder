package coms309.Users;

public class Song {

    private String name;

    private int key;

    private float loudness;

    private float tempo;

    public Song(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setLoudness(float loudness) {
        this.loudness = loudness;
    }

    public void setTepo(float tepo) {
        this.tempo = tepo;
    }

    public int getKey() {
        return key;
    }

    public float getLoudness() {
        return loudness;
    }

    public float getTempo() {
        return tempo;
    }
}

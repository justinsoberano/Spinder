package mappers.Track;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
class Image {

    @Id
    private int id;
    private int height;
    private int width;
    private String url;

    public int getId(){ return this.id; }

    public void setId(int id){ this.id = id; }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getUrl() {
        return url;
    }
}

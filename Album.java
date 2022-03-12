

public class Album extends Media {  // where album is a subclass from media
    private String artist;
    private String songs;

    public Album(String title, int numberOfCopies,String code ,String artist, String songs) {
        super(title, numberOfCopies,code);
        this.artist = artist;
        this.songs=songs;

    }

    //default getters and setters

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSongs() {
        return songs;
    }

    public void setSongs(String songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "Album "+","+super.toString()  + artist +" , "+ songs ;  // i used commas to easily split the string
    }
}

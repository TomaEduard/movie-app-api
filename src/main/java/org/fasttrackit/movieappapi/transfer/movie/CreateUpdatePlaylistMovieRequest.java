package org.fasttrackit.movieappapi.transfer.movie;

public class CreateUpdatePlaylistMovieRequest {

    private long id;
    private String name;
    private boolean playlist;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPlaylist() {
        return playlist;
    }

    public void setPlaylist(boolean playlist) {
        this.playlist = playlist;
    }

    @Override
    public String toString() {
        return "CreateUpdatePlaylistMovieRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", playlist=" + playlist +
                '}';
    }
}

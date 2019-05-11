package org.fasttrackit.movieappapi.transfer.movie;

public class UpdatePlaylistMovieRequest {

    private long id;
    private String name;
    private boolean favorite;

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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "UpdatePlaylistMovieRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", favorite=" + favorite +
                '}';
    }
}

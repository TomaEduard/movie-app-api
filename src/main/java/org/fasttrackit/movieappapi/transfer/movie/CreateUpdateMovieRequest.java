package org.fasttrackit.movieappapi.transfer.movie;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class CreateUpdateMovieRequest {

    @NotNull
    private long id;
    private String name;

    private double rating;
    private boolean favorite;
    private boolean watchlist;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isWatchlist() {
        return watchlist;
    }

    public void setWatchlist(boolean watchlist) {
        this.watchlist = watchlist;
    }

    public boolean isPlaylist() {
        return playlist;
    }

    public void setPlaylist(boolean playlist) {
        this.playlist = playlist;
    }

    @Override
    public String toString() {
        return "CreateUpdateMovieRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", favorite=" + favorite +
                ", watchlist=" + watchlist +
                ", playlist=" + playlist +
                '}';
    }
}

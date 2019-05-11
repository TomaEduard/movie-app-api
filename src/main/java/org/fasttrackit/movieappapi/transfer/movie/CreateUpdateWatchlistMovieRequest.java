package org.fasttrackit.movieappapi.transfer.movie;

public class CreateUpdateWatchlistMovieRequest {

    private long id;
    private String name;
    private boolean watchlist;

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

    public boolean isWatchlist() {
        return watchlist;
    }

    public void setWatchlist(boolean watchlist) {
        this.watchlist = watchlist;
    }


    @Override
    public String toString() {
        return "CreateUpdateWatchlistMovieRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", watchlist=" + watchlist +
                '}';
    }
}

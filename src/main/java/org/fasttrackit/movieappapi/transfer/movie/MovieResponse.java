package org.fasttrackit.movieappapi.transfer.movie;

public class MovieResponse {

    private long id;
    private String name;
    private double rating;
    private boolean favorite;
    private boolean watchlist;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieResponse that = (MovieResponse) o;

        if (id != that.id) return false;
        if (Double.compare(that.rating, rating) != 0) return false;
        if (favorite != that.favorite) return false;
        if (watchlist != that.watchlist) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (favorite ? 1 : 0);
        result = 31 * result + (watchlist ? 1 : 0);
        return result;
    }

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

    @Override
    public String toString() {
        return "MovieResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", favorite=" + favorite +
                ", watchlist=" + watchlist +
                '}';
    }
}

package org.fasttrackit.movieappapi.transfer.movie;

public class GetMovieRequest {

    private long id;
    private String partialName;
    private Double rating;
    private Boolean favorite;
    private Boolean watchlist;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPartialName() {
        return partialName;
    }

    public void setPartialName(String partialName) {
        this.partialName = partialName;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Boolean getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Boolean watchlist) {
        this.watchlist = watchlist;
    }


    @Override
    public String toString() {
        return "GetMovieRequest{" +
                "id=" + id +
                ", partialName='" + partialName + '\'' +
                ", rating=" + rating +
                ", favorite=" + favorite +
                ", watchlist=" + watchlist +
                '}';
    }
}

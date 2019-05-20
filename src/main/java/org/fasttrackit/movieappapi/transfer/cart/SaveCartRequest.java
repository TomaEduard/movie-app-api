package org.fasttrackit.movieappapi.transfer.cart;

import java.util.Set;

public class SaveCartRequest {

    private long customerId;
    private Set<Long> movieId;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Set<Long> getMovieId() {
        return movieId;
    }

    public void setMovieId(Set<Long> movieId) {
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "SaveCartRequest{" +
                "customerId=" + customerId +
                ", movieId=" + movieId +
                '}';
    }
}

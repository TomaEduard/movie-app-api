package org.fasttrackit.movieappapi.transfer;

public class CreateMovieRequest {

    private String name;
    private double rating;

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

    @Override
    public String toString() {
        return "CreateMovieRequest{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }
}

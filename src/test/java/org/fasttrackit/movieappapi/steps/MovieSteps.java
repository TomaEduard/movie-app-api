package org.fasttrackit.movieappapi.steps;

import org.fasttrackit.movieappapi.domain.Movie;
import org.fasttrackit.movieappapi.service.MovieService;
import org.fasttrackit.movieappapi.transfer.movie.CreateUpdateMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieSteps {

    @Autowired
    private MovieService movieService;

    public Movie createMovie() {
        CreateUpdateMovieRequest request = new CreateUpdateMovieRequest();
        request.setId(9999999);
        request.setName("test");
        request.setRating(3.5);
        request.setFavorite(true);
        request.setWatchlist(true);

        return movieService.createMovie(request);
    }
}

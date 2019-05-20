package org.fasttrackit.movieappapi;

import org.fasttrackit.movieappapi.domain.Movie;
import org.fasttrackit.movieappapi.service.MovieService;
import org.fasttrackit.movieappapi.steps.MovieSteps;
import org.fasttrackit.movieappapi.transfer.movie.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceIntegrationTests {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieSteps movieSteps;

    //    Create Movie Method
    @Test
    public void testCreateMovie_whenValidRequest_thenReturnProductWithId() {
        CreateUpdateMovieRequest request = new CreateUpdateMovieRequest();
        request.setId(299534);
        request.setName("Avengers: Endgame");
        request.setRating(3.5);
        request.setFavorite(true);
        request.setWatchlist(true);

        movieService.createMovie(request);
//        Movie movie = movieSteps.createMovie();

        // Make sure the product does not have null values
//        assertThat(movie, notNullValue());
//        assertThat(movie.getId(), greaterThan(0L));
    }

//   Create or update exist movie
//    Movie id not exist in db
    @Test
    public void testCreateUpdateMovie_whenValidRequest_MovieIdNotExistInDb_thenCreateMovie() throws Exception {

        // Create db object
        CreateUpdateMovieRequest request = new CreateUpdateMovieRequest();
        request.setId(299534);
        request.setName("Avengers: Endgame");
        request.setFavorite(false);
        request.setRating(5);
        request.setWatchlist(false);

        // Create service
        Movie createUpdateMovieRequest = movieService.createUpdateMovie(request);
        assertThat(createUpdateMovieRequest, notNullValue());
        assertThat(createUpdateMovieRequest.getId(), greaterThan(0L));

        assertThat(createUpdateMovieRequest.getRating(), greaterThanOrEqualTo(0.0));
        assertThat(createUpdateMovieRequest.getRating(), lessThanOrEqualTo(5.0));
    }

//    Movie id exist in db
    @Test
    public void testCreateUpdateMovie_whenValidRequest_MovieIdExistInDb_thenReturnMovie() throws Exception {

        // Create db object
        CreateUpdateMovieRequest request = new CreateUpdateMovieRequest();
        request.setId(299534);
        request.setName("Avengers: Endgame");
        request.setFavorite(true);
        request.setRating(2);
        request.setWatchlist(true);

        // Create service
        movieService.createUpdateMovie(request);
        assertThat(request, notNullValue());
        assertThat(request.getId(), greaterThan(0L));

        assertThat(request.getRating(), greaterThanOrEqualTo(0.0));
        assertThat(request.getRating(), lessThanOrEqualTo(5.0));
    }

//    Get Methods
    @Test
    public void testGetMovie_whenAllCriteriaProvidedAndMatching_thenReturnFilteredResults() throws Exception {
        Movie movie = movieSteps.createMovie();

        GetMovieRequest request = new GetMovieRequest();
        request.setPartialName("Aven");
//        request.setFavorite(true);
//        request.setRating(1.0);
//        request.setWatchlist(true);

        Page<Movie> movies = movieService.getMovies(request, PageRequest.of(0, 10));

        assertThat(movies.getTotalElements(), greaterThanOrEqualTo(1L));
    }

    // todo: Implement more tests for update


//    @Test
//    public void testCreateUpdateFavoriteMovie_thenReturnMovieWithId() {
//        CreateUpdateFavoriteMovieRequest request = new CreateUpdateFavoriteMovieRequest();
//        request.setId(299534);
//        request.setName("Agengers: Endgame");
//        request.setFavorite(true);
//
//        assertThat(request.getId(), greaterThan(0L));
//        assertThat(request.getId(), notNullValue());
//        assertThat(request.getName(), notNullValue());
//        assertThat(request.isFavorite(), notNullValue());
//
//        movieService.createUpdateFavoriteMovie(request);
//    }


}
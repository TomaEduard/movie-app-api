package org.fasttrackit.movieappapi;

import org.fasttrackit.movieappapi.domain.Movie;
import org.fasttrackit.movieappapi.exception.ResourceNotFoundException;
import org.fasttrackit.movieappapi.service.MovieService;
import org.fasttrackit.movieappapi.transfer.CreateMovieRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceIntegrationTests {

    @Autowired
    private MovieService movieService;

    @Test
    public void testCreateMovie_whenValidRequest_thenReturnProductWithId() {
        Movie movie = createMovie();

//         Make sure the product does not have null values
        assertThat(movie, notNullValue());
        assertThat(movie.getId(), greaterThan(0L));

        assertThat(movie.getRating(), notNullValue());
        assertThat(movie.getRating(), greaterThanOrEqualTo(0.0));
        assertThat(movie.getRating(), lessThanOrEqualTo(5.0));

        assertThat(movie.isFavorite(), notNullValue());
        assertThat(movie.isWatchlist(), notNullValue());
        assertThat(movie.isPlaylist(), notNullValue());
    }

    private Movie createMovie() {
        CreateMovieRequest request = new CreateMovieRequest();
        request.setName("Jumbo");
        request.setRating(5);
        request.setFavorite(true);
        request.setWatchlist(true);
        request.setPlaylist(false);

        return movieService.createMovie(request);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetMovie_whenMovieNotFound_ThrowException() throws Exception {
        movieService.getMovie(0);
    }

    @Test
    public void testGetMovie_whenExistingId_thanReturnMatchingMovie() throws Exception {
        Movie movie = createMovie();

        Movie retrivedMovie = movieService.getMovie(movie.getId());
        assertThat(retrivedMovie.getId(), is(movie.getId()));
        assertThat(retrivedMovie.getName(), is(movie.getName()));
    }
}
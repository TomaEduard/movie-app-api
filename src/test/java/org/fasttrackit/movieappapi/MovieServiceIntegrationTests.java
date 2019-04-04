package org.fasttrackit.movieappapi;

import org.fasttrackit.movieappapi.domain.Movie;
import org.fasttrackit.movieappapi.service.MovieService;
import org.fasttrackit.movieappapi.transfer.CreateMovieRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceIntegrationTests {

    @Autowired
    private MovieService movieService;

    @Test
    public void testCreateMovie_whenValidRequest_thenReturnProductWithId() {
        CreateMovieRequest request = new CreateMovieRequest();
        request.setName("Bambi");
        request.setRating(4.5);

        Movie movie = movieService.createMovie(request);

        // Make sure the product does not have null values
        assertThat(movie, notNullValue());
        assertThat(movie.getId(), greaterThan(0L));
    }
}

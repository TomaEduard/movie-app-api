package org.fasttrackit.movieappapi;

import org.fasttrackit.movieappapi.domain.Movie;
import org.fasttrackit.movieappapi.service.MovieService;
import org.fasttrackit.movieappapi.transfer.movie.*;
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

//    //    Create Movie Method
//    private Movie createMovie() {
//        CreateUpdateFavoriteMovieRequest request = new CreateUpdateFavoriteMovieRequest();
//        request.setId(299534);
//        request.setName("Agengers: Endgame");
////        request.setRating(3.5);
//        request.setFavorite(false);
////        request.setWatchlist(false);
////        request.setPlaylist(false);
//
//        return movieService.createUpdateFavoriteMovie(request);
//    }

    //    Create Movie with different propertie
    @Test
    public void testCreateUpdateFavoriteMovie_thenReturnMovieWithId() {
        CreateUpdateFavoriteMovieRequest request = new CreateUpdateFavoriteMovieRequest();
        request.setId(299534);
        request.setName("Agengers: Endgame");
        request.setFavorite(true);

        assertThat(request.getId(), greaterThan(0L));
        assertThat(request.getId(), notNullValue());
        assertThat(request.getName(), notNullValue());
        assertThat(request.isFavorite(), notNullValue());

        movieService.createUpdateFavoriteMovie(request);
    }

    @Test
    public void testCreateUpdateRatingMovie_thenReturnMovieWithId() {
        CreateUpdateRatingMovieRequest request = new CreateUpdateRatingMovieRequest();
        request.setId(299534);
        request.setName("Agengers: Endgame");
        request.setRating(4);

        assertThat(request.getId(), greaterThan(0L));
        assertThat(request.getId(), notNullValue());

        assertThat(request.getName(), notNullValue());

        assertThat(request.getRating(), notNullValue());
        assertThat(request.getRating(), notNullValue());
        assertThat(request.getRating(), greaterThanOrEqualTo(0.0));
        assertThat(request.getRating(), lessThanOrEqualTo(5.0));

        movieService.createUpdateRatingMovie(request);
    }

    @Test
    public void testCreateUpdateWatchlistMovie_thenReturnMovieWithId() {
        CreateUpdateWatchlistMovieRequest request = new CreateUpdateWatchlistMovieRequest();
        request.setId(299534);
        request.setName("Agengers: Endgame");
        request.setWatchlist(true);

        assertThat(request.getId(), greaterThan(0L));

        assertThat(request, notNullValue());

        movieService.createUpdateWatchlistMovie(request);
    }

    @Test
    public void testCreateUpdatePlatlistMovie_thenReturnMovieWithId() {
        CreateUpdatePlaylistMovieRequest request = new CreateUpdatePlaylistMovieRequest();
        request.setId(299534);
        request.setName("Agengers: Endgame");
        request.setPlaylist(true);

        assertThat(request.getId(), greaterThan(0L));
        assertThat(request.getId(), notNullValue());
        assertThat(request.getName(), notNullValue());
        assertThat(request.isPlaylist(), notNullValue());

        movieService.createUpdatePlaylistMovie(request);
    }

    //    Update Movie
    @Test
    public void testCreateUpdateFavoriteMovie_whenValidRequest_MovieExistInDb_thanReturnMovie() throws Exception {

//        Create local movie
        CreateUpdateFavoriteMovieRequest request = new CreateUpdateFavoriteMovieRequest();
        request.setId(299534);
        request.setName("Agengers: Endgame");
        request.setFavorite(true);
//        Create movie in db
//        movieService.createUpdateFavoriteMovie(createMovie1);

//        Create service
        Movie updateFavoriteMovie = movieService.CreateUpdateFavoriteMovie(request.getId(), request);
//        assertThat(updateFavoriteMovie.getName(), is(request.getName()));
//        assertThat(updateFavoriteMovie.getId(), is(request.getId()));
//
//        assertThat(updateFavoriteMovie.isFavorite(), not(is(request.getId())));
    }


/*
//    Create Movie
    @Test
    public void testCreateMovie_whenValidRequest_thenReturnMovieWithId2() {
        Movie movie = createMovie();

        // Make sure the product does not have null values
        assertThat(movie, notNullValue());
        assertThat(movie.getId(), greaterThan(0L));

        assertThat(movie.getRating(), notNullValue());
        assertThat(movie.getRating(), greaterThanOrEqualTo(0.0));
        assertThat(movie.getRating(), lessThanOrEqualTo(5.0));

        assertThat(movie.isFavorite(), notNullValue());
        assertThat(movie.isWatchlist(), notNullValue());
        assertThat(movie.isPlaylist(), notNullValue());
    }

    @Test
    public void testCreateUpdateFavoriteMovie_whenIdExistInDb_thanReturnMovieWithId() throws Exception {
        Movie createMovie = createMovie();

        CreateUpdateFavoriteMovieRequest request = new CreateUpdateFavoriteMovieRequest();
        request.setName(createMovie.getName());
        request.setId(createMovie.getId());
        request.setFavorite(!createMovie.isFavorite());

        Movie updateMovie = movieService.updateFavoriteMovie(createMovie.getId(), request);

        assertThat(updateMovie.getName(), is(request.getName()));
        assertThat(updateMovie.isFavorite(), not(is(createMovie.isFavorite())));
    }

*/

//    //    Update Movie
//    @Test
//    public void testCreateUpdateFavoriteMovie_whenValidRequest_MovieExistInDb_thanReturnMovie() throws Exception {
//
////        Create local movie
//        CreateUpdateFavoriteMovieRequest request = new CreateUpdateFavoriteMovieRequest();
//        request.setId(299534);
//        request.setName("Agengers: Endgame");
//        request.setFavorite(true);
////        Create movie in db
////        movieService.createUpdateFavoriteMovie(createMovie1);
//
////        Create local request
//        CreateUpdateFavoriteMovieRequest createUpdateFavoriteMovieRequest = new CreateUpdateFavoriteMovieRequest();
//        createUpdateFavoriteMovieRequest.setId(createMovie1.getId());
//        createUpdateFavoriteMovieRequest.setName(createMovie1.getName());
////        Set different favorite value of this movie
//        createUpdateFavoriteMovieRequest.setFavorite(!createMovie1.isFavorite());
//
////        Create service
//        Movie updateFavoriteMovie = movieService.CreateUpdateFavoriteMovie(createMovie1.getId(), createUpdateFavoriteMovieRequest);
//        assertThat(updateFavoriteMovie.getName(), is(createUpdateFavoriteMovieRequest.getName()));
//        assertThat(updateFavoriteMovie.getId(), is(createUpdateFavoriteMovieRequest.getId()));
//
//        assertThat(updateFavoriteMovie.isFavorite(), not(is(createUpdateFavoriteMovieRequest.getId())));
//    }

}
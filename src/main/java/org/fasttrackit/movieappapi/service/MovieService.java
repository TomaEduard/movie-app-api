package org.fasttrackit.movieappapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.movieappapi.domain.Movie;
import org.fasttrackit.movieappapi.exception.ResourceNotFoundException;
import org.fasttrackit.movieappapi.persistence.MovieRepository;
import org.fasttrackit.movieappapi.transfer.movie.CreateUpdateMovieRequest;
import org.fasttrackit.movieappapi.transfer.movie.GetMovieRequest;
import org.fasttrackit.movieappapi.transfer.movie.MovieResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(Movie.class);

    private final MovieRepository movieRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public MovieService(MovieRepository movieRepository, ObjectMapper objectMapper) {
        this.movieRepository = movieRepository;
        this.objectMapper = objectMapper;
    }

    //    Use for testing
    public Movie createMovie(CreateUpdateMovieRequest request) {
        LOGGER.info("Creating movie {}", request);
        Movie movie = objectMapper.convertValue(request, Movie.class);
        return movieRepository.save(movie);
    }

    public Movie createUpdateMovie(CreateUpdateMovieRequest request) throws ResourceNotFoundException {
        LOGGER.info("Creating or update movie: {}", request );

        if (movieRepository.existsById(request.getId())) {
            LOGGER.info("Retriving movie >> {} ", request);

            Movie movie = getMovie(request.getId()); // Get object from db
            BeanUtils.copyProperties(request, movie); // Set value from request to object

            return movieRepository.save(movie);
        } else {
            // if the id not exist in db, cr8 new object with request id
            LOGGER.info("Creating movie {}", request);
            Movie movie = objectMapper.convertValue(request, Movie.class);
            return movieRepository.save(movie);
        }
    }

    public Page<MovieResponse> getMovies(GetMovieRequest request, Pageable pageable) {
        LOGGER.info("Retriving movie >> {}", request );

        Page<Movie> movies;

        // name + favorite + rating + watchlist 15
        if (request.getPartialName() != null &&
                request.getFavorite() != null &&
                request.getRating() != null &&
                request.getWatchlist() != null) {
            movies = movieRepository.findByNameContainingAndFavoriteTrueAndRatingGreaterThanEqualAndWatchlistTrue(
                    request.getPartialName(), request.getRating(), pageable);
        } else if (request.getFavorite() != null &&             // favorite + rating + watchlist       14
                request.getRating() !=null &&
                request.getWatchlist() !=null) {
            movies = movieRepository.findByRatingGreaterThanEqualAndWatchlistTrueAndFavoriteTrue(
                    request.getRating(), pageable);
        } else if (request.getPartialName() != null &&              // name + rating + watchlist       13
                request.getRating() !=null &&
                request.getWatchlist() !=null) {
            movies = movieRepository.findByRatingGreaterThanEqualAndWatchlistTrueAndNameContaining(
                    request.getRating(), request.getPartialName(), pageable);

        } else if (request.getPartialName() != null &&          // name + favorite + watchlist         12
                request.getFavorite() !=null &&
                request.getWatchlist() !=null) {
            movies = movieRepository.findByNameContainingAndFavoriteIsTrueAndWatchlistIsTrue(
                    request.getPartialName(), pageable);
        } else if (request.getPartialName() != null &&          // name + favorite + rating            11
                request.getFavorite() != null &&
                request.getRating() != null) {
            movies = movieRepository.findByRatingGreaterThanEqualAndFavoriteTrueAndNameContaining(
                    request.getRating(), request.getPartialName(),  pageable);
        } else if (request.getRating() != null &&               // rating + watchlist                  10
                request.getWatchlist() != null) {
            movies = movieRepository.findByWatchlistIsTrueAndRatingGreaterThanEqual(request.getRating(), pageable);
        } else if (request.getFavorite() != null &&             // favorite + watchlist                 9
                request.getWatchlist() != null) {
            movies = movieRepository.findByFavoriteTrueAndWatchlistTrue(pageable);
        } else if (request.getFavorite() != null &&             // favorite + rating                    8
                request.getRating() != null) {
            movies = movieRepository.findByFavoriteTrueAndRatingGreaterThanEqual(request.getRating(), pageable);
        } else if (request.getPartialName() != null &&          // name + watchlist                     7
                request.getWatchlist() != null) {
            movies = movieRepository.findByNameContainingAndWatchlistTrue(request.getPartialName(), pageable);
        } else if (request.getPartialName() != null &&          // name + rating                        6
                request.getRating() != null) {
            movies = movieRepository.findByNameContainingAndRatingGreaterThanEqual(request.getPartialName(), request.getRating(), pageable);
        } else if (request.getPartialName() != null &&          // name + favorite                      5    NOT
                request.getFavorite() != null) {
            movies = movieRepository.findByNameContainingAndFavoriteTrue(request.getPartialName(), pageable);
        } else if (request.getWatchlist() != null) {            // watchlist                            4 work
            movies = movieRepository.findByWatchlistTrue(pageable);
        } else if (request.getRating() != null) {               // rating                               3 work
            movies = movieRepository.findByRatingGreaterThanEqual(request.getRating(), pageable);
        } else if (request.getFavorite() != null) {             // favorite                             2 work
            movies = movieRepository.findByFavoriteTrue(pageable);
        } else if (request.getPartialName() != null) {          // name                                 1 work
            movies = movieRepository.findByNameContaining(request.getPartialName(), pageable);

        } else {
            movies = movieRepository.findAll(pageable);
        }

        List<MovieResponse> movieResponseList = new ArrayList<>();
        for (Movie movie : movies.getContent()) {
            MovieResponse movieResponse = new MovieResponse();
            movieResponse.setId(movie.getId());
            movieResponse.setName(movie.getName());
            movieResponse.setFavorite(movie.isFavorite());
            movieResponse.setRating(movie.getRating());
            movieResponse.setWatchlist(movie.isWatchlist());

            movieResponseList.add(movieResponse);
        }
        return new PageImpl<>(movieResponseList, pageable, movies.getTotalElements());
    }

//    OLD VERSION
//    public Page<MovieResponse> getMovies(GetMovieRequest request, Pageable pageable) {
//        LOGGER.info("Retriving movie >> {}", request );
//
//        Page<Movie> movies;
//
//        // name                                 1       work
//        if (request.getPartialName() != null) {
//            movies = movieRepository.findByNameContaining(request.getPartialName(), pageable);
//            // favorite                             2       work
//        } else if (request.getFavorite() != null) {
//            movies = movieRepository.findByFavoriteTrue(pageable);
//            // rating                               3       work
//        } else if (request.getRating() != null) {
//            movies = movieRepository.findByRatingGreaterThanEqual(request.getRating(), pageable);
//            // watchlist                            4       work
//        } else if (request.getWatchlist() != null) {
//            movies = movieRepository.findByWatchlistTrue(pageable);
//            // name + favorite                      5               NOT
//        } else if (request.getPartialName() != null &&
//                request.getFavorite() != null) {
//            movies = movieRepository.findByNameContainingAndFavoriteTrue(request.getPartialName(), pageable);
//            // name + rating                        6
//        } else if (request.getPartialName() != null &&
//                request.getRating() != null) {
//            movies = movieRepository.findByNameContainingAndRatingGreaterThanEqual(request.getPartialName(), request.getRating(), pageable);
//            // name + watchlist                     7
//        } else if (request.getPartialName() != null &&
//                request.getWatchlist() != null) {
//            movies = movieRepository.findByNameContainingAndWatchlistTrue(request.getPartialName(), pageable);
//            // favorite + rating                    8
//        } else if (request.getFavorite() != null &&
//                request.getRating() != null) {
//            movies = movieRepository.findByFavoriteTrueAndRatingGreaterThanEqual(request.getRating(), pageable);
//            // favorite + watchlist                 9
//        } else if (request.getFavorite() != null &&
//                request.getWatchlist() != null) {
//            movies = movieRepository.findByFavoriteTrueAndWatchlistTrue(pageable);
//            // rating + watchlist                  10
//        } else if (request.getRating() != null &&
//                request.getWatchlist() != null) {
//            movies = movieRepository.findByWatchlistIsTrueAndRatingGreaterThanEqual(request.getRating(), pageable);
//            // name + favorite + rating            11
//        } else if (request.getPartialName() != null &&
//                request.getFavorite() != null &&
//                request.getRating() != null) {
//            movies = movieRepository.findByRatingGreaterThanEqualAndFavoriteTrueAndNameContaining(
//                    request.getRating(), request.getPartialName(),  pageable);
//            // name + favorite + watchlist         12
//        } else if (request.getPartialName() != null &&
//                request.getFavorite() !=null &&
//                request.getWatchlist() !=null) {
//            movies = movieRepository.findByNameContainingAndFavoriteIsTrueAndWatchlistIsTrue(
//                    request.getPartialName(), pageable);
//            // name + rating + watchlist       13
//        } else if (request.getPartialName() != null &&
//                request.getRating() !=null &&
//                request.getWatchlist() !=null) {
//            movies = movieRepository.findByRatingGreaterThanEqualAndWatchlistTrueAndNameContaining(
//                    request.getRating(), request.getPartialName(), pageable);
//            // favorite + rating + watchlist       14
//        } else if (request.getFavorite() != null &&
//                request.getRating() !=null &&
//                request.getWatchlist() !=null) {
//            movies = movieRepository.findByRatingGreaterThanEqualAndWatchlistTrueAndFavoriteTrue(
//                    request.getRating(), pageable);
//            // name + favorite + rating + watchlist 15
//        } else if (request.getPartialName() != null &&
//                request.getFavorite() != null &&
//                request.getRating() != null &&
//                request.getWatchlist() != null) {
//            movies = movieRepository.findByNameContainingAndFavoriteTrueAndRatingGreaterThanEqualAndWatchlistTrue(
//                    request.getPartialName(), request.getRating(), pageable);
//        } else {
//            movies = movieRepository.findAll(pageable);
//        }
//
//        List<MovieResponse> movieResponseList = new ArrayList<>();
//        for (Movie movie : movies.getContent()) {
//            MovieResponse movieResponse = new MovieResponse();
//            movieResponse.setId(movie.getId());
//            movieResponse.setName(movie.getName());
//            movieResponse.setFavorite(movie.isFavorite());
//            movieResponse.setRating(movie.getRating());
//            movieResponse.setWatchlist(movie.isWatchlist());
//
//            movieResponseList.add(movieResponse);
//        }
//        return new PageImpl<>(movieResponseList, pageable, movies.getTotalElements());
//    }





    // Get Methods
    // get method for other services
    public Movie getMovie(long id) throws ResourceNotFoundException {
        LOGGER.info("Retriving movie {}", id);

        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product" + id + "Resource not found"));
    }

    // getmethod for controller
    public MovieResponse getMovieWithoutCart(long id) throws ResourceNotFoundException {
        LOGGER.info("Find movie {}", id);


        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product" + id + "Resource not found"));

        LOGGER.info("Retriving movie {}", id);

        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setId(movie.getId());
        movieResponse.setName(movie.getName());
        movieResponse.setFavorite(movie.isFavorite());
        movieResponse.setRating(movie.getRating());
        movieResponse.setWatchlist(movie.isWatchlist());

        return movieResponse;
    }

    public void deleteMovie(long id) {
        LOGGER.info("Deleting movie {}", id);
        movieRepository.deleteById(id);
        LOGGER.info("Deleted movie {}", id);
    }


//    Update single value, old version (need - else if to detect what prop.
//
//    public Movie createUpdateFavoriteMovie(CreateUpdateFavoriteMovieRequest request) {
//        LOGGER.info("Creating favorite movie: {}", request );
//        Movie movie = objectMapper.convertValue(request, Movie.class);
//
//        return movieRepository.save(movie);
//    }
//
//    public Movie createUpdateRatingMovie(CreateUpdateRatingMovieRequest request) {
//        LOGGER.info("Creating rating movie: {}", request );
//        Movie movie = objectMapper.convertValue(request, Movie.class);
//
//        return movieRepository.save(movie);
//    }
//
//    public Movie createUpdateWatchlistMovie(CreateUpdateWatchlistMovieRequest request) {
//        LOGGER.info("Creating watchlist movie: {}", request );
//        Movie movie = objectMapper.convertValue(request, Movie.class);
//
//        return movieRepository.save(movie);
//    }
//
//    public Movie createUpdatePlaylistMovie(CreateUpdatePlaylistMovieRequest request) {
//        LOGGER.info("Creating watchlist movie: {}", request );
//        Movie movie = objectMapper.convertValue(request, Movie.class);
//
//        return movieRepository.save(movie);
//    }
}

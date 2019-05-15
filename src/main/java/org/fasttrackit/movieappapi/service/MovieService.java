package org.fasttrackit.movieappapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.movieappapi.domain.Movie;
import org.fasttrackit.movieappapi.exception.ResourceNotFoundException;
import org.fasttrackit.movieappapi.persistence.MovieRepository;
import org.fasttrackit.movieappapi.transfer.movie.CreateUpdateMovieRequest;
import org.fasttrackit.movieappapi.transfer.movie.GetMovieRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Page<Movie> getMovies(GetMovieRequest request, Pageable pageable) {
        LOGGER.info("Retriving movie >> {}", request );
        // name                                 1
        if (request.getPartialName() != null) {
            return movieRepository.findByNameContaining(request.getPartialName(), pageable);
        // favorite                             2
        } else if (request.getFavorite() != null) {
            return movieRepository.findByFavoriteTrue(pageable);
        // rating                               3
        } else if (request.getRating() != null) {
            return movieRepository.findByRatingGreaterThanEqual(request.getRating(), pageable);
        // watchlist                            4
        } else if (request.getWatchlist() != null) {
            return movieRepository.findByWatchlistTrue(pageable);
        // name + favorite                      5
        } else if (request.getPartialName() != null &&
                    request.getFavorite() != null) {
            return movieRepository.findByNameContainingAndFavoriteIsTrue(request.getPartialName(), pageable);
        // name + rating                        6
        } else if (request.getPartialName() != null &&
                request.getRating() != null) {
            return movieRepository.findByNameContainingAndRatingGreaterThanEqual(request.getPartialName(), request.getRating(), pageable);
        // name + watchlist                     7
        } else if (request.getPartialName() != null &&
                     request.getWatchlist() != null) {
            return movieRepository.findByNameContainingAndWatchlistTrue(request.getPartialName(), pageable);
        // favorite + rating                    8
        } else if (request.getFavorite() != null &&
                    request.getRating() != null) {
            return movieRepository.findByFavoriteTrueAndRatingGreaterThanEqual(request.getRating(), pageable);
        // favorite + watchlist                 9
        } else if (request.getFavorite() != null &&
                  request.getWatchlist() != null) {
            return movieRepository.findByFavoriteTrueAndWatchlistTrue(pageable);
        // rating + watchlist                  10
        } else if (request.getRating() != null &&
                    request.getWatchlist() != null) {
            return movieRepository.findByWatchlistIsTrueAndRatingGreaterThanEqual(request.getRating(), pageable);
        // name + favorite + rating            11
        } else if (request.getPartialName() != null &&
                    request.getFavorite() != null &&
                    request.getRating() != null) {
            return movieRepository.findByRatingGreaterThanEqualAndFavoriteTrueAndNameContaining(
                    request.getRating(), request.getPartialName(),  pageable);
        // name + favorite + watchlist         12
        } else if (request.getPartialName() != null &&
                    request.getFavorite() !=null &&
                    request.getWatchlist() !=null) {
            return movieRepository.findByNameContainingAndFavoriteIsTrueAndWatchlistIsTrue(
                    request.getPartialName(), pageable);
        // name + rating + watchlist       13
        } else if (request.getPartialName() != null &&
                request.getRating() !=null &&
                request.getWatchlist() !=null) {
            return movieRepository.findByRatingGreaterThanEqualAndWatchlistTrueAndNameContaining(
                     request.getRating(), request.getPartialName(), pageable);
        // favorite + rating + watchlist       14
        } else if (request.getFavorite() != null &&
                request.getRating() !=null &&
                request.getWatchlist() !=null) {
            return movieRepository.findByRatingGreaterThanEqualAndWatchlistTrueAndFavoriteTrue(
                    request.getRating(), pageable);
        }
        // name + favorite + rating + watchlist 15
         else if (request.getPartialName() != null &&
                    request.getFavorite() != null &&
                    request.getRating() != null &&
                    request.getWatchlist() != null) {
            return movieRepository.findByNameContainingAndFavoriteTrueAndRatingGreaterThanEqualAndWatchlistTrue(
                    request.getPartialName(), request.getRating(), pageable);
        }

        // any field completed
        return movieRepository.findAll(pageable);
    }

    public Movie getMovie(long id) throws ResourceNotFoundException {
        LOGGER.info("Retriving product {}", id );

        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product" + id + "Resource not found"));
    }

    public void deleteMovie(long id) {
        LOGGER.info("Deleting movie {}", id);
        movieRepository.deleteById(id);
        LOGGER.info("Deleted movie {}", id);
    }


    //    Update single value, old version (need - else if to detect what prop. have object)
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

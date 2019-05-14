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
            LOGGER.info("Creating movie {}", request);
            Movie movie = objectMapper.convertValue(request, Movie.class);
            return movieRepository.save(movie);
        }
    }


    public Page<Movie> getMovies(GetMovieRequest request, Pageable pageable) {
        LOGGER.info("Retriving movie >> {}", request );

        if (request.getPartialName() != null) {
            movieRepository.findByNameContaining(request.getPartialName(), pageable);

        } else if (request.getFavorite() != null) {
            movieRepository.findByFavoriteTrue(pageable);

        } else if (request.getRating() != null) {
            movieRepository.findByRatingGreaterThanEqual(request.getRating(), pageable);

        } else if (request.getWatchlist() != null) {
            movieRepository.findByWatchlistTrue(pageable);

        } else if (request.getPartialName() != null &&
                    request.getFavorite() != null &&
                    request.getRating() != null &&
                    request.getWatchlist() != null) {
            movieRepository.findByNameContainingAndFavoriteTrueAndRatingGreaterThanEqualAndWatchlistTrue(
                    request.getPartialName(), request.getRating(), pageable);
        }

        return movieRepository.findAll(pageable);
    }

    public Movie getMovie(long id) throws ResourceNotFoundException {
        LOGGER.info("Retriving product {}", id );

        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product" + id + "Resource not found"));
    }


    //    Update single value, old version
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

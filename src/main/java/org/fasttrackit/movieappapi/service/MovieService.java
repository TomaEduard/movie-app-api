package org.fasttrackit.movieappapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.movieappapi.domain.Movie;
import org.fasttrackit.movieappapi.exception.ResourceNotFoundException;
import org.fasttrackit.movieappapi.persistence.MovieRepository;
import org.fasttrackit.movieappapi.transfer.movie.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Movie createUpdateFavoriteMovie(CreateUpdateFavoriteMovieRequest request) {
        LOGGER.info("Creating favorite movie: {}", request );
        Movie movie = objectMapper.convertValue(request, Movie.class);

        return movieRepository.save(movie);
    }

    public Movie createUpdateRatingMovie(CreateUpdateRatingMovieRequest request) {
        LOGGER.info("Creating rating movie: {}", request );
        Movie movie = objectMapper.convertValue(request, Movie.class);

        return movieRepository.save(movie);
    }

    public Movie createUpdateWatchlistMovie(CreateUpdateWatchlistMovieRequest request) {
        LOGGER.info("Creating watchlist movie: {}", request );
        Movie movie = objectMapper.convertValue(request, Movie.class);

        return movieRepository.save(movie);
    }

    public Movie createUpdatePlaylistMovie(CreateUpdatePlaylistMovieRequest request) {
        LOGGER.info("Creating watchlist movie: {}", request );
        Movie movie = objectMapper.convertValue(request, Movie.class);

        return movieRepository.save(movie);
    }


    //    Update movie
    public Movie CreateUpdateFavoriteMovie(long id, CreateUpdateFavoriteMovieRequest request) throws Exception {
        LOGGER.info("Updating product {}, {}", id, request);

//        Cautam in baza daca avem acel id al filmului
        Movie movie = getMovie(id);
//        Copiam valorile lui request in obiectul movie
        BeanUtils.copyProperties(request, movie);

        return movieRepository.save(movie);
    }


    public Movie getMovie(long id) throws Exception {
        LOGGER.info("Retriving product {}", id );
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product" + id + "Resource not found"));
    }
}

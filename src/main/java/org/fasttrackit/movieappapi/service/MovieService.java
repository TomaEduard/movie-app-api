package org.fasttrackit.movieappapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.movieappapi.domain.Movie;
import org.fasttrackit.movieappapi.exception.ResourceNotFoundException;
import org.fasttrackit.movieappapi.persistence.MovieRepository;
import org.fasttrackit.movieappapi.transfer.CreateMovieRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public Movie createMovie(CreateMovieRequest request) {
        LOGGER.info("Creating product {}", request );
        Movie movie = objectMapper.convertValue(request, Movie.class);
        return movieRepository.save(movie);
    }

    public Movie getMovie(long id) throws Exception {
        LOGGER.info("Retriving product {}", id );
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product" + id + "Resource not found"));
    }

}

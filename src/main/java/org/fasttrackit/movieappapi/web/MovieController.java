package org.fasttrackit.movieappapi.web;

import org.fasttrackit.movieappapi.domain.Movie;
import org.fasttrackit.movieappapi.exception.ResourceNotFoundException;
import org.fasttrackit.movieappapi.service.MovieService;
import org.fasttrackit.movieappapi.transfer.movie.CreateUpdateMovieRequest;
import org.fasttrackit.movieappapi.transfer.movie.GetMovieRequest;
import org.fasttrackit.movieappapi.transfer.movie.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/movies")
@CrossOrigin
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

//    GET
    // get only 1 movie with id
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovie(@PathVariable("id") long id) throws Exception {
        MovieResponse response = movieService.getMovieWithoutCart(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // get objects from db depending on what fields are completed
    @GetMapping
    public ResponseEntity<Page<MovieResponse>> getMovies(@Valid GetMovieRequest request, Pageable pageable) {
        Page<MovieResponse> response = movieService.getMovies(request, pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    POST
    // create and delete
    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody @Valid CreateUpdateMovieRequest request) throws ResourceNotFoundException {
        Movie response = movieService.createUpdateMovie(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") long id) {
        movieService.deleteMovie(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}

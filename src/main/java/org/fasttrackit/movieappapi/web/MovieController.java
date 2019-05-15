package org.fasttrackit.movieappapi.web;

import org.fasttrackit.movieappapi.domain.Movie;
import org.fasttrackit.movieappapi.exception.ResourceNotFoundException;
import org.fasttrackit.movieappapi.service.MovieService;
import org.fasttrackit.movieappapi.transfer.movie.CreateUpdateMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService productService;

    @Autowired
    public MovieController(MovieService productService) {
        this.productService = productService;
    }

    // ge only 1 movie with id
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable("id") long id) throws Exception {
        Movie response = productService.getMovie(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // create and delete
    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody @Valid CreateUpdateMovieRequest request) throws ResourceNotFoundException {
        Movie response = productService.createUpdateMovie(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") long id) {
        productService.deleteMovie(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}

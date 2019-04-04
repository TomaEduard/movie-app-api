package org.fasttrackit.movieappapi.persistence;

import org.fasttrackit.movieappapi.domain.Movie;
import org.springframework.data.repository.PagingAndSortingRepository;

// Long is wrapper class for primitive long
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {

}

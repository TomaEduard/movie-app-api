package org.fasttrackit.movieappapi.persistence;

import org.fasttrackit.movieappapi.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// Long is wrapper class for primitive long
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {
//    Name
    Page<Movie> findByNameContaining(@NotNull @Size(min = 1, max = 300) String name, Pageable pageable);
//    Name + Favorite
    Page<Movie> findByNameContainingAndFavoriteIsTrue(String namme, Pageable pageable);
//    Name + Rating
    Page<Movie> findByNameContainingAndRatingGreaterThanEqual(String name, double rating, Pageable pageable);
//    Name + Watchlist
    Page<Movie> findByNameContainingAndWatchlistTrue(String name, Pageable pageable);
//    Name + Favorite + Watchlist
    Page<Movie> findByNameContainingAndFavoriteIsTrueAndWatchlistIsTrue(String name, Pageable pageable);

//    Favorite
    Page<Movie> findByFavoriteTrue(Pageable pageable);
//    Favorite + Watchlist
    Page<Movie> findByFavoriteTrueAndWatchlistTrue(Pageable pageable);
//    Favorite + Rating
    Page<Movie> findByFavoriteTrueAndRatingGreaterThanEqual(double rating, Pageable pageable);

//    Rating
    Page<Movie> findByRatingGreaterThanEqual(double rating, Pageable pageable);
//    Rating + Favorite + Name
        Page<Movie> findByRatingGreaterThanEqualAndFavoriteTrueAndNameContaining
        (double rating, String name, Pageable pageable);
//    Rating + Watchlist + Name
        Page<Movie> findByRatingGreaterThanEqualAndWatchlistTrueAndNameContaining( double rating, String name, Pageable pageable);
//    Rating + Watchlist + Favorite
        Page<Movie> findByRatingGreaterThanEqualAndWatchlistTrueAndFavoriteTrue(double rating, Pageable pageable);

//    Watchlist
    Page<Movie> findByWatchlistTrue(Pageable pageable);
//    Watchlist + Rating
    Page<Movie> findByWatchlistIsTrueAndRatingGreaterThanEqual(double rating, Pageable pageable);

//    Name + Favorite + Rating + Watchlist
    Page<Movie> findByNameContainingAndFavoriteTrueAndRatingGreaterThanEqualAndWatchlistTrue(
            String name, double rating, Pageable pageabe);









}

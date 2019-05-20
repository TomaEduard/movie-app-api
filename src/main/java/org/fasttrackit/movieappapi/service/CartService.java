package org.fasttrackit.movieappapi.service;

import org.fasttrackit.movieappapi.domain.Cart;
import org.fasttrackit.movieappapi.domain.Customer;
import org.fasttrackit.movieappapi.domain.Movie;
import org.fasttrackit.movieappapi.persistence.CartRepository;
import org.fasttrackit.movieappapi.transfer.cart.SaveCartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    private final CartRepository cartRepository;
    private final CustomerService customerService;
    private final MovieService movieService;

    @Autowired
    public CartService(CartRepository cartRepository, CustomerService customerService, MovieService movieService) {
        this.cartRepository = cartRepository;
        this.customerService = customerService;
        this.movieService = movieService;
    }

    @Transactional
    public Cart addMovieToCart(SaveCartRequest request) throws Exception {
        LOGGER.info("Adding movie to cart: {}", request);

        Customer customer = customerService.getCustomer(request.getCustomerId());

        Cart cart = new Cart();
        cart.setCustomer(customer);

        for (Long id : request.getMovieId()) {
            // could be done more efficientyle with a getAllProductsByIds
            Movie movie = movieService.getMovie(id);
            cart.addMovie(movie);
        }

        return cartRepository.save(cart);
    }

}

package org.fasttrackit.movieappapi.integrationTest;

import org.fasttrackit.movieappapi.domain.Cart;
import org.fasttrackit.movieappapi.domain.Customer;
import org.fasttrackit.movieappapi.domain.Movie;
import org.fasttrackit.movieappapi.service.CartService;
import org.fasttrackit.movieappapi.steps.CustomerSteps;
import org.fasttrackit.movieappapi.steps.MovieSteps;
import org.fasttrackit.movieappapi.transfer.cart.SaveCartRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CartIntegrationTests {

	@Autowired
	private CartService cartService;

	@Autowired
	private MovieSteps movieSteps;

	@Autowired
	private CustomerSteps customerSteps;

	@Test
	public void testAddMovieToCart_whenValidRequest_thanReturnCart() throws Exception {
		Movie movie = movieSteps.createMovie();				// cr8 movie in db
		Customer customer = customerSteps.createCustomer();	// cr8 customer in db

		// Create DTO
		SaveCartRequest request = new SaveCartRequest();
		request.setCustomerId(customer.getId());			// Add customer to cart
		request.setMovieId(Collections.singleton(movie.getId()));

		Cart cart = cartService.addMovieToCart(request);
		assertThat(cart, notNullValue());
		assertThat(cart.getId(), is(customer.getId()));
		assertThat(cart.getMovies(), hasSize(1));

	}

}






















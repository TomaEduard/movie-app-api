package org.fasttrackit.movieappapi.web;

import org.fasttrackit.movieappapi.service.CartService;
import org.fasttrackit.movieappapi.transfer.cart.SaveCartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@CrossOrigin
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity addProductToCart(@RequestBody SaveCartRequest request) throws Exception {
        cartService.addMovieToCart(request);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

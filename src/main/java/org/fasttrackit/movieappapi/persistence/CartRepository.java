package org.fasttrackit.movieappapi.persistence;

import org.fasttrackit.movieappapi.domain.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {


}

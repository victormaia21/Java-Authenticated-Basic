package br.com.carlosjunior.authapi.domain.repositories;

import br.com.carlosjunior.authapi.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}

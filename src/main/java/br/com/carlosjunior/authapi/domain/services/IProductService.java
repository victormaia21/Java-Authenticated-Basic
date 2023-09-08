package br.com.carlosjunior.authapi.domain.services;

import br.com.carlosjunior.authapi.domain.dtos.ProductDTO;
import br.com.carlosjunior.authapi.domain.dtos.ProductRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {
    Page<ProductDTO> findAll(Pageable pageable);

    ProductDTO findById(Long id) throws Exception;

    ProductDTO create(ProductRequestDTO dto);

    void deleteById(Long id) throws Exception;



}

package br.com.carlosjunior.authapi.domain.services.impl;

import br.com.carlosjunior.authapi.domain.dtos.ProductDTO;
import br.com.carlosjunior.authapi.domain.dtos.ProductRequestDTO;
import br.com.carlosjunior.authapi.domain.entities.Product;
import br.com.carlosjunior.authapi.domain.repositories.ProductRepository;
import br.com.carlosjunior.authapi.domain.services.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    private final ProductRepository repository;
    private final ModelMapper mapper;

    public ProductService(ProductRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> listProducts = repository.findAll(pageable);
        return listProducts.map(item -> mapper.map(item, ProductDTO.class));

    }

    @Override
    public ProductDTO findById(Long id) throws Exception {
        Product product = repository.findById(id).orElseThrow(Exception::new);
        return new ProductDTO(product);
    }

    @Override
    public ProductDTO create(ProductRequestDTO dto) {
        Product product = new Product(null, dto.getName(), dto.getPrice());
        product = repository.save(product);
        return new ProductDTO(product);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        repository.deleteById(id);
    }
}

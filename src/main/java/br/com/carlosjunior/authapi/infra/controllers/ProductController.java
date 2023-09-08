package br.com.carlosjunior.authapi.infra.controllers;

import br.com.carlosjunior.authapi.domain.dtos.ProductDTO;
import br.com.carlosjunior.authapi.domain.dtos.ProductRequestDTO;
import br.com.carlosjunior.authapi.domain.services.impl.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAllPaged(@PageableDefault Pageable pageable) throws Exception {
        return new ResponseEntity<>(service.findAll(pageable), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(service.findById(id), OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductRequestDTO dto) throws Exception {
        return new ResponseEntity<>(service.create(dto), OK);
    }

}

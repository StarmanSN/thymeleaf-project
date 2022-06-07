package ru.gb.externalapi.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbapimay.product.api.ProductGateway;
import ru.gb.gbapimay.product.dto.ProductDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductRestController {

    private final ProductGateway productGateway;

    @GetMapping
    public String getProductList(Model model) {
        model.addAttribute("products", productGateway.getProductList());
        return "product-list";
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable("productId") Long id) {
        return productGateway.getProduct(id);
    }

    @PostMapping
    public String handlePost(ProductDto productDto) {
        productGateway.handlePost(productDto);
        return "redirect:/api/v1/product";
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> handleUpdate(@PathVariable("productId") Long id, @Validated @RequestBody ProductDto productDto) {
        return productGateway.handleUpdate(id, productDto);

    }

    @DeleteMapping("/delete/{productId}")
    public String deleteById(@PathVariable("productId") Long id) {
        productGateway.deleteById(id);
        return "redirect:/product/all";
    }
}

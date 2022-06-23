package ru.gb.externalapi.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbapimay.manufacturer.api.ManufacturerGateway;
import ru.gb.gbapimay.manufacturer.dto.ManufacturerDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/manufacturer")
public class ManufacturerRestController {

    private final ManufacturerGateway manufacturerGateway;

    @GetMapping
    public String getManufacturerList(Model model) {
        model.addAttribute("manufacturers", manufacturerGateway.getManufacturerList());
        return "manufacturer-list";
    }

    @GetMapping("/{manufacturerId}")
    public ResponseEntity<?> getManufacturer(@PathVariable("manufacturerId") Long id) {
        return manufacturerGateway.getManufacturer(id);
    }

    @PostMapping
    public String handlePost(ManufacturerDto manufacturerDto) {
        manufacturerGateway.handlePost(manufacturerDto);
        return "redirect:/api/v1/manufacturer";
    }

    @PutMapping("/{manufacturerId}")
    public ResponseEntity<?> handleUpdate(@PathVariable("manufacturerId") Long id, @Validated @RequestBody ManufacturerDto manufacturerDto) {
        return manufacturerGateway.handleUpdate(id, manufacturerDto);

    }

    @DeleteMapping("/delete/{manufacturerId}")
    public String deleteById(@PathVariable("manufacturerId") Long id) {
        manufacturerGateway.deleteById(id);
        return "redirect:/manufacturer/all";
    }
}

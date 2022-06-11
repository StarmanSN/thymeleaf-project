//package ru.gb.thymeleafproject.web.controller.rest;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import ru.gb.gbapimay.manufacturer.dto.ManufacturerDto;
//import ru.gb.thymeleafproject.service.ManufacturerService;
//
//import java.net.URI;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/manufacturer")
//@Slf4j
//public class ManufacturerRestController {
//
//    private final ManufacturerService manufacturerService;
//
//    @GetMapping("/all")
//    public String getManufacturerList(Model model) {
//        model.addAttribute("manufacturers", manufacturerService.findAll());
//        return "manufacturer-list";
//
//    }
//
//    @GetMapping("/{manufacturerId}")
//    public ResponseEntity<?> getManufacturer(@PathVariable("manufacturerId") Long id) {
//        ManufacturerDto manufacturerDto;
//        if (id != null) {
//            manufacturerDto = manufacturerService.findById(id);
//            if (manufacturerDto != null) {
//                return new ResponseEntity<>(manufacturerDto, HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @PostMapping
//    public ResponseEntity<?> handlePost(@Validated @RequestBody ManufacturerDto manufacturerDto) {
//        ManufacturerDto savedManufacturerDto = manufacturerService.save(manufacturerDto);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(URI.create("/api/v1/manufacturer/" + savedManufacturerDto.getId()));
//        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{manufacturerId}")
//    public ResponseEntity<?> handleUpdate(@PathVariable("manufacturerId") Long id, @Validated @RequestBody ManufacturerDto manufacturerDto) {
//        manufacturerDto.setId(id);
//        ManufacturerDto savedManufacturerDto = manufacturerService.save(manufacturerDto);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(URI.create("/api/v1/manufacturer/" + savedManufacturerDto.getId()));
//        return new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT);
//    }
//
//    @DeleteMapping("/{manufacturerId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteById(@PathVariable("manufacturerId") Long id) {
//        manufacturerService.deleteById(id);
//    }
//}

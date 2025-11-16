package syksy25.card.web;

import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import syksy25.card.domain.Address;
import syksy25.card.domain.AddressRepository;

@RestController
@RequestMapping("/api/addresses")
public class AddressRestController {
    
    private final AddressRepository addressRepository;

    public AddressRestController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping
    public Iterable<Address> getAll() {
        return addressRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Address> getById(@PathVariable Long id) {
        return addressRepository.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Address createAddress(@Valid @RequestBody Address address) {
        return addressRepository.save(address);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("I{id}")
    public Address updateAddress(@PathVariable Long id, @Valid @RequestBody Address address) {
        address.setId(id);
        return addressRepository.save(address);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressRepository.deleteById(id);
    }

}

package syksy25.card.web;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import syksy25.card.domain.Address;
import syksy25.card.domain.AddressRepository;

@RestController
@RequestMapping("/api/addresslist")
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
    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(
            @PathVariable Long id,
            @Valid @RequestBody Address updatedData) {

        return addressRepository.findById(id)
                .map(existing -> {
                    // Update fields
                    existing.setFirstName(updatedData.getFirstName());
                    existing.setLastName(updatedData.getLastName());
                    existing.setStreet(updatedData.getStreet());
                    existing.setPostCode(updatedData.getPostCode());
                    existing.setCity(updatedData.getCity());
                    existing.setCountry(updatedData.getCountry());
                    existing.setCategory(updatedData.getCategory());

                    Address saved = addressRepository.save(existing);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressRepository.deleteById(id);
    }

}

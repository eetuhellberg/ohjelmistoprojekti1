package syksy25.card.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import syksy25.card.domain.Address;
import syksy25.card.domain.AddressRepository;
import syksy25.card.domain.CategoryRepository;


@Controller
public class AddressController {
    
    private final AddressRepository addressRepository;
    private final CategoryRepository categoryRepository;

    public AddressController(AddressRepository addressRepository, CategoryRepository categoryRepository) {
        this.addressRepository = addressRepository;
        this.categoryRepository = categoryRepository;
    }

    //listaa kaikki
    @GetMapping("/addresslist")
    public String addressList(Model model) {
        model.addAttribute("addresses", addressRepository.findAllWithCategory());
        return "addresslist";
    }

    //uuden osoitteen luonti
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/addresslist/add")
    public String addAddressForm(Model model) {
        model.addAttribute("address", new Address());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addressform";
    }

    //uuden osoitteen tallennus
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addresslist/save")
    public String saveAddress(@Valid @ModelAttribute("address") Address address, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "addressform";
        }
        addressRepository.save(address);
        return "redirect:/addresslist";
    }

    //osoitteen muokkaus
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/addresslist/edit/{id}")
    public String editAddress(@PathVariable("id") Long id, Model model) {
        model.addAttribute("address", addressRepository.findById(id).orElse(null));
        model.addAttribute("categories", categoryRepository.findAll());
        return "addressform";
    }

    //osoitteen poisto
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/addresslist/delete/{id}")
    public String deleteAddress(@PathVariable("id") Long id) {
        addressRepository.deleteById(id);
        return "redirect:/addresslist";
    }
    
}

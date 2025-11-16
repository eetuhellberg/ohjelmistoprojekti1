package syksy25.card.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be max of 50")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be max of 50")
    private String lastName;

    @NotBlank(message = "Address is reqquired")
    @Size(max = 100, message = "Street name must be max if 100")
    private String street;

    @NotBlank(message = "Postcode is required")
    @Pattern(
        regexp = "^[0-9A-Za-z\\- ]{3,10}$",
        message = "Postcode must be between 3-10 characters"
    )
    private String postCode;

    @NotBlank(message = "city is required")
    @Size(max = 50, message = "City name must be max of 50")
    private String city;

    @NotBlank(message = "Country is required")
    @Size(max = 50, message = "Country name must be max of 50")
    private String country;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("addresses")
    private Category category;

    public Address() {}

    public Address(String firstName, String lastName, String street, String postCode, String city, String country, Category category) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.postCode = postCode;
        this.city = city;
        this.country = country;
        this.category = category;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getPostCode() {
        return postCode;
    }
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }


}

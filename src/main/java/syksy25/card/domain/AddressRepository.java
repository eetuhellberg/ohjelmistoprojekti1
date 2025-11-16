package syksy25.card.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long>{
    @Query("SELECT a FROM Address a JOIN FETCH a.category")
    List<Address> findAllWithCategory();
}

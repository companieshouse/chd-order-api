package uk.gov.companieshouse.chd.order.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import uk.gov.companieshouse.chd.order.api.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String> {

    @Query("SELECT c FROM Customer c WHERE c.customerId= :customerId AND c.email= :email")
    Customer findCustomerByCustomerIdAndEmail(@Param("customerId") long customerId, @Param("email") String email);
}

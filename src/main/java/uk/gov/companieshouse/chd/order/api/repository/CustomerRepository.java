package uk.gov.companieshouse.chd.order.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uk.gov.companieshouse.chd.order.api.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String> {

    @Query("SELECT * FROM CUSTOMER WHERE CUSTOMERID=?1 AND EMAIL=?2")
    Customer findCustomerByCustomerIdAndEmail(long customerId, String email);
}

package uk.gov.companieshouse.chd.order.api.repository;

import org.springframework.data.repository.CrudRepository;
import uk.gov.companieshouse.chd.order.api.model.OrderHeader;

public interface OrderHeaderRepository extends CrudRepository<OrderHeader, String> { }

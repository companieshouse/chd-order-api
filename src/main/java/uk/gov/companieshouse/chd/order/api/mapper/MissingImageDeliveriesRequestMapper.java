package uk.gov.companieshouse.chd.order.api.mapper;

import org.mapstruct.Mapper;
import uk.gov.companieshouse.chd.order.api.dto.MissingImageDeliveriesDTO;
import uk.gov.companieshouse.chd.order.api.model.MissingImageDeliveriesRequest;

@Mapper(componentModel = "spring")
public interface MissingImageDeliveriesRequestMapper {
    MissingImageDeliveriesRequest mapMissingImageDeliveriesRequest(MissingImageDeliveriesDTO request);
}

package uk.gov.companieshouse.chd.order.api.exception;

/*
 * Custom exception that we can throw throughout our service
 * */
public class OrderServiceException extends RuntimeException {

	public OrderServiceException(String message) {
		super(message);
	}

}

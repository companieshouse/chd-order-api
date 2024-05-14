package uk.gov.companieshouse.chd.order.api.interceptor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;
import static uk.gov.companieshouse.api.util.security.EricConstants.ERIC_IDENTITY;
import static uk.gov.companieshouse.api.util.security.EricConstants.ERIC_IDENTITY_TYPE;
import static uk.gov.companieshouse.api.util.security.SecurityConstants.API_KEY_IDENTITY_TYPE;
import static uk.gov.companieshouse.chd.order.api.util.TestConstants.ERIC_IDENTITY_VALUE;

@ExtendWith(MockitoExtension.class)
public class ApiKeyAuthenticationInterceptorTest {

    @InjectMocks
    private ApiKeyAuthenticationInterceptor apiKeyAuthenticationInterceptor;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Test
    public void willAuthoriseIfEricHeadersArePresent() {
        lenient().doReturn(ERIC_IDENTITY_VALUE).when(request).getHeader(ERIC_IDENTITY);
        lenient().doReturn(API_KEY_IDENTITY_TYPE).when(request).getHeader(ERIC_IDENTITY_TYPE);
        assertTrue(apiKeyAuthenticationInterceptor.preHandle(request, response, null));
    }

    @Test
    public void willNotAuthoriseIfIdentityHeaderIsNotPresent() {
        lenient().doReturn(null).when(request).getHeader(ERIC_IDENTITY);
        lenient().doReturn(API_KEY_IDENTITY_TYPE).when(request).getHeader(ERIC_IDENTITY_TYPE);
        assertFalse(apiKeyAuthenticationInterceptor.preHandle(request, response, null));
    }

    @Test
    public void willNotAuthoriseIfIdentityTypeHeaderIsNotPresent() {
        lenient().doReturn(ERIC_IDENTITY_VALUE).when(request).getHeader(ERIC_IDENTITY);
        lenient().doReturn(null).when(request).getHeader(ERIC_IDENTITY_TYPE);
        assertFalse(apiKeyAuthenticationInterceptor.preHandle(request, response, null));
    }
    @Test
    public void willNotAuthoriseIfIdentityTypeHeaderIsInvalid() {
        lenient().doReturn(ERIC_IDENTITY_VALUE).when(request).getHeader(ERIC_IDENTITY);
        lenient().doReturn("some-incorrect-identity-type").when(request).getHeader(null);
        assertFalse(apiKeyAuthenticationInterceptor.preHandle(request, response, null));
    }
}

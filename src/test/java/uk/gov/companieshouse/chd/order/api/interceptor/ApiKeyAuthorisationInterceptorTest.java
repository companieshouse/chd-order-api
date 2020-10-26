package uk.gov.companieshouse.chd.order.api.interceptor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.lenient;
import static uk.gov.companieshouse.api.util.security.EricConstants.ERIC_IDENTITY_TYPE;
import static uk.gov.companieshouse.api.util.security.SecurityConstants.API_KEY_IDENTITY_TYPE;

@ExtendWith(MockitoExtension.class)
class ApiKeyAuthorisationInterceptorTest {

    @InjectMocks
    private ApiKeyAuthorisationInterceptor apiKeyAuthorisationInterceptor;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Test
    public void willAuthoriseIfIdentityTypeHeaderIsValid() {
        lenient().doReturn(API_KEY_IDENTITY_TYPE).when(request).getHeader(ERIC_IDENTITY_TYPE);
        assertTrue(apiKeyAuthorisationInterceptor.preHandle(request, response, null));
    }

    @Test
    public void willNotAuthoriseIfIdentityTypeHeaderIsNotValid() {
        lenient().doReturn("invalid").when(request).getHeader(ERIC_IDENTITY_TYPE);
        assertFalse(apiKeyAuthorisationInterceptor.preHandle(request, response, null));
    }

}

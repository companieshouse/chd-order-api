package uk.gov.companieshouse.chd.order.api.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import uk.gov.companieshouse.api.util.security.AuthorisationUtil;
import uk.gov.companieshouse.api.util.security.SecurityConstants;
import uk.gov.companieshouse.logging.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import static uk.gov.companieshouse.chd.order.api.logging.LoggingUtils.getLogger;

public class ApiKeyAuthorisationInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = getLogger();

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        final String identityType = AuthorisationUtil.getAuthorisedIdentityType(request);
        if (!identityType.equals(SecurityConstants.API_KEY_IDENTITY_TYPE)) {
            LOGGER.infoRequest(request, "invalid identity type [" + identityType + "]", null);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        LOGGER.infoRequest(request, "authorised as api key", null);
        return true;
    }

}


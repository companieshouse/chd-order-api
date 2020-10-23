package uk.gov.companieshouse.chd.order.api.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import uk.gov.companieshouse.api.util.security.AuthorisationUtil;
import uk.gov.companieshouse.api.util.security.SecurityConstants;
import uk.gov.companieshouse.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static uk.gov.companieshouse.chd.order.api.logging.LoggingUtils.getLogger;

public class ApiKeyAuthorisationInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = getLogger();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        final String identityType = AuthorisationUtil.getAuthorisedIdentityType(request);
        if (!identityType.equals(SecurityConstants.API_KEY_IDENTITY_TYPE)) {
            LOGGER.debugRequest(request, "invalid identity type [" + identityType + "]", null);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        LOGGER.debugRequest(request, "authorised as api key", null);
        return true;
    }

}


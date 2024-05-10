package uk.gov.companieshouse.chd.order.api.interceptor;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import uk.gov.companieshouse.api.util.security.AuthorisationUtil;
import uk.gov.companieshouse.logging.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static uk.gov.companieshouse.chd.order.api.logging.LoggingUtils.getLogger;
@Component
public class ApiKeyAuthenticationInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = getLogger();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        final String authorisedUser = AuthorisationUtil.getAuthorisedIdentity(request);
        if (authorisedUser == null) {
            LOGGER.infoRequest(request, "no authorised identity", null);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        final String identityType = AuthorisationUtil.getAuthorisedIdentityType(request);
        if (identityType == null) {
            LOGGER.infoRequest(request, "no authorised identity type", null);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return true;
    }
}

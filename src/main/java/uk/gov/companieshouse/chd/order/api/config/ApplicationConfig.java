package uk.gov.companieshouse.chd.order.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import uk.gov.companieshouse.chd.order.api.interceptor.ApiKeyAuthenticationInterceptor;
import uk.gov.companieshouse.chd.order.api.interceptor.ApiKeyAuthorisationInterceptor;

import static uk.gov.companieshouse.chd.order.api.controller.HealthcheckController.HEALTHCHECK_URI;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

    private final String healthcheckUri;

    public ApplicationConfig(@Value(HEALTHCHECK_URI) final String healthcheckUri) {
        this.healthcheckUri = healthcheckUri;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new ApiKeyAuthenticationInterceptor()).excludePathPatterns(healthcheckUri);;
        registry.addInterceptor(new ApiKeyAuthorisationInterceptor()).excludePathPatterns(healthcheckUri);;
    }
}

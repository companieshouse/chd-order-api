package uk.gov.companieshouse.chd.order.api.logging;

import io.opentelemetry.api.OpenTelemetry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class OpenTelemetryAppenderInitializerTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withUserConfiguration(OpenTelemetryAppenderTestConfiguration.class);

    @Test
    @DisplayName("OpenTelemetry appender initialiser bean is not created by default")
    void appenderInitialiserBeanIsNotCreatedByDefault() {
        contextRunner.run(context -> assertThat(context.getBeansOfType(OpenTelemetryAppenderInitializer.class))
                .isEmpty());
    }

    @Test
    @DisplayName("OpenTelemetry appender initialiser bean is created when enabled")
    void appenderInitialiserBeanIsCreatedWhenEnabled() {
        contextRunner.withPropertyValues("management.opentelemetry.enabled=true")
                .run(context -> assertThat(context.getBeansOfType(OpenTelemetryAppenderInitializer.class))
                        .hasSize(1));
    }

    @Test
    @DisplayName("OpenTelemetry appender initialiser can install appender")
    void openTelemetryAppenderInitialiserCanInstallAppender() {
        OpenTelemetryAppenderInitializer initializer = new OpenTelemetryAppenderInitializer(OpenTelemetry.noop());
        assertDoesNotThrow(initializer::afterPropertiesSet);
    }

    @Configuration
    @Import(OpenTelemetryAppenderInitializer.class)
    static class OpenTelemetryAppenderTestConfiguration {

        @Bean
        OpenTelemetry openTelemetry() {
            return OpenTelemetry.noop();
        }
    }
}

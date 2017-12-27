package cn.mimiron.gateway.config;

import cn.mimiron.gateway.filter.accesscontrol.AccessControlFilter;
import cn.mimiron.gateway.filter.ratelimiting.RateLimitingFilter;
import cn.mimiron.gateway.filter.responserewriting.SwaggerBasePathRewritingFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangxd
 */
@Configuration
public class GatewayConfiguration {

    @Configuration
    public static class SwaggerBasePathRewritingConfiguration {

        @Bean
        public SwaggerBasePathRewritingFilter swaggerBasePathRewritingFilter() {
            return new SwaggerBasePathRewritingFilter();
        }
    }

    @Configuration
    public static class AccessControlFilterConfiguration {

        @Bean
        public AccessControlFilter accessControlFilter(RouteLocator routeLocator, ApplicationProperties applicationProperties) {
            return new AccessControlFilter(routeLocator, applicationProperties);
        }
    }

    /**
     * Configures the Zuul filter that limits the number of API calls per user.
     * <p>
     * This uses Bucket4J to limit the API calls, see {@link RateLimitingFilter}.
     */
    @Configuration
    @ConditionalOnProperty("application.gateway.rate-limiting.enabled")
    public static class RateLimitingConfiguration {

        private final ApplicationProperties applicationProperties;

        public RateLimitingConfiguration(ApplicationProperties applicationProperties) {
            this.applicationProperties = applicationProperties;
        }

        @Bean
        public RateLimitingFilter rateLimitingFilter() {
            return new RateLimitingFilter(applicationProperties);
        }
    }
}

package org.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    /*
     * Etape 6 - partie A (routes via Eureka, a utiliser a la place du bean ci-dessous) :
     *
     * @Bean
     * RouteLocator routes(RouteLocatorBuilder builder) {
     *     return builder.routes()
     *             .route(r -> r.path("/clients/**").uri("lb://SERVICE-CLIENT"))
     *             .route(r -> r.path("/client/**").uri("lb://SERVICE-CLIENT"))
     *             .route(r -> r.path("/voitures/**").uri("lb://SERVICE-VOITURE"))
     *             .build();
     * }
     */

    // Etape 6 - partie B : routes dynamiques -> /NOM-DU-SERVICE/** pour chaque service inscrit dans Eureka
    @Bean
    DiscoveryClientRouteDefinitionLocator routesDynamique(
            ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp) {
        return new DiscoveryClientRouteDefinitionLocator(rdc, dlp);
    }
}

package io.spring.workshop.stockquotes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class QuoteRouter {

    @Bean
    RouterFunction<ServerResponse> quotesRouter(QuoteHandler handler) {
        return RouterFunctions
                .route(GET("/quotes").and(accept(MediaType.APPLICATION_STREAM_JSON)), handler::streamQuotes);
    }
}

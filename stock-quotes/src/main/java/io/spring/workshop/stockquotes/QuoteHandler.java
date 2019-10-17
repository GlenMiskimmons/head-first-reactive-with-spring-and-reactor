package io.spring.workshop.stockquotes;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class QuoteHandler {
    private QuoteGenerator quoteGenerator;

    public QuoteHandler(QuoteGenerator quoteGenerator) {
        this.quoteGenerator = quoteGenerator;
    }

    public Mono<ServerResponse> streamQuotes(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(quoteGenerator.fetchQuoteStream(), Quote.class);
    }

}

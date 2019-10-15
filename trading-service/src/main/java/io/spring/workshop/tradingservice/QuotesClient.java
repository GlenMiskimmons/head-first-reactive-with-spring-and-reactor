package io.spring.workshop.tradingservice;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class QuotesClient {

    private WebClient.Builder builder;

    QuotesClient(WebClient.Builder builder) {
        this.builder = builder;
    }

    public Flux<Quote> quotesFeed() {
        return null;
    }

}

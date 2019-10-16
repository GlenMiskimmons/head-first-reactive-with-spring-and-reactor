package io.spring.workshop.tradingservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class QuotesClient {

    private WebClient client;

    QuotesClient(WebClient.Builder client, @Value("${stockQuotes.url}") String stockQuotesUrl) {
        this.client = client.baseUrl(stockQuotesUrl)
                .build();
    }

    public Flux<Quote> quotesFeed() {
        return client
                .get()
                .uri("/quotes")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(Quote.class);
    }

    public Mono<Quote> getLatestQuote(String ticker) {
        return quotesFeed()
                .filter(quote -> quote.getTicker().contains(ticker))
                .next()
                .timeout(Duration.ofSeconds(15), Mono.just(new Quote(ticker)));
    }

}

package io.spring.workshop.tradingservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TradingCompanyClient {

    private WebClient client;

    TradingCompanyClient(WebClient.Builder clientBuilder, @Value("${stockDetails.url}") String stockDetailsUrl) {
        this.client = clientBuilder.baseUrl(stockDetailsUrl)
                .build();
    }

    public Flux<TradingCompany> getAllTradingCompanies() {
        return client.get()
                .uri("/details")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(TradingCompany.class);
    }

    public Mono<TradingCompany> getTradingCompanyByTicker(String ticker) {
        return client.get()
                .uri("/details/{ticker}", ticker)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(TradingCompany.class)
                .switchIfEmpty(Mono.error(new TickerNotFoundException("Unknown Ticker: " + ticker)));
    }
}

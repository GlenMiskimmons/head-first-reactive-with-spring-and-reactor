package io.spring.workshop.tradingservice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

@Controller
public class QuotesController {

    private QuotesClient quotesClient;

    QuotesController(QuotesClient quotesClient) {
        this.quotesClient = quotesClient;
    }

    @GetMapping("/quotes/feed")
    public Flux<Quote> getQuotesFeed() {
        return null;
    }
}

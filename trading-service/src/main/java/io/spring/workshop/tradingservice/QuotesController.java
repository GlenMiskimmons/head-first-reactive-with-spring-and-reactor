package io.spring.workshop.tradingservice;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;

@Controller
public class QuotesController {

    private QuotesClient quotesClient;

    QuotesController(QuotesClient quotesClient) {
        this.quotesClient = quotesClient;
    }

    @GetMapping(value = "/quotes/feed", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<Quote> getQuotesFeed() {
        return quotesClient.quotesFeed();
    }
}

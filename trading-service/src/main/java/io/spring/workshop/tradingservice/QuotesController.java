package io.spring.workshop.tradingservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class QuotesController {

    private QuotesClient quotesClient;
    private TradingCompanyClient companyClient;

    QuotesController(QuotesClient quotesClient, TradingCompanyClient companyClient) {
        this.quotesClient = quotesClient;
        this.companyClient = companyClient;
    }

    @GetMapping(value = "/quotes/feed", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<Quote> getQuotesFeed() {
        return quotesClient.quotesFeed();
    }

    @GetMapping(value = "/quotes/summary/{ticker}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Mono<TradingCompanySummary> getQuotesFeed(@PathVariable String ticker) {
        return Mono.zip(quotesClient.getLatestQuote(ticker), companyClient.getTradingCompanyByTicker(ticker),
            (quote, tradingCompany) -> new TradingCompanySummary(tradingCompany, quote));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TickerNotFoundException.class)
    public void onTickerNotFound() { }
}

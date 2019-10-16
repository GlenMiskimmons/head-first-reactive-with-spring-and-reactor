package io.spring.workshop.tradingservice;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;

@Controller
public class TradingCompanyController {
    private TradingCompanyClient client;

    public TradingCompanyController(TradingCompanyClient client) {
        this.client = client;
    }

    @GetMapping(value = "/details", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Flux<TradingCompany> getAllTradingCompanies() {
        return client.getAllTradingCompanies();
    }

    @GetMapping(value = "/details/{ticker}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Mono<TradingCompany> getTradingCompanyByTicker(@PathVariable String ticker) {
        return client.getTradingCompanyByTicker(ticker);
    }
}

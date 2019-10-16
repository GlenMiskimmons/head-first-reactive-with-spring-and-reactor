package io.spring.workshop.stockdetails;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TradingCompanyController {
    private TradingCompanyRepository repository;

    TradingCompanyController(TradingCompanyRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<TradingCompany> getAllTradingCompanies() {
        return repository.findAll();
    }

    @GetMapping(value = "/details/{ticker}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TradingCompany> getTradingCompanyByTicker(@PathVariable String ticker) {
        return repository.findByTicker(ticker);
    }

}

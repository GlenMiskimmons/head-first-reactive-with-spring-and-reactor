package io.spring.workshop.stockdetails;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class TradingCompanyCommandLineRunner implements CommandLineRunner {
    private TradingCompanyRepository repository;

    TradingCompanyCommandLineRunner(TradingCompanyRepository repository) {
        this.repository = repository;
    }
    @Override
    public void run(String... args) throws Exception {
        Flux.just(new TradingCompany("Pivotal Software", "PVTL"),
                new TradingCompany("Dell Technologies", "DELL"),
                new TradingCompany("Google", "GOOG"),
                new TradingCompany("Microsoft", "MSFT"),
                new TradingCompany("Oracle", "ORCL"),
                new TradingCompany("Red Hat", "RHT"),
                new TradingCompany("Vmware", "VMW"))
                .flatMap(repository::save)
                .then()
                .block();
    }
}

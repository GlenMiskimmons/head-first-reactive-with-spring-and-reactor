package io.spring.workshop.stockquotes;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StockQuotesApplicationTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void fetchQuotes() {
		List<Quote> result =
				webTestClient
						.get().uri("/quotes")
						.accept(MediaType.APPLICATION_STREAM_JSON)
						.exchange()
						.expectStatus().isOk()
						.expectHeader().contentType(MediaType.APPLICATION_STREAM_JSON)
						.returnResult(Quote.class)
						.getResponseBody()
						.take(20)
						.collectList()
						.block();

		assertThat(result).allSatisfy(quote -> assertThat(quote.getPrice()).isPositive());
	}

}
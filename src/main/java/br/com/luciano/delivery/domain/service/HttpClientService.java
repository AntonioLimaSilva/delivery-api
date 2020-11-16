package br.com.luciano.delivery.domain.service;

import br.com.luciano.delivery.api.model.PaymentResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Service
public class HttpClientService {

    private final RestTemplate restTemplate;

    public PaymentResponse find(String number) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange("http://payment/payment/payments/{number}", HttpMethod.GET, entity,
                PaymentResponse.class, number).getBody();
    }

}

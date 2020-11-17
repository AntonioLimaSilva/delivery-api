package br.com.luciano.delivery.domain.service;

import br.com.luciano.delivery.api.model.PaymentResponse;
import br.com.luciano.delivery.core.properties.BundleProperties;
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
    private final BundleProperties bundleProperties;

    public PaymentResponse find(String number) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        return restTemplate.exchange(bundleProperties.getPaymentUrl(), HttpMethod.GET, new HttpEntity<>(headers),
                    PaymentResponse.class, number).getBody();
    }

}

package br.com.luciano.delivery.core.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class BundleProperties {

    @Value("${luciano.payment.url}")
    private String paymentUrl;

}

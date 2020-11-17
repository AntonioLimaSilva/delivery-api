package br.com.luciano.delivery.core.client;

import br.com.luciano.delivery.api.model.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("payment")
public interface PaymentClient {

    @GetMapping("/luclima23/payments/{number}")
    PaymentResponse getPayment(@PathVariable String number);

}

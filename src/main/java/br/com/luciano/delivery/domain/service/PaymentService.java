package br.com.luciano.delivery.domain.service;

import br.com.luciano.delivery.domain.exception.FormaPagamentoNaoEncontradaException;
import br.com.luciano.delivery.domain.entity.PaymentEntity;
import br.com.luciano.delivery.domain.repository.PaymentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public PaymentEntity save(PaymentEntity paymentEntity) {
        return this.paymentRepository.save(paymentEntity);
    }

    public List<PaymentEntity> findAll() {
        return this.paymentRepository.findAll();
    }

    @Transactional
    public PaymentEntity findByIdOrFail(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new FormaPagamentoNaoEncontradaException(id));
    }

    @Transactional
    public PaymentEntity update(Long id, PaymentEntity paymentEntity) {
        PaymentEntity paymentEntitySave = this.findByIdOrFail(id);

        BeanUtils.copyProperties(paymentEntity, paymentEntitySave, "id");

        return this.save(paymentEntitySave);
    }

    @Transactional
    public void delete(Long id) {
        PaymentEntity paymentEntity = this.findByIdOrFail(id);

        this.paymentRepository.delete(paymentEntity);
    }

}

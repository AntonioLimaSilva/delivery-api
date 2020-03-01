package br.com.luciano.delivery.domain.service;

import br.com.luciano.delivery.domain.exception.FormaPagamentoNaoEncontradaException;
import br.com.luciano.delivery.domain.model.FormaPagamento;
import br.com.luciano.delivery.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroFormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return this.formaPagamentoRepository.save(formaPagamento);
    }

    public List<FormaPagamento> buscarTodas() {
        return this.formaPagamentoRepository.findAll();
    }

    @Transactional
    public FormaPagamento buscarPorId(Long idPagamento) {
        return formaPagamentoRepository.findById(idPagamento).orElseThrow(() -> new FormaPagamentoNaoEncontradaException(idPagamento));
    }

    @Transactional
    public FormaPagamento atualizar(Long idPagamento, FormaPagamento formaPagamento) {
        FormaPagamento formaPagamentoSalva = this.buscarPorId(idPagamento);

        BeanUtils.copyProperties(formaPagamento, formaPagamentoSalva, "id");

        return this.salvar(formaPagamentoSalva);
    }

    @Transactional
    public void excluir(Long idPagamento) {
        FormaPagamento formaPagamento = this.buscarPorId(idPagamento);

        this.formaPagamentoRepository.delete(formaPagamento);
    }

}

package br.com.luciano.delivery.api.handler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada");

    private final String url;
    private final String title;

    ProblemType(String path, String title) {
        this.url = "http://baraodesobral.com.br".concat(path);
        this.title = title;
    }
}

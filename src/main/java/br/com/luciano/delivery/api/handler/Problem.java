package br.com.luciano.delivery.api.handler;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;
}

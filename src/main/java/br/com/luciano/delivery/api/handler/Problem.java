package br.com.luciano.delivery.api.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;
    private List<Field> fields;

    @Builder
    @Getter
    @AllArgsConstructor
    public static class Field {
        private String name;
        private String message;
    }
}

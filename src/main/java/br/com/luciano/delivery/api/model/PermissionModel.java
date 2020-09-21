package br.com.luciano.delivery.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionModel {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "ADMIN")
    private String name;

}

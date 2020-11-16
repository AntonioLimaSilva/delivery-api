package br.com.luciano.delivery.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class OrderModel {

    @ApiModelProperty(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
    private String id;

    @ApiModelProperty(example = "298.90")
    private BigDecimal subtotal;

    @ApiModelProperty(example = "10.00")
    private BigDecimal shippingFee;

    @ApiModelProperty(example = "308.90")
    private BigDecimal total;

    @ApiModelProperty(example = "CRIADO")
    private String status;

    @ApiModelProperty(example = "2019-12-01T20:34:04Z")
    private OffsetDateTime createBy;

    @ApiModelProperty(example = "2019-12-01T20:35:10Z")
    private OffsetDateTime confirmationAt;

    @ApiModelProperty(example = "2019-12-01T20:55:30Z")
    private OffsetDateTime deliveryAt;

    @ApiModelProperty(example = "2019-12-01T20:35:00Z")
    private OffsetDateTime cancellationAt;

    private RestaurantResumeModel restaurant;
    private UserModel user;
    private PaymentModel payment;
    private AddressModel address;
    private List<ItemModel> items;

}

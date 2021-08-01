package org.kds.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class PaymentEvent {

    @JsonProperty
    private String paymentId;
    @JsonProperty
    private BigDecimal amount;
    @JsonProperty
    private String paymentType;


    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PaymentEvent.class.getSimpleName() + "[", "]")
                .add("paymentId='" + paymentId + "'")
                .add("amount=" + amount)
                .add("paymentType='" + paymentType + "'")
                .toString();
    }
}

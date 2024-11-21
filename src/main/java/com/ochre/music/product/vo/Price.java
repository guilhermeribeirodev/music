package com.ochre.music.product.vo;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Embeddable
public class Price implements Serializable, Comparable<Price> {
    private BigDecimal amount;
    private Currency currency; // Currency is an enum or another value object

    public Price(BigDecimal amount, Currency currency) {
        this.currency = Objects.requireNonNull(currency);
        this.amount = Objects.requireNonNull(amount).setScale(currency.getScale(), currency.getRoundingMode());
    }

    protected Price(){}

    public Price add(Price other) {
        assertSameCurrency(other);
        return new Price(amount.add(other.amount), currency);
    }

    public Price subtract(Price other) {
        assertSameCurrency(other);
        return new Price(amount.subtract(other.amount), currency);
    }

    private void assertSameCurrency(Price other) {
        if (!other.currency.equals(this.currency)) {
            throw new IllegalArgumentException("Price objects must have the same currency");
        }
    }

    @Override
    public int compareTo(Price price) {
        return 0;
    }

    public enum Currency {
        GBP, EUR, USD;

        public int getScale() {
            return 2;
        }

        public RoundingMode getRoundingMode() {
            return RoundingMode.CEILING;
        }
    }
}

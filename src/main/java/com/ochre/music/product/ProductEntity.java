package com.ochre.music.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Calendar;

@Entity
@Table(name = "product")
@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;
    private Distribution distribution;
    private MediaFormat mediaFormat;
    private Price price;
    private Calendar releaseDate;
    private String productGroupTitle;
    private Calendar productGroupReleaseDate;
    private String tags;

    public ProductEntity() {

    }

    enum Distribution {
        PHYSICAL, DIGITAL
    }

    enum Price {
        GBP, EUR, USD
    }

    enum MediaFormat {
        VINYL, CD, CASSETE, MP3, WAV, WAV24
    }

    public void updatePrice(Price price){
        this.price = price;
    }
}

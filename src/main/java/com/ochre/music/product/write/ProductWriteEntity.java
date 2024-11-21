package com.ochre.music.product.write;

import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;

@Entity
@Table(name = "product")
@Builder()
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ProductWriteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    private Long id;

    private String title;
    private Distribution distribution;
    private MediaFormat mediaFormat;
    private Price price;
    private Calendar releaseDate;
    private String productGroupTitle;
    private Calendar productGroupReleaseDate;
    private String tags;

    public ProductWriteEntity() {

    }

    public enum Distribution {
        PHYSICAL, DIGITAL
    }

    public enum Price {
        GBP, EUR, USD
    }

    public enum MediaFormat {
        VINYL, CD, CASSETE, MP3, WAV, WAV24
    }

    public void updatePrice(Price price){
        this.price = price;
    }
}

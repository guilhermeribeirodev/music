package com.ochre.music.product;

import com.ochre.music.product.vo.Price;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    private BigInteger id;

    @NotBlank(message = "Product title cannot be empty")
    @Pattern(regexp = "^[A-Za-z]+$")
    private String title;

    @Enumerated(EnumType.STRING)
    private Distribution distribution;

    @Enumerated(EnumType.STRING)
    private MediaFormat mediaFormat;

    @Embedded
    private Price price;

    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @Column(name = "group_title")
    private String productGroupTitle;

    @Column(name = "group_release_date")
    @Temporal(TemporalType.DATE)
    private Calendar productGroupReleaseDate;

    @OneToMany
    private List<ProductTag> tags;

    public enum Distribution {
        PHYSICAL, DIGITAL
    }

    public enum MediaFormat {
        VINYL, CD, CASSETE, MP3, WAV, WAV24
    }

    public void updatePrice(Price price){
        this.price = price;
    }
}

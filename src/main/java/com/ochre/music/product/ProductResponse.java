package com.ochre.music.product;

import com.ochre.music.product.vo.Price;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigInteger;
import java.util.Calendar;

@Data
@Builder
@Getter
public class ProductResponse {

    BigInteger id;

    @Pattern(regexp = "^[A-Za-z0-9]+$ ")
    @NotEmpty(message = "{required.field}")
    @Size(min = 1, max = 200, message = "{invalid.field}")
    String title;

    @NotEmpty(message = "{required.field}")
    @Size(min = 1, max = 20, message = "{invalid.field}")
    String distribution;

    @NotEmpty(message = "{required.field}")
    @Size(min = 1, max = 20, message = "{invalid.field}")
    String mediaFormat;

    @PositiveOrZero(message = "{invalid.field}")
    Price price;

    @FutureOrPresent
    Calendar releaseDate;

    @NotEmpty(message = "{required.field}")
    @Size(min = 1, max = 200, message = "{invalid.field}")
    String productGroupTitle;

    Calendar productGroupReleaseDate;

    @NotEmpty(message = "{required.field}")
    @Size(min = 1, max = 200, message = "{invalid.field}")
    String tags;

}

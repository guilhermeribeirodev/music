package com.ochre.music.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ochre.music.product.vo.Price;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    //BigInteger id;

    @Pattern(regexp = "^[A-Za-z0-9]+$")
    @NotEmpty(message = "{required.field}")
    @Size(min = 1, max = 200, message = "{invalid.field}")
    String title;

    @NotEmpty(message = "{required.field}")
    @Size(min = 1, max = 20, message = "{invalid.field}")
    String distribution;

    @NotEmpty(message = "{required.field}")
    @Size(min = 1, max = 20, message = "{invalid.field}")
    String mediaFormat;

    @JsonProperty("price")
    Price price;

    @FutureOrPresent
    @JsonFormat(pattern="yyyy-MM-dd")
    Calendar releaseDate;

    @NotEmpty(message = "{required.field}")
    @Size(min = 1, max = 200, message = "{invalid.field}")
    String productGroupTitle;

    @FutureOrPresent
    @JsonFormat(pattern="yyyy-MM-dd")
    Calendar productGroupReleaseDate;

    @JsonProperty("tags")
    List<String> tags;

}

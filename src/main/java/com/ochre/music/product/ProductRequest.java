package com.ochre.music.product;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotEmpty(message = "{required.field}")
    @Size(min = 1, max = 200, message = "{invalid.field}")
    String title;

//    @NotEmpty(message = "{required.field}")
//    @Size(min = 1, max = 20, message = "{invalid.field}")
//    String distribution;
//
//    @NotEmpty(message = "{required.field}")
//    @Size(min = 1, max = 20, message = "{invalid.field}")
//    String mediaFormat;
//
//    @PositiveOrZero(message = "{invalid.field}")
//    Double price;
//
//    @FutureOrPresent
//    Calendar releaseDate;
//
//    @NotEmpty(message = "{required.field}")
//    @Size(min = 1, max = 200, message = "{invalid.field}")
//    String productGroupTitle;
//
//    Calendar productGroupReleaseDate;
//
//    @NotEmpty(message = "{required.field}")
//    @Size(min = 1, max = 200, message = "{invalid.field}")
//    String tags;

}

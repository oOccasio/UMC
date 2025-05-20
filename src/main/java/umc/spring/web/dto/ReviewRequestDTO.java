package umc.spring.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.spring.validation.annotation.ExistStore;

public class ReviewRequestDTO {

    @Getter
    public static class addReviewDTO{

        @ExistStore
        Long storeId;

        @Size(min = 1, max = 50)
        String body;

        @NotNull
        @Min(1)
        @Max(5)
        Float score;
    }
}

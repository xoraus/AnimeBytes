package io.xoraus.CyberScribeHub.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer categoryId;
    @NotBlank
    @Size(min = 4,message = "Category title need to have at least 4 characters")
    private String categoryTitle;

    @NotBlank
    @Size(min = 10, message = "Category description need to have at least is 10 characters")
    private String categoryDescription;

}

package hello.mvc_item_service.item.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ItemSave {

    @NotEmpty
    private String name;
    @NotNull
    @Range(min = 1000, max = 1_000_000)
    private Integer price;
    @NotNull
    private Integer quantity;

    public ItemSave(String name, Integer price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

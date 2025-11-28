package hello.mvc_item_service.item.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemEdit {
    @NotNull
    private Long id;
    @NotEmpty
    private String name;
    @NotNull //수정할 땐 금액제한 없음
    private Integer price;
    @NotNull
    private Integer quantity;
}

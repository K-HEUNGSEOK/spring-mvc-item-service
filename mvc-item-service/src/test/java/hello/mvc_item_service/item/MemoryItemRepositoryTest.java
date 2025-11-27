package hello.mvc_item_service.item;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MemoryItemRepositoryTest {
    ItemRepository itemRepository = new MemoryItemRepository();
    @Test
    //조회, 저장, 업데이트 테스트
    void itemTets(){
        Item item = new Item("냉장고", 10000, 1);
        itemRepository.save(item);
        Item updateItem = new Item("에어컨", 20000, 2);

        itemRepository.update(item.getItemId(), updateItem);

        Item findItem = itemRepository.findById(item.getItemId());
        assertThat(findItem.getItemName()).isEqualTo(updateItem.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateItem.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateItem.getQuantity());
    }
}
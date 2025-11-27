package hello.mvc_item_service.item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    Item save(Item item);
    Item findById(Long itemId);
    List<Item> findByAll();
    void clear();
    void update(Long itemId, Item updateItem);
}

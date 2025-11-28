package hello.mvc_item_service.item.repository;

import hello.mvc_item_service.item.model.Item;
import java.util.List;

public interface ItemRepository {
    Item save(Item item);

    Item findById(Long itemId);

    List<Item> findByAll();

    void clear();

    void update(Long itemId, Item updateItem);

    void delete(Long itemId);
}

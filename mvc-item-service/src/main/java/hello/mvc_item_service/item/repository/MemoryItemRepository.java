package hello.mvc_item_service.item.repository;

import hello.mvc_item_service.item.model.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryItemRepository implements ItemRepository{
    private static final Map<Long, Item> itemStore = new ConcurrentHashMap<>();
    private static Long sequence = 0L;

    @Override
    public Item save(Item item) {
        item.setId(++sequence);
        itemStore.put(item.getId(), item);
        return item;
    }

    @Override
    public Item findById(Long itemId) {
        return itemStore.get(itemId);
    }

    @Override
    public List<Item> findByAll() {
        return new ArrayList<>(itemStore.values());
    }

    @Override
    public void clear() {
        itemStore.clear();
    }

    @Override
    public void update(Long itemId, Item updateItem) {
        Item findItem = itemStore.get(itemId);
        findItem.setName(updateItem.getName());
        findItem.setPrice(updateItem.getPrice());
        findItem.setQuantity(updateItem.getQuantity());
    }

    @Override
    public void delete(Long itemId) {
        itemStore.remove(itemId);
    }
}

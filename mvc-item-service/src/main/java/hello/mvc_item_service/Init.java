package hello.mvc_item_service;

import hello.mvc_item_service.item.model.Item;
import hello.mvc_item_service.item.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Init {

    private final ItemRepository itemRepository;

    @PostConstruct
    public void init(){
        Item itemA = new Item("냉장고", 10000, 1);
        Item itemB = new Item("에어컨", 20000, 2);
        itemRepository.save(itemA);
        itemRepository.save(itemB);
    }
}

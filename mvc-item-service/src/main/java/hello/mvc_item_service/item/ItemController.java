package hello.mvc_item_service.item;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
@Slf4j
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;
    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findByAll();
        model.addAttribute("items" , items);
        return "items/items";
    }
}

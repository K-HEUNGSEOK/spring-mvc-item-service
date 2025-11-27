package hello.mvc_item_service.item;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "items/item";
    }

    @GetMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "/items/editForm";
    }
    @PostMapping("/{itemId}/edit")
    public String update(@PathVariable Long itemId , @ModelAttribute Item item) {
        itemRepository.update(itemId,item);
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/addForm")
    public String add(Model model){
        model.addAttribute("item", new Item());
        return "items/addForm";
    }

    @PostMapping("/addForm")
    public String save(@ModelAttribute Item item, RedirectAttributes redirectAttributes){
        itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", item.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/items/{itemId}";
    }
}

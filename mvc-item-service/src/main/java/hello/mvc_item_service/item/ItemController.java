package hello.mvc_item_service.item;

import hello.mvc_item_service.item.dto.ItemEdit;
import hello.mvc_item_service.item.dto.ItemSave;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public String items(Model model) {
        List<Item> items = itemRepository.findByAll();
        model.addAttribute("items", items);
        return "items/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "items/item";
    }

    @GetMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "/items/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String update(@PathVariable Long itemId, @Validated @ModelAttribute("item") ItemEdit itemEdit,
                         BindingResult bindingResult) {
        if (itemEdit.getPrice() != null && itemEdit.getQuantity() != null) {
            int result = itemEdit.getPrice() * itemEdit.getQuantity();
            if (result < 10000) {
                bindingResult.reject("totalPrice", new Object[]{10000, result}, null);
            }
        }
        if (bindingResult.hasErrors()){
            return "/items/editForm";
        }
        Item item = new Item(itemEdit.getName(), itemEdit.getPrice(), itemEdit.getQuantity());
        itemRepository.update(itemId, item);
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/addForm")
    public String add(Model model) {
        model.addAttribute("item", new Item());
        return "items/addForm";
    }

    @PostMapping("/addForm")
    public String save(@Validated @ModelAttribute("item") ItemSave itemSave, BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        //글로벌 예외
        if (itemSave.getPrice() != null && itemSave.getQuantity() != null) {
            int result = itemSave.getPrice() * itemSave.getQuantity();
            if (result < 10000) {
                bindingResult.reject("totalPrice", new Object[]{10000, result}, null);
            }
        }

        //검증 추가
        if (bindingResult.hasErrors()) {
            return "items/addForm";
        }
        Item item = new Item(itemSave.getName(), itemSave.getPrice(), itemSave.getQuantity());
        itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", item.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/items/{itemId}";
    }
}

package hello.mvc_item_service.util;

import hello.mvc_item_service.item.model.Item;
import hello.mvc_item_service.item.repository.ItemRepository;
import hello.mvc_item_service.member.Member;
import hello.mvc_item_service.member.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Init {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        Member member = new Member("김흥석", "test", "test!");
        memberRepository.save(member);
        Item itemA = new Item("냉장고", 10000, 1);
        Item itemB = new Item("에어컨", 20000, 2);
        itemRepository.save(itemA);
        itemRepository.save(itemB);
    }
}

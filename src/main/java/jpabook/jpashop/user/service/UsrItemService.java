package jpabook.jpashop.user.service;

import jpabook.jpashop.common.domain.Category;
import jpabook.jpashop.common.domain.item.Item;
import jpabook.jpashop.user.repository.UsrItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UsrItemService {

    private final UsrItemRepository itemRepository;

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public List<Item> findItems(String category) {
        return itemRepository.findAll(category);
    }

    public List<Category> findCategories() {
        return itemRepository.findCategories();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}

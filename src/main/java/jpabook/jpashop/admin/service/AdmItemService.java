package jpabook.jpashop.admin.service;

import jpabook.jpashop.admin.repository.AdmItemRepository;
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
public class AdmItemService {

    private final AdmItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {itemRepository.save(item);}

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}

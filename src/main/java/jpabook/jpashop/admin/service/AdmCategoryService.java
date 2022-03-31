package jpabook.jpashop.admin.service;

import jpabook.jpashop.admin.repository.AdmCategoryRepository;
import jpabook.jpashop.common.domain.Category;
import jpabook.jpashop.common.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdmCategoryService {

    private final AdmCategoryRepository categoryRepository;

    public List<Category> findCategories() { return categoryRepository.findAll();}

    public Category findOne(Long categoryId) { return categoryRepository.findOne(categoryId);}

    @Transactional
    public void saveCategories (Category category) { categoryRepository.save(category); }

    @Transactional
    public void updateCategory(Long categoryId, String name, String koName, Long parentId) {
        Category findCategory = categoryRepository.findOne(categoryId);

        if (parentId != null) {
            Category parent = new Category();
            parent.setId(parentId);
            findCategory.setParent(parent);
        } else {
            findCategory.setParent(null);
        }

        findCategory.setName(name);
        findCategory.setKoName(koName);
    }
}

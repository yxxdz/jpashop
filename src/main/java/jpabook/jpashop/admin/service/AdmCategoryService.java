package jpabook.jpashop.admin.service;

import jpabook.jpashop.admin.repository.AdmCategoryRepository;
import jpabook.jpashop.common.domain.Category;
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
}

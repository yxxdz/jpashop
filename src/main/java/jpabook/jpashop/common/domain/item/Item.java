package jpabook.jpashop.common.domain.item;

import jpabook.jpashop.common.domain.Category;
import jpabook.jpashop.common.exception.CustomException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static jpabook.jpashop.common.exception.ErrorCode.NEED_MORE_STOCK;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @Column(name = "dtype", insertable=false, updatable = false)
    private String dtype;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    // ==비즈니스 로직== /

    /**
     * stock 증가
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0) {
            throw new CustomException(NEED_MORE_STOCK);
    }
        this.stockQuantity = restStock;
    }
}

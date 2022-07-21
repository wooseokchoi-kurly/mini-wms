package com.fpd.miniwms.domain;

import com.fpd.miniwms.domain.base.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
@Table(name = "stock")
public class Stock extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("재고 아이디")
    @Column(name = "stock_id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @Comment("아이템 아이디")
    @JoinColumn(name = "item_id", foreignKey = @ForeignKey(name = "fk_item_stock"))
    private Item item;

    @Comment("재고 수량")
    @Column(name = "stock_qty")
    private Integer stockQty;
}

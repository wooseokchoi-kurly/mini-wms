package com.fpd.miniwms.domain;

import com.fpd.miniwms.domain.common.BaseEntity;
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
@Table(name = "item")
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이템 아이디")
    @Column(name = "item_id")
    private Long id;

    @Comment("아이템 코드")
    @Column(name = "item_code")
    private String itemCode;

    @Comment("아이템 이름")
    @Column(name = "item_name")
    private String itemName;

    @Comment("아이템 가격")
    @Column(name = "item_price")
    private Integer itemPrice;

    @Comment("아이템 사용 여부")
    @Column(name = "is_item_use")
    private Boolean isItemUse = false;

}

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
@Table(name = "inbound_detail")
public class InboundDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("입고 상세 아이디")
    @Column(name = "inbound_detail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("입고 헤더 아이디")
    @JoinColumn(name = "inbound_header_id", foreignKey = @ForeignKey(name = "fk_inbound_header_inbound_detail"))
    private InboundHeader inboundHeader;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("아이템 아이디")
    @JoinColumn(name = "item_id", foreignKey = @ForeignKey(name = "fk_item_inbound_detail"))
    private Item item;

    @Comment("입고 수량")
    @Column(name = "inbound_qty")
    private Integer inboundQty;
}

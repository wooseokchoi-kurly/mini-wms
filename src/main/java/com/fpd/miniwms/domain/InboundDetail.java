package com.fpd.miniwms.domain;

import com.fpd.miniwms.domain.base.BaseDeletableEntity;
import com.fpd.miniwms.domain.base.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
@SQLDelete(sql = "UPDATE inbound_detail SET is_delete = true WHERE inbound_detail_id = ?")
@Table(name = "inbound_detail")
public class InboundDetail extends BaseDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("입고 상세 아이디")
    @Column(name = "inbound_detail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("입고 헤더 아이디")
    @JoinColumn(name = "inbound_header_id", foreignKey = @ForeignKey(name = "fk_inbound_header_inbound_detail"))
    private InboundHeader inboundHeader;

    @OneToOne(fetch = FetchType.EAGER)
    @Comment("아이템 아이디")
    @JoinColumn(name = "item_id", foreignKey = @ForeignKey(name = "fk_item_inbound_detail"))
    private Item item;

    @Comment("입고 수량")
    @Column(name = "inbound_qty")
    private Integer inboundQty;
}

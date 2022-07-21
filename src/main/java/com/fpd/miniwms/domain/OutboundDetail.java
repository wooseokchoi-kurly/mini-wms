package com.fpd.miniwms.domain;

import com.fpd.miniwms.domain.base.BaseDeletableEntity;
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
@SQLDelete(sql = "UPDATE outbound_detail SET is_delete = true WHERE outbound_detail_id = ?")
@Table(name = "outbound_detail")
public class OutboundDetail extends BaseDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("출고 상세 아이디")
    @Column(name = "outbound_detail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("출고 헤더 아이디")
    @JoinColumn(name = "outbound_header_id", foreignKey = @ForeignKey(name = "fk_outbound_header_outbound_detail"))
    private OutboundHeader outboundHeader;

    @OneToOne(fetch = FetchType.EAGER)
    @Comment("아이템 아이디")
    @JoinColumn(name = "item_id", foreignKey = @ForeignKey(name = "fk_item_outbound_detail"))
    private Item item;

    @Comment("출고 수량")
    @Column(name = "outbound_qty")
    private Integer outboundQty;
}

package com.fpd.miniwms.domain;

import com.fpd.miniwms.domain.base.BaseDeletableEntity;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
@SQLDelete(sql = "UPDATE outbound_header SET is_delete = true WHERE outbound_header_id = ?")
@Table(name = "outbound_header")
public class OutboundHeader extends BaseDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("출고 헤더 아이디")
    @Column(name = "outbound_header_id")
    private Long id;

    @Comment("출고 담당자(pic, person in charge)")
    @Column(name = "outbound_pic")
    private String outboundPic;

    @Comment("출고 완료 여부")
    @Column(name = "is_outbound_complete")
    private Boolean isOutboundComplete = false;
}

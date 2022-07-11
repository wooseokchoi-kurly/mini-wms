package com.fpd.miniwms.domain;

import com.fpd.miniwms.domain.common.BaseDeletableEntity;
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

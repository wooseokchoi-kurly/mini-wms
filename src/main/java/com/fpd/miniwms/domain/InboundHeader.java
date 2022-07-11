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
@Table(name = "inbound_header")
public class InboundHeader extends BaseDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("입고 헤더 아이디")
    @Column(name = "inbound_header_id")
    private Long id;

    @Comment("입고 담당자(pic, person in charge)")
    @Column(name = "inbound_pic")
    private String inboundPic;

    @Comment("입고 완료 여부")
    @Column(name = "is_inbound_complete")
    private Boolean isInboundComplete = false;
}

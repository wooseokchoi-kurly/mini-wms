package com.fpd.miniwms.domain.common;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class BaseDeletableEntity extends BaseEntity{

    @Comment("삭제 여부")
    @Column(name = "is_delete")
    private Boolean isDelete = false;
}

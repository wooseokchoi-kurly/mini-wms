package com.fpd.miniwms.domain.base;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@Where(clause = "is_delete = false")
@MappedSuperclass
public class BaseDeletableEntity extends BaseEntity{

    @Comment("삭제 여부")
    @Column(name = "is_delete")
    private Boolean isDelete = false;
}

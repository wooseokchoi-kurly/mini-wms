package com.fpd.miniwms.domain.common;

import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseEntity implements Serializable {

    @CreatedDate
    @Comment("생성일")
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Comment("수정일")
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
}

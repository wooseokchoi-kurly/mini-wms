package com.fpd.miniwms.domain.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

    @JsonIgnore
    @CreatedDate
    @Comment("생성일")
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @JsonIgnore
    @LastModifiedDate
    @Comment("수정일")
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
}

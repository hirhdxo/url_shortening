package com.task.musinsa.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Getter
@ToString
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
@EqualsAndHashCode(of = "id")
public abstract class BaseEntity {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 생성 날짜
     */
    @Setter
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssZ", timezone = "UTC")
    Instant created;
}

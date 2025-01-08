package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BaseEntity {

    @Setter
    protected Long id;

    protected LocalDateTime createdAt;

    public BaseEntity(Long id) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
    }

}

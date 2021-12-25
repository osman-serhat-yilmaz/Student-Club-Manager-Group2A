package com.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Application extends BaseEntity{

    @Column(name = "senderid", columnDefinition = "BINARY(16)", insertable = false, updatable = false)
    private UUID senderID;
    @Column(name = "recipientid", columnDefinition = "BINARY(16)", insertable = false, updatable = false)
    private UUID recipientID;
    private String applicationStatus;
    private UUID clubID;

}

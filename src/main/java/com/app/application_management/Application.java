package com.app.application_management;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Application {
    @Id
    private UUID id;

    private UUID senderID;
    private UUID recipientID;
    private String applicationStatus;
    private UUID clubID;

}

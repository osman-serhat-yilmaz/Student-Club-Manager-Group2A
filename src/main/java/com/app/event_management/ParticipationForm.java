package com.app.event_management;

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
public class ParticipationForm {
    @Id
    private UUID id;
    private UUID userId;
    private UUID eventId;
    private String question;
    private String answer;

}

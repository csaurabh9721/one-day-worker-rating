package com.rating_service.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ratings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "giver_id", nullable = false)
    private Long giverId;

    @Column(name = "receiver_id", nullable = false)
    private Long receiverId;

    @Column(name = "score", nullable = false)
    private int score;

    @Column(name = "comment", length = 500)
    private String comment;
}

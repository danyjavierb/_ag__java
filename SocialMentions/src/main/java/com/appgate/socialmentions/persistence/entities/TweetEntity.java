package com.appgate.socialmentions.persistence.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "analyzed_tweets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetEntity implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    @Column()
    Double tweeterScore;

    @Column()
    String message;

    @Column()
    String tweeterUrl;

    @Column()
    String tweeterAccount;
}

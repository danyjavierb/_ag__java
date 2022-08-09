package com.appgate.socialmentions.persistence.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "analyzed_fb_posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    @Column()
    Double facebookScore;

    @Column()
    String message;

    @Column()
    String facebookAccount;
}

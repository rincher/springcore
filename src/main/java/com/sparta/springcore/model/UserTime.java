package com.sparta.springcore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class UserTime {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false)
    private long totalTime;

    @Column(nullable = false, columnDefinition = "bigint default 0")
    private long totalCall;

    public UserTime(User user, long totalTime, long totalCall){
        this.user = user;
        this.totalTime = totalTime;
        this.totalCall = totalCall;
    }
    public void updateTotalTime(long totalTime, long totalCall){
        this.totalTime = totalTime;
        this.totalCall = totalCall;
    }
}

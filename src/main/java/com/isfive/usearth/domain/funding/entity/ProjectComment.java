package com.isfive.usearth.domain.funding.entity;

import com.isfive.usearth.domain.member.entity.Member;

import jakarta.persistence.*;

@Entity
public class ProjectComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;
}

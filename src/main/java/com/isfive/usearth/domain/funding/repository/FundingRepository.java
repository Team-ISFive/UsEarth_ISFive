package com.isfive.usearth.domain.funding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isfive.usearth.domain.funding.entity.Funding;

public interface FundingRepository extends JpaRepository<Funding, Long> {
}

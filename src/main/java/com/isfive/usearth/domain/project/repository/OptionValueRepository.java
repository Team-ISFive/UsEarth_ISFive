package com.isfive.usearth.domain.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isfive.usearth.domain.project.entity.OptionValue;

public interface OptionValueRepository extends JpaRepository<OptionValue, Long> {
    OptionValue findByValueAndReward_Id(String optionValue, Long rewardId);
}

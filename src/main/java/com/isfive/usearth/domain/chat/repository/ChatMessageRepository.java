package com.isfive.usearth.domain.chat.repository;

import com.isfive.usearth.domain.chat.entity.ChatMessage;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends CrudRepository<ChatMessage,Long> {
}

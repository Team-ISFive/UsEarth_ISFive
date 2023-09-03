package com.isfive.usearth.domain.chat.service;

import com.isfive.usearth.domain.chat.RedisSubscriber;
import com.isfive.usearth.domain.chat.dto.ChatRoomResponse;
import com.isfive.usearth.domain.chat.entity.ChatRoom;
import com.isfive.usearth.domain.chat.entity.ChatRoomAppend;
import com.isfive.usearth.domain.chat.repository.ChatRoomAppendRepository;
import com.isfive.usearth.domain.chat.repository.ChatRoomRepository;
import com.isfive.usearth.domain.member.entity.Member;
import com.isfive.usearth.domain.member.repository.MemberRepository;
import com.isfive.usearth.web.chat.dto.ChatMessageDto;
import com.isfive.usearth.web.chat.dto.ChatRoomDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final RedisTemplate redisTemplate;
    private final RedisMessageListenerContainer redisMessageListener;
    private final RedisSubscriber redisSubscriber;
    private final MemberRepository memberRepository;
    private final ChatRoomAppendRepository chatRoomAppendRepository;

    public void sendMessage(ChatMessageDto chatMessageDto) {
        if (ChatMessageDto.MessageType.ENTER.equals(chatMessageDto.getType())) {
            chatMessageDto.setMessage(chatMessageDto.getNickname() + "님이 입장하셨습니다.");
        }
        redisTemplate.convertAndSend(chatMessageDto.getRoomId(), chatMessageDto);
    }

    public ChatRoomResponse createChatRoom(ChatRoomDto chatRoomDto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberRepository.findByUsernameOrThrow(username);
        ChatRoom chatRoom = chatRoomRepository.save(chatRoomDto.toEntity());
        ChatRoomAppend chatRoomAppend = ChatRoomAppend.creatChatRoomAppend(member,chatRoom);
        chatRoomAppendRepository.save(chatRoomAppend);
        ChannelTopic topic = new ChannelTopic(Long.toString(chatRoom.getId()));
        redisMessageListener.addMessageListener(redisSubscriber,topic);
        return ChatRoomResponse.fromEntity(chatRoom);
    }

    public List<ChatRoomResponse> readAllChatRoom() {
        List<ChatRoomResponse> chatRoomList = new ArrayList<>();
        for (ChatRoom chatRoom: chatRoomRepository.findAll())
            chatRoomList.add(ChatRoomResponse.fromEntity(chatRoom));
        return chatRoomList;
    }

    public ChatRoomResponse readChatRoom(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElseThrow();
        return ChatRoomResponse.fromEntity(chatRoom);
    }
}

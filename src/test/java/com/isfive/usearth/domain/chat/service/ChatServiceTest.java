package com.isfive.usearth.domain.chat.service;

import com.isfive.usearth.domain.chat.dto.ChatRoomResponse;
import com.isfive.usearth.web.chat.dto.ChatRoomDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@ActiveProfiles("test")
class ChatServiceTest {

    @Autowired ChatService chatService;


    @Test
    void createChat() {
        //given
        ChatRoomDto chatRoomDto = new ChatRoomDto("채팅방","랄랄라");
        //when
        ChatRoomResponse chatRoomResponse = chatService.createChatRoom(chatRoomDto);
        //then
        assertThat(chatRoomResponse.getRoomName().equals("채팅방"));
    }

    @Test
    void readAllChat() {
        // given
        ChatRoomDto chatRoomDto = new ChatRoomDto("채팅방1","랄랄라");
        ChatRoomDto chatRoomDto2 = new ChatRoomDto("채팅방2","눈난나");
        chatService.createChatRoom(chatRoomDto);
        chatService.createChatRoom(chatRoomDto2);
        // when
        List<ChatRoomResponse> chatRoomList = chatService.readAllChatRoom();
        // then
        assertThat(chatRoomList,hasSize(2));

    }

    @Test
    void readChat() {
        // given
        ChatRoomDto chatRoomDto = new ChatRoomDto("채팅방1","랄랄라");
        ChatRoomDto chatRoomDto2 = new ChatRoomDto("채팅방2","눈난나");
        chatService.createChatRoom(chatRoomDto);
        chatService.createChatRoom(chatRoomDto2);

        // when
        ChatRoomResponse chatRoomResponse = chatService.readChatRoom(1L);

        // then
        assertThat(chatRoomResponse.getRoomName().equals("채팅방1"));

    }



}
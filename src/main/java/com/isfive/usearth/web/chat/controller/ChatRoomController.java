package com.isfive.usearth.web.chat.controller;

import com.isfive.usearth.domain.chat.dto.ChatRoomResponse;
import com.isfive.usearth.domain.chat.repository.ChatRoomRepository;
import com.isfive.usearth.domain.chat.service.ChatService;
import com.isfive.usearth.web.chat.dto.ChatRoomDto;
import com.isfive.usearth.web.chat.dto.ChatRoomRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatService chatService;

    @GetMapping("/rooms")
    public List<ChatRoomResponse> getAllRoom() {
        return chatService.readAllChatRoom();
    }

    @PostMapping("/room")
    public void createRoom( @RequestBody ChatRoomRequest request) {
        ChatRoomDto chatRoomDto = new ChatRoomDto(request);
        chatService.createChatRoom(chatRoomDto);
    }

    @GetMapping("/room/{roomId}")
    public ChatRoomResponse roomInfo(@PathVariable("roomId") Long id) {
        return chatService.readChatRoom(id);
    }
}

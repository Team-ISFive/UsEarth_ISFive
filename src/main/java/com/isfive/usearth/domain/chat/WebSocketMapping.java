package com.isfive.usearth.domain.chat;

import com.isfive.usearth.domain.chat.service.ChatService;
import com.isfive.usearth.web.chat.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebSocketMapping {
    private final ChatService chatService;

    @MessageMapping("/chat/message")
    public void sendChat(ChatMessageDto chatMessageDto) {
        log.info(chatMessageDto.toString());
        if (ChatMessageDto.MessageType.ENTER.equals(chatMessageDto.getType())) {
            chatMessageDto.setMessage(chatMessageDto.getNickName() + "님이 입장하셨습니다.");
        }
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        chatMessageDto.setTime(time);
        chatService.sendMessage(chatMessageDto);
    }

}

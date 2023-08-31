package com.isfive.usearth.web.chat.dto;

import com.isfive.usearth.domain.chat.entity.ChatMessage;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
public class ChatMessageDto  {
    public enum MessageType {
        ENTER, TALK
    }

    private String roomId;
    private String sender;
    @Setter
    private String message;
    @Setter
    private String time;
    private MessageType type;

    public ChatMessage toEntity() {


        return ChatMessage.builder()
                .nickname(this.sender)
                .message(this.message)
                .build();
    }
}

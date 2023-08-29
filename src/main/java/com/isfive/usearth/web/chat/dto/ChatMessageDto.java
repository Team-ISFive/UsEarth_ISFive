package com.isfive.usearth.web.chat.dto;

import com.isfive.usearth.domain.chat.entity.ChatMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ChatMessageDto {

    public enum MessageType {
        ENTER, TALK
    }

    private String roomId;
    private String nickName;
    @Setter
    private String message;
    @Setter
    private String time;
    private MessageType type;

    public ChatMessage toEntity() {

        return ChatMessage.builder()
                .nickname(this.nickName)
                .message(this.message)
                .build();
    }
}

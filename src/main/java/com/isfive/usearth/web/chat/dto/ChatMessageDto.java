package com.isfive.usearth.web.chat.dto;

import com.isfive.usearth.domain.chat.entity.ChatMessage;
import lombok.Data;
import lombok.Setter;


@Data
public class ChatMessageDto  {
    public enum MessageType {
        ENTER, TALK
    }

    private String roomId;
    private String nickname;
    @Setter
    private String message;
    @Setter
    private String time;
    private MessageType type;

    public ChatMessage toEntity() {


        return ChatMessage.builder()
                .nickname(this.nickname)
                .message(this.message)
                .build();
    }
}

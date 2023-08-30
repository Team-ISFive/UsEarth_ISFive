package com.isfive.usearth.web.chat.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;


@Data
public class ChatRoomRequest {

    @NotBlank(message = "채팅방 이름을 입력해주세요.")
    private String roomName;
    private String description;
}

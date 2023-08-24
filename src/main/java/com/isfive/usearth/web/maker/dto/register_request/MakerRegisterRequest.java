package com.isfive.usearth.web.maker.dto.register_request;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@NoArgsConstructor
public class MakerRegisterRequest {

    @NotBlank(message = "메이커명을 입력해주세요.")
    private String name;

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "전화번호를 입력해주세요.")
    private String phone;

}
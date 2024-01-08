package HelloCRUD.hello.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardFormDto {

    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;
    @NotEmpty(message = "내용은 필수 입력 값입니다.")
    private String content;
    private String writer;




}

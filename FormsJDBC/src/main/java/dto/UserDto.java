package dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String courseName;
    private Integer age;
}

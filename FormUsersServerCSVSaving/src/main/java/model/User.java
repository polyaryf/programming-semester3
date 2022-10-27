package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String courseName;
    private Integer age;
}
package dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subscriber {
    Integer id;
    String firstName;
    String lastName;
    Integer photoId;
    String tgUsername;
}

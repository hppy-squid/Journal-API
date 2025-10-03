package Journal.DTO;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
public class PostDto {
    private Long id;
    private String content;
    private String status;
    private LocalDateTime date;
    
}

package Journal.DTO;

import java.time.LocalDate;
import java.util.Map;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Data
public class StatisticsDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private Map<String, Double> statusPercentages; 

    
}

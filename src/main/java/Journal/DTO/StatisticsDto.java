package Journal.DTO;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.cglib.core.Local;

public class StatisticsDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private Map<String, Double> statusPercentages; 

    
}

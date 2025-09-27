package Journal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Journal.DTO.StatisticsDto;
import Journal.service.StatisticsService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;


    @GetMapping("/all")
    public ResponseEntity<StatisticsDto> statistics (@RequestParam LocalDate startDate,
                                                    @RequestParam LocalDate endDate) {
         StatisticsDto stats = statisticsService.getStatistics(startDate, endDate);
         return ResponseEntity.ok(stats);
         
    }
    
    
}

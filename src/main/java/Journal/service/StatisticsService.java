package Journal.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Journal.DTO.StatisticsDto;
import Journal.model.Post;
import Journal.repositories.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final PostRepository postRepository;

    public StatisticsDto getStatistics(LocalDate startDate, LocalDate endDate) {
       List<Post> posts = postRepository.findByDateBetween(startDate, endDate);

    long total = posts.size();
    Map<String, Double> percentages = new HashMap<>();

    if (total > 0) {
        percentages = posts.stream()
                .collect(Collectors.groupingBy(
                        post -> post.getStatus().name(),
                        Collectors.collectingAndThen(Collectors.counting(),
                                count -> (count * 100.0) / total)
                ));
    }

    StatisticsDto dto = new StatisticsDto();
    dto.setStartDate(startDate);
    dto.setEndDate(endDate);
    dto.setStatusPercentages(percentages);

    return dto;

    }
    
}

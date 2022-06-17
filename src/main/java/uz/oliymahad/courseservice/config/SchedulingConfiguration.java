package uz.oliymahad.courseservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import uz.oliymahad.courseservice.service.GroupService;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulingConfiguration {

    private final GroupService groupService ;

    @Scheduled(cron = "0 0 9 1 * *")
    void schedule() throws InterruptedException {
//        groupService.add();
    }

}

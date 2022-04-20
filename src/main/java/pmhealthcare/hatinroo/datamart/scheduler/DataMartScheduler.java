package pmhealthcare.hatinroo.datamart.scheduler;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pmhealthcare.hatinroo.datamart.service.DailyStatService;
import pmhealthcare.hatinroo.datamart.service.StorageService;
import pmhealthcare.hatinroo.datamart.task.DailyTaskService;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataMartScheduler {

    private final DailyTaskService dateTaskService;
    private final StorageService storageService;
    private final DailyStatService dailyStatService;

//    @Scheduled(fixedDelay = 10000)
//    public void alert() {
//        System.out.println("pulling check, 현재 시간 : {}" + new Date());
//    }


//    @Scheduled(cron = "0 0/5 * * * *")
//    public void saveToday() {
//        log.info("=============================  date batch start ===============================");
//        dateTaskService.saveDailyStatsInService();
//        log.info("=============================  date batch end ===============================");
//    }


    @Scheduled(cron = "0 1 0 * * *")
    public void saveDaily() {
        log.info("=============================  daily schedule start ===============================");
        dateTaskService.saveDailyStatsInService();
        log.info("=============================  daily schedule end ===============================");
    }

    @Scheduled(cron = "0 30 0 * * *")
    public void saveMemberStat() {
        log.info("=============================  daily schedule start ===============================");
        dailyStatService.setMemberStat();
        log.info("=============================  daily schedule end ===============================");
    }

    @Scheduled(cron = "0 0 1 * * *")
    public void saveSensorAtStorage() {
        log.info("=============================  daily schedule start ===============================");
        storageService.saveSensorData();
        log.info("=============================  daily schedule end ===============================");
    }

    @Scheduled(cron = "0 30 1 * * *")
    public void saveMemberHistAtStorage() {
        log.info("=============================  daily schedule start ===============================");
        storageService.saveMemberHist();
        log.info("=============================  daily schedule end ===============================");
    }


    @Scheduled(cron = "0 0 2 * * *")
    public void removePushs() {
        log.info("=============================  daily schedule start ===============================");
        storageService.removePushs();
        log.info("=============================  daily schedule end ===============================");
    }



    @Scheduled(cron = "0 30 2 * * *")
    public void saveMemberDailyStats() {
        log.info("=============================  daily schedule start ===============================");
        dailyStatService.setDailyAvgHist();
        log.info("=============================  daily schedule end ===============================");
    }


}

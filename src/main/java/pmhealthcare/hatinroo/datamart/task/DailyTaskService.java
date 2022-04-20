package pmhealthcare.hatinroo.datamart.task;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pmhealthcare.hatinroo.datamart.model.device.Device;
import pmhealthcare.hatinroo.datamart.model.device.MemberStatusHist;
import pmhealthcare.hatinroo.datamart.model.dto.MemberTimeStats;
import pmhealthcare.hatinroo.datamart.repository.device.DeviceRepository;
import pmhealthcare.hatinroo.datamart.service.*;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class DailyTaskService {

    private final DeviceRepository deviceRepository;

    private final DateStatsService dateStatsService;
    private final TimeStatsService timeStatsService;


    // 오늘 날짜
//    public void saveTodayStatsInService(){
//
//        log.info("====================================== today date stats ===========================================");
//
//        //date stats
//        List<Object[]> findTodayStatus =  dateStatsService.getToday();
//
//        if( null != findTodayStatus && 0 != findTodayStatus.size() ){
//
//            for(Object[] todayData: findTodayStatus){
//
//                Device findDevice = deviceRepository.findByNo(Long.parseLong(todayData[E_MemberStatsByDate.E_DEVICENO.getMnValue()].toString()));
//
//                if(null != findDevice){
//                    dateStatsService.saveStats(findDevice.getMemberNo(), findDevice.getNo(), todayData);
//                }
//            }
//        }
//
//
//        log.info("====================================== today time status ===========================================");
//
//        //time stats
//        List<Device> devices = deviceRepository.findAll();
//        if( null != devices && 0 != devices.size()) {
//            //for device
//            for (Device device : devices) {
//
//                // today hist list
//                List<MemberStatusHist> statusHists = timeStatsService.getToday(device.getNo());
//
//                if(null != statusHists && 0 != statusHists.size()){
//                    //get time data
//                    MemberTimeStats timeStats = timeStatsService.getTimeStats(device, statusHists);
//
//                    //save time date
//                    timeStatsService.saveMemberTimeStats(timeStats, statusHists.get(0).getRegdate().toLocalDate());
//                }
//
//
//            }
//        }
//
//    }







    // 일자 별 데이터
    public void saveDailyStatsInService(){


        log.info("====================================== daily date stats ===========================================");

        //date stats
        List<Object[]> findDailys =  dateStatsService.getDaily();

        if(null != findDailys && 0 != findDailys.size()){

            for(Object[] dailyData: findDailys){

                Device findDevice = deviceRepository.findByNo(Long.parseLong(dailyData[E_MemberStatsByDate.E_DEVICENO.getMnValue()].toString()));

                if(null != findDevice){
                    dateStatsService.saveStats(findDevice.getMemberNo(), findDevice.getNo(), dailyData);
                }
            }
        }


        log.info("====================================== daily time status ===========================================");

        // time stats
        // get device list
        List<Device> devices = deviceRepository.findAll();

        if( null != devices && 0 != devices.size()) {

            //for device
            for (Device device : devices) {

//                log.info("======== device : " + device.toString());

                //daily hist list
                List<MemberStatusHist> statusHists = timeStatsService.getDaily(device.getNo());

                if(null != statusHists && 0 != statusHists.size()){

                    List<MemberStatusHist> fullHist = timeStatsService.createStatusHistory(statusHists, device);

//                    if( null != fullHist && fullHist.size() > 0 && device.getNo() == 16){
//                        for ( MemberStatusHist me : fullHist){
//                            log.info(me.toString());
//
//                        }
//                    }

//                    log.info(" fullHist size  : " + fullHist.size());

//                    get time data
                    MemberTimeStats timeStats = timeStatsService.getTimeStats(device, fullHist);
//
                    timeStatsService.collect(timeStats);

                    //save time date
                    timeStatsService.saveMemberTimeStats(timeStats, statusHists.get(0).getRegdate().toLocalDate().minusDays(1));
                }

            }
        }

    }





    public enum E_MemberStatsByDate
    {
        E_DEVICENO(0),
        E_REGDATE(1),

        E_AVG_HR(2),
        E_MAX_HR(3),
        E_MIN_HR(4),

        E_AVG_BR(5),
        E_MAX_BR(6),
        E_MIN_BR(7),

        E_AVG_TEMPERATURE(8),
        E_MAX_TEMPERATURE(9),
        E_MIN_TEMPERATURE(10),

        E_AVG_HUMIDITY(11),
        E_MAX_HUMIDITY(12),
        E_MIN_HUMIDITY(13),

        E_AVG_TVOC(14),
        E_MAX_TVOC(15),
        E_MIN_TVOC(16),

        E_AVG_LIGHT(17),
        E_MAX_LIGHT(18),
        E_MIN_LIGHT(19);

        private int mnValue;

        private E_MemberStatsByDate(int nValue)
        {
            mnValue =   nValue;
        }

        public  int     getMnValue()
        {
            return mnValue;
        }
    }


}


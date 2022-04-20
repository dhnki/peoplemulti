package pmhealthcare.hatinroo.datamart.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pmhealthcare.hatinroo.datamart.model.device.Device;
import pmhealthcare.hatinroo.datamart.model.device.MemberStatusHist;
import pmhealthcare.hatinroo.datamart.model.device.date.*;
import pmhealthcare.hatinroo.datamart.model.dto.MemberTimeStats;
import pmhealthcare.hatinroo.datamart.repository.device.MemberStatusHistRepository;
import pmhealthcare.hatinroo.datamart.repository.device.date.ActivityRepository;
import pmhealthcare.hatinroo.datamart.repository.device.date.SleepRepository;
import pmhealthcare.hatinroo.datamart.repository.device.date.StayRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TimeStatsService {


    @Value("custom.batch.time")
    private String batchTime;

    private final int DEVICE_SEND_SECOND = 60;

    private final ActivityRepository activityRepository;
    private final StayRepository stayRepository;
    private final SleepRepository sleepRepository;

    private final MemberStatusHistRepository memberStatusHistRepository;



    //일별
    public List<MemberStatusHist> getDaily(long deviceNo){
        return memberStatusHistRepository.getHistDaily(deviceNo);
    }

    //오늘
    public List<MemberStatusHist> getToday(long deviceNo){
        return memberStatusHistRepository.getTodayTimeStats(deviceNo);
    }


    public List<MemberStatusHist> createStatusHistory(List<MemberStatusHist> memberStatusHistList, Device device){

        List<MemberStatusHist> createHists = new ArrayList<>();

        int idx = 0;
        for (int i = 0; i < 1440; i++) {

            if(idx < memberStatusHistList.size() && i == ( memberStatusHistList.get(idx).getRegdate().toLocalTime().toSecondOfDay() / 60 ) ){
                createHists.add(i, memberStatusHistList.get(idx));
                idx++;
            }
            else {
                MemberStatusHist hist = new MemberStatusHist();
                hist.createNull(device.getMemberNo(), device.getNo(), device.getDeviceId(), LocalDateTime.now().minusDays(1).toLocalDate().atStartOfDay().plusMinutes(i));
                createHists.add(i, hist);
            }

        }

        return createHists;
    }


    public MemberTimeStats getTimeStats(Device device, List<MemberStatusHist> statusHistList) {

        MemberTimeStats memberStat = new MemberTimeStats(device.getMemberNo(), device.getNo(), device.getDeviceId());

        MemberStatusHist beforeMemberStatus = null;

        Stay stay = null;
        Sleep sleep = null;
        Activity activity = null;

        //start

        for (MemberStatusHist hist : statusHistList) {

            if(hist.getStay() == 0) {
                hist.setStay((byte) 1);
            }

            if(hist.getSleep() == 0) {
                hist.setSleep((byte) 1);
            }

            if(hist.getActivity() == 0) {
                hist.setActivity((byte) 1);
            }


            //null check
            if (null == beforeMemberStatus) {
                // add stay
                stay = new Stay(hist.getMemberNo(), hist.getDeviceNo(), hist.getDeviceId(), hist.getStay(),
                        hist.getRegdate(), hist.getRegdate().plusSeconds(DEVICE_SEND_SECOND), DEVICE_SEND_SECOND, hist.getRegdate());

                memberStat.addStay(stay);

                sleep = new Sleep(hist.getMemberNo(), hist.getDeviceNo(), hist.getDeviceId(),
                        hist.getRegdate(), hist.getRegdate().plusSeconds(DEVICE_SEND_SECOND), DEVICE_SEND_SECOND, hist.getRegdate(),
                        ( (1 == hist.getSleep()) ? (byte) 1 : (byte) 0 ) );

                memberStat.addSleep(sleep);

                activity = new Activity(hist.getMemberNo(), hist.getDeviceNo(), hist.getDeviceId(),
                        hist.getRegdate(), hist.getRegdate().plusSeconds(DEVICE_SEND_SECOND), DEVICE_SEND_SECOND, hist.getRegdate(),
                        ( (1 == hist.getActivity()) ? (byte) 1 : (byte) 0 ) );

                memberStat.addActivity(activity);


            // add Object
            } else {

                if( hist.getStay() == beforeMemberStatus.getStay() ){
                    stay.addTime(DEVICE_SEND_SECOND);
                }else{
                    stay = new Stay(hist.getMemberNo(), hist.getDeviceNo(), hist.getDeviceId(), hist.getStay(),
                            hist.getRegdate(), hist.getRegdate().plusSeconds(DEVICE_SEND_SECOND), DEVICE_SEND_SECOND, hist.getRegdate());

                    memberStat.addStay(stay);
                }

                // add sleep
                if ( hist.getSleep() == beforeMemberStatus.getSleep() ) {
                    sleep.addTime(DEVICE_SEND_SECOND);
                } else {
                    sleep = new Sleep(hist.getMemberNo(), hist.getDeviceNo(), hist.getDeviceId(),
                            hist.getRegdate(), hist.getRegdate().plusSeconds(DEVICE_SEND_SECOND), DEVICE_SEND_SECOND, hist.getRegdate()
                            ,  ( (1 == hist.getSleep()) ? (byte) 1 : (byte) 0 ) );

                    memberStat.addSleep(sleep);
                }

                // add activity
                if ( hist.getActivity() == beforeMemberStatus.getActivity() ) {
                    activity.addTime(DEVICE_SEND_SECOND);
                } else {
                    activity = new Activity(hist.getMemberNo(), hist.getDeviceNo(), hist.getDeviceId(),
                            hist.getRegdate(), hist.getRegdate().plusSeconds(DEVICE_SEND_SECOND), DEVICE_SEND_SECOND, hist.getRegdate()
                            , ( (1 == hist.getActivity()) ? (byte) 1 : (byte) 0 )  );

                    memberStat.addActivity(activity);
                }
            }

            // 이전 값 저장
            beforeMemberStatus = hist;

        }

        return memberStat;
    }


    public void collect(MemberTimeStats memberTimeStats) {

        List<Sleep> sleeps = memberTimeStats.getSleeps();
        Sleep beforeSleep = null;
        Sleep blankSleep = null;

        for(Sleep sleep : sleeps){
            if(sleep.getValidity() == 1){
                if(null != beforeSleep){
//                    log.info("========== duration : " + beforeSleep.getEnd().toString() + " : " + sleep.getStart().toString() );

                    Duration duration = Duration.between(beforeSleep.getEnd(), sleep.getStart());
                    if(duration.getSeconds() < 601){
                        blankSleep.setValidity((byte) 1);
                    }

                }

                beforeSleep = sleep;
            } else {
                blankSleep = sleep;
            }
        }

//        log.info("=============================    sleep   ====================================");
//        for(Sleep sleep : sleeps){
//            log.info(sleep.toString());
//        }

        List<Sleep> newSleeps = new ArrayList<>();
        beforeSleep = null;
        for(Sleep sleep : sleeps){
            if( null != beforeSleep && beforeSleep.getValidity() == sleep.getValidity()){
                beforeSleep.addTime((int) sleep.getSleepTime());
            }else{
                newSleeps.add(sleep);
                beforeSleep = sleep;
            }
        }

        memberTimeStats.setSleeps(newSleeps);
//
//        log.info("=============================    sleep   ====================================");
//        for(Sleep sleep : newSleeps){
//            log.info(sleep.toString());
//        }


        List<Activity> activities = memberTimeStats.getActivities();
        Activity beforeActivity = null;
        Activity blankActivity = null;

        for(Activity activity : activities){
            if(activity.getValidity() == 1){
                if(null != beforeActivity){

                    Duration duration = Duration.between(beforeActivity.getEnd(), activity.getStart());
                    if(duration.getSeconds() < 601){
                        blankActivity.setValidity((byte) 1);
                    }

                }

                beforeActivity = activity;
            } else {
                blankActivity = activity;
            }
        }

        List<Activity> newActivitys = new ArrayList<>();
        beforeActivity = null;
        for(Activity activity : activities){
            if( null != beforeActivity && beforeActivity.getValidity() == activity.getValidity()){
                beforeActivity.addTime((int) activity.getActivityTime());
            }else{
                newActivitys.add(activity);
                beforeActivity = activity;
            }

        }

        memberTimeStats.setActivities(newActivitys);

//        log.info("=============================    activity   ====================================");
//        for(Activity activity : newActivitys){
//            log.info(activity.toString());
//        }




        List<Stay> stays = memberTimeStats.getStays();
        Stay beforeStay = null;
        Stay blankStay = null;

        for(Stay stay : stays){
            if(stay.getStayYn() == 1){
                if(null != beforeStay){
                    Duration duration = Duration.between(beforeStay.getEnd(), stay.getStart());
                    if(duration.getSeconds() < 601){
                        blankStay.setStayYn((byte) 1);
                    }
                }

                beforeStay = stay;
            } else {
                blankStay = stay;
            }
        }


        List<Stay> newStays = new ArrayList<>();
        beforeStay = null;
        for(Stay stay : stays){
            if( null != beforeStay && beforeStay.getStayYn() == stay.getStayYn() ){
                beforeStay.addTime((int) stay.getStayTime());
            }else{
                newStays.add(stay);
                beforeStay = stay;
            }

        }

        memberTimeStats.setStays(newStays);

//        log.info("=============================    stay   ====================================");
//        for(Stay stay : newStays){
//            log.info(stay.toString());
//        }


    }



    @Transactional
    public void saveActivity(Activity activity){
        activityRepository.save(activity);
    }

    @Transactional
    public void saveStay(Stay stay){
        stayRepository.save(stay);
    }

    @Transactional
    public void saveSleep(Sleep sleep){
        sleepRepository.save(sleep);
    }


    @Transactional
    public void saveMemberTimeStats(MemberTimeStats timeStats, LocalDate regdate){


        // save activity
        List<Activity> activities = timeStats.getActivities();
//        List<Activity> findActivityList = activityRepository.getToday(timeStats.getDeviceNo(), regdate);

        for(int i=0; i < activities.size(); i++){

            Activity temp = activities.get(i);
//            if(null != findActivityList && i < findActivityList.size() ){
//                log.info(findActivityList.get(i).toString());
//                temp.setId(findActivityList.get(i).getNo());
//            }
            saveActivity(temp);
        }


        // save stays
        List<Stay> stays = timeStats.getStays();
//        List<Stay> findStays = stayRepository.getToday(timeStats.getDeviceNo(), regdate);

        for(int i=0; i < stays.size(); i++){
            Stay temp = stays.get(i);
//            if(null != findStays && i < findStays.size()){
//                log.info(findStays.get(i).toString());
//                temp.setId(findStays.get(i).getNo());
//            }
            saveStay(temp);
        }


        // save sleep
        List<Sleep> sleeps = timeStats.getSleeps();
//        List<Sleep> findSleeps = sleepRepository.getToday(timeStats.getDeviceNo(), regdate);

        for(int i=0; i < sleeps.size(); i++){
            Sleep temp = sleeps.get(i);
//            if(null != findSleeps && i < findSleeps.size()){
//                log.info(findSleeps.get(i).toString());
//                temp.setId(findSleeps.get(i).getNo());
//            }
            saveSleep(temp);
        }
    }



}

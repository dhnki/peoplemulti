package pmhealthcare.hatinroo.datamart.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pmhealthcare.hatinroo.datamart.model.device.DeviceStat;
import pmhealthcare.hatinroo.datamart.model.device.MemberDailyStats;
import pmhealthcare.hatinroo.datamart.model.device.MemberStatus;
import pmhealthcare.hatinroo.datamart.model.device.MemberStatusHist;
import pmhealthcare.hatinroo.datamart.repository.device.DeviceStatRepository;
import pmhealthcare.hatinroo.datamart.repository.device.MemberDailyStatsRepository;
import pmhealthcare.hatinroo.datamart.repository.device.MemberStatusHistRepository;
import pmhealthcare.hatinroo.datamart.repository.device.MemberStatusRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DailyStatService {

    private final MemberStatusHistRepository memberStatusHistRepository;
    private final MemberStatusRepository memberStatusRepository;
    private final MemberDailyStatsRepository memberDailyStatsRepository;

    private final DeviceStatRepository deviceStatRepository;

    @Transactional
    public void setMemberStat(){

        List<MemberStatus> dailyUseDevice = memberStatusRepository.findDailyUseDevice();

        for( MemberStatus memberStatus : dailyUseDevice ){

            List<MemberStatusHist> histDaily = memberStatusHistRepository.getHistDaily(memberStatus.getDeviceNo());

            float min = 99999;
            float avgHr = 0;
            float avgBr = 0;

            int count = 0;

            for( MemberStatusHist hist : histDaily ){

                if( 1 == hist.getSleep()  ){

                    if( min > hist.getAvgDiff() && null != hist.getAvgDiff() && hist.getAvgDiff() > 0 ){
                        min = hist.getAvgDiff();
                    }

                    if( hist.getMot() == 0 ){
                        avgBr += hist.getBr();
                        avgHr += hist.getHr();
                        count++;
                    }

                }
            }

            if(min < 0.01 || count > 0) {

                DeviceStat deviceStat = new DeviceStat();

                deviceStat.setDeviceNo(memberStatus.getDeviceNo());

                if (min < 0.01) {
                    deviceStat.setAvgDiff(min);
                }

                if (count > 0) {
                    avgBr = avgBr / count;
                    avgHr = avgHr / count;

                    deviceStat.setDeepSleepBr(avgBr);
                    deviceStat.setDeepSleepHr(avgHr);

                }

                deviceStat.setRegdate(LocalDate.now().minusDays(1).atStartOfDay());

                log.info(deviceStat.toString());

                deviceStatRepository.save(deviceStat);

            }

        }
    }



    @Transactional
    public void setDailyAvgHist(){

        List<Object[]> histDaily = memberStatusHistRepository.getDailyAvgHist(LocalDate.now().minusDays(1));

        for (int idx = 0; idx < histDaily.size(); idx++) {

            MemberDailyStats memberDailyStats = new MemberDailyStats();

            memberDailyStats.create(histDaily.get(idx));

            memberDailyStatsRepository.save(memberDailyStats);

        }

    }





}


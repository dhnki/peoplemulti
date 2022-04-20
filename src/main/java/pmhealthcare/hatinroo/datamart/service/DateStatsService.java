package pmhealthcare.hatinroo.datamart.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pmhealthcare.hatinroo.datamart.model.device.date.*;
import pmhealthcare.hatinroo.datamart.repository.device.MemberStatusHistRepository;
import pmhealthcare.hatinroo.datamart.repository.device.date.*;

import java.time.LocalDate;
import java.util.List;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DateStatsService {

    private final DateBrRepository dateBrRepository;
    private final DateHrRepository dateHrRepository;
    private final DateHumidityRepository dateHumidityRepository;
    private final DateLightRepository dateLightRepository;
    private final DateTemperatureRepository dateTemperatureRepository;
    private final DateTvocRepository dateTvocRepository;


    private final MemberStatusHistRepository memberStatusHistRepository;

    //일별
    public List<Object[]> getDaily(){
        return memberStatusHistRepository.getHistDaily();
    }

    //오늘
    public List<Object[]> getToday(){
        return memberStatusHistRepository.getTodayStats();
    }


    @Transactional
    public void saveDateBr(DateBr dateBr){

        DateBr findDateBr = dateBrRepository.findByDeviceNoAndRegDate(dateBr.getDeviceNo(), dateBr.getRegdate());
        if(null != findDateBr){
            findDateBr.updateDailyData(dateBr);
            dateBrRepository.save(findDateBr);
        }
        else{
            dateBrRepository.save(dateBr);
        }
    }

    @Transactional
    public void saveDateHr(DateHr dateHr){

        DateHr findDateHr = dateHrRepository.findByDeviceNoAndRegDate(dateHr.getDeviceNo(), dateHr.getRegdate());
        if(null != findDateHr){
            findDateHr.updateDailyData(dateHr);
            dateHrRepository.save(findDateHr);
        }
        else{
            dateHrRepository.save(dateHr);
        }

    }

    @Transactional
    public void saveDateHumidity(DateHumidity dateHumidity){

        DateHumidity findHumidity = dateHumidityRepository.findByDeviceNoAndRegDate(dateHumidity.getDeviceNo(), dateHumidity.getRegdate());
        if( null != findHumidity){
            findHumidity.updateDailyData(dateHumidity);
            dateHumidityRepository.save(findHumidity);
        }
        else{
            dateHumidityRepository.save(dateHumidity);
        }

    }

    @Transactional
    public void saveDateLight(DateLight dateLight){

        DateLight findLight = dateLightRepository.findByDeviceNoAndRegDate(dateLight.getDeviceNo(), dateLight.getRegdate());
        if( null != findLight){
            findLight.updateDailyData(dateLight);
            dateLightRepository.save(findLight);
        }
        else{
            dateLightRepository.save(dateLight);
        }

    }

    @Transactional
    public void saveDateTemperature(DateTemperature dateTemperature){

        DateTemperature findTemperature = dateTemperatureRepository.findByDeviceNoAndRegDate(dateTemperature.getDeviceNo(), dateTemperature.getRegdate());
        if(null != findTemperature){
            findTemperature.updateDailyData(dateTemperature);
            dateTemperatureRepository.save(findTemperature);
        }
        else{
            dateTemperatureRepository.save(dateTemperature);
        }

    }

    @Transactional
    public void saveDateTvoc(DateTvoc dateTvoc){

        DateTvoc findTvoc = dateTvocRepository.findByDeviceNoAndRegDate(dateTvoc.getDeviceNo(), dateTvoc.getRegdate());
        if(null != findTvoc){
            findTvoc.updateDailyData(dateTvoc);
            dateTvocRepository.save(findTvoc);
        }
        else{
            dateTvocRepository.save(dateTvoc);
        }

    }


    @Transactional
    public void saveStats(Long memberNo, long deviceNo, Object[] deviceUser){
//        try {
            //호흡 저장
            DateBr dateBr = new DateBr(
                    memberNo,
                    deviceNo,
                    Float.parseFloat( deviceUser[E_MemberStatsByDate.E_MIN_BR.getMnValue()].toString()),
                    Float.parseFloat( deviceUser[E_MemberStatsByDate.E_MAX_BR.getMnValue()].toString()),
                    Float.parseFloat( deviceUser[E_MemberStatsByDate.E_AVG_BR.getMnValue()].toString()),
                    LocalDate.parse ( deviceUser[E_MemberStatsByDate.E_REGDATE.getMnValue()].toString() ).atStartOfDay()
            );
            saveDateBr(dateBr);

            //심박수 저장
            DateHr dateHr = new DateHr(
                    memberNo,
                    deviceNo,
                    Float.parseFloat( deviceUser[E_MemberStatsByDate.E_MIN_HR.getMnValue()].toString()),
                    Float.parseFloat( deviceUser[E_MemberStatsByDate.E_MAX_HR.getMnValue()].toString()),
                    Float.parseFloat( deviceUser[E_MemberStatsByDate.E_AVG_HR.getMnValue()].toString()),
                    LocalDate.parse ( deviceUser[E_MemberStatsByDate.E_REGDATE.getMnValue()].toString() ).atStartOfDay()
            );
            saveDateHr(dateHr);

            //온도 저장
            DateTemperature dateTemperature = new DateTemperature(
                    memberNo,
                    deviceNo,
                    Float.parseFloat( deviceUser[E_MemberStatsByDate.E_MIN_TEMPERATURE.getMnValue()].toString()),
                    Float.parseFloat( deviceUser[E_MemberStatsByDate.E_MAX_TEMPERATURE.getMnValue()].toString()),
                    Float.parseFloat( deviceUser[E_MemberStatsByDate.E_AVG_TEMPERATURE.getMnValue()].toString()),
                    LocalDate.parse ( deviceUser[E_MemberStatsByDate.E_REGDATE.getMnValue()].toString() ).atStartOfDay()
            );
            saveDateTemperature(dateTemperature);

            //습도 저장
            DateHumidity dateHumidity = new DateHumidity(
                    memberNo,
                    deviceNo,
                    Float.parseFloat( deviceUser[E_MemberStatsByDate.E_MIN_HUMIDITY.getMnValue()].toString()),
                    Float.parseFloat( deviceUser[E_MemberStatsByDate.E_MAX_HUMIDITY.getMnValue()].toString()),
                    Float.parseFloat( deviceUser[E_MemberStatsByDate.E_AVG_HUMIDITY.getMnValue()].toString()),
                    LocalDate.parse ( deviceUser[E_MemberStatsByDate.E_REGDATE.getMnValue()].toString() ).atStartOfDay()
            );
            saveDateHumidity(dateHumidity);

            //유기화학물
            DateTvoc dateTvoc = new DateTvoc(
                    memberNo,
                    deviceNo,
                    Float.parseFloat( deviceUser[E_MemberStatsByDate.E_MIN_TVOC.getMnValue()].toString()),
                    Float.parseFloat( deviceUser[E_MemberStatsByDate.E_MAX_TVOC.getMnValue()].toString()),
                    Float.parseFloat( deviceUser[E_MemberStatsByDate.E_AVG_TVOC.getMnValue()].toString()),
                    LocalDate.parse ( deviceUser[E_MemberStatsByDate.E_REGDATE.getMnValue()].toString() ).atStartOfDay()
            );
            saveDateTvoc(dateTvoc);

            //조도
            DateLight dateLight = new DateLight(
                    memberNo,
                    deviceNo,
                    Float.parseFloat( deviceUser[E_MemberStatsByDate.E_MIN_LIGHT.getMnValue()].toString()),
                    Float.parseFloat( deviceUser[E_MemberStatsByDate.E_MAX_LIGHT.getMnValue()].toString()),
                    Float.parseFloat( deviceUser[E_MemberStatsByDate.E_AVG_LIGHT.getMnValue()].toString()),
                    LocalDate.parse ( deviceUser[E_MemberStatsByDate.E_REGDATE.getMnValue()].toString() ).atStartOfDay()
            );
            saveDateLight(dateLight);

//        }catch (Exception e){
//            log.error(e.getMessage());
//        }


    }



    public enum E_MemberStatsByDate
    {
        E_DEVICE_ID(0),
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

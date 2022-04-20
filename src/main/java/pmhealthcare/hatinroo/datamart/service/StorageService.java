package pmhealthcare.hatinroo.datamart.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pmhealthcare.hatinroo.datamart.model.device.MemberStatusHist;
import pmhealthcare.hatinroo.datamart.model.device.SensorData;
import pmhealthcare.hatinroo.datamart.repository.app.PushRepository;
import pmhealthcare.hatinroo.datamart.repository.device.MemberStatusHistRepository;
import pmhealthcare.hatinroo.datamart.repository.device.SensorDataRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StorageService {

    private final int READ_SIZE = 3000;

    private final String BUCKET_SENSOR_DATA = "sensor-data";
    private final String BUCKET_MEMBER_HIST = "member-hist";

    private final MemberStatusHistRepository memberStatusHistRepository;
    private final SensorDataRepository sensorDataRepository;
    private final ResourceService resourceService;
    private final PushRepository pushRepository;


    @Transactional
    public void saveSensorData() {

//        LocalDateTime searchDate = LocalDate.now().atStartOfDay().minusMonths(3);
        LocalDateTime searchDate = LocalDate.now().atStartOfDay().minusDays(90);

        log.info( searchDate.toString());

        File saveFile = new File(BUCKET_SENSOR_DATA+"_" + searchDate.toLocalDate().toString()+".txt");

        List<Object[]> countObject = sensorDataRepository.getCount(searchDate);

        if( null == countObject || 0 == countObject.size() || null == countObject.get(0)){
            return;
        }

        Object[] count = countObject.get(0);

        int nTotalCount = Integer.parseInt(String.valueOf(count[0]));

        log.info(" total count : " + nTotalCount);

        try (   FileWriter fileWriter = new FileWriter(saveFile);
                BufferedWriter writer = new BufferedWriter(fileWriter) ) {

            writer.write(SensorData.getColumn());

//            int idx = 0;
            for (int row = 0; row < nTotalCount; row += READ_SIZE) {
                log.info(" row : " + row + " , read_size : " + (row + READ_SIZE));

                List<SensorData> dataList = sensorDataRepository.getByDate(searchDate, row, READ_SIZE);

                log.info("list size : " + dataList.size());
                for (SensorData sensorData : dataList) {

                    writer.write(  sensorData.toString());
                }

            }
            writer.flush();
            writer.close();

            resourceService.resourceUploader(BUCKET_SENSOR_DATA, saveFile.getName(),  searchDate.getYear() + "-" + searchDate.getMonthValue(), saveFile);

            sensorDataRepository.deleteByDate(searchDate);

        }catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        saveFile.delete();

    }


    @Transactional
    public void saveMemberHist() {

//        LocalDateTime searchDate = LocalDate.now().atStartOfDay().minusMonths(3);
        LocalDateTime searchDate = LocalDate.now().atStartOfDay().minusDays(90);

        log.info( searchDate.toString());

        File saveFile = new File(BUCKET_MEMBER_HIST+ "_" + searchDate.toLocalDate().toString()+".txt");

        List<Object[]> countObject = memberStatusHistRepository.getCount(searchDate);

        if( null == countObject || 0 == countObject.size() || null == countObject.get(0)){
            return;
        }

        Object[] count = countObject.get(0);

        int nTotalCount = Integer.parseInt(String.valueOf(count[0]));

        log.info(" total count : " + nTotalCount);

        try (   FileWriter fileWriter = new FileWriter(saveFile);
                BufferedWriter writer = new BufferedWriter(fileWriter) ) {

            writer.write(MemberStatusHist.getColumn());

//            int idx = 0;
            for (int row = 0; row < nTotalCount; row += READ_SIZE) {
                log.info(" row : " + row + " , read_size : " + (row + READ_SIZE));

                List<MemberStatusHist> dataList = memberStatusHistRepository.getByDate(searchDate, row, READ_SIZE);

                log.info("list size : " + dataList.size());
                for (MemberStatusHist hist : dataList) {

                    writer.write( hist.toString());

                }

            }
            writer.flush();
            writer.close();

            log.info("directory : " + searchDate.getYear() + "-" + searchDate.getMonthValue());

            resourceService.resourceUploader(BUCKET_MEMBER_HIST, saveFile.getName(),  searchDate.getYear() + "-" + searchDate.getMonthValue(), saveFile);

            memberStatusHistRepository.deleteByDate(searchDate);

        }catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        saveFile.delete();

    }


    public void removePushs() {

        LocalDateTime searchDate = LocalDate.now().atStartOfDay().minusYears(1);

        pushRepository.removeByDate(searchDate);

    }
}

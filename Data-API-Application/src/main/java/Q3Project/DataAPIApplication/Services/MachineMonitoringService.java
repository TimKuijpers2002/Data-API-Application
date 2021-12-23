package Q3Project.DataAPIApplication.Services;

import Q3Project.DataAPIApplication.Interface.IMonitoringDataService;
import Q3Project.DataAPIApplication.Model.*;
import Q3Project.DataAPIApplication.Repository.MachineMonitoringPoortenRepository;
import Q3Project.DataAPIApplication.Repository.MonitoringData202009Repository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MachineMonitoringService implements IMonitoringDataService {

    private final MonitoringData202009Repository monitoringData202009Repository;

    public MachineMonitoringService(MonitoringData202009Repository monitoringData202009Repository, MachineMonitoringPoortenRepository machineMonitoringPoortenRepository) {
        this.monitoringData202009Repository = monitoringData202009Repository;
    }

    @Override
    public List<MonitoringData202009> GetAllFromMachine(String name) {
        return monitoringData202009Repository.findByName(name);
    }

    @Override
    public List<MonitoringData202009> GetAllFromMachinePerDay(String machineName, String datetime) throws ParseException {
        List<Date> beginEndDay = GetStartAndEndDay(datetime);
        return monitoringData202009Repository.findByNameAndDate(machineName, beginEndDay.stream().findFirst().get(), beginEndDay.get(beginEndDay.size()-1));
    }

    @Override
    public List<MonitoringData202009> GetAllUpAndDownTimes(String name, String datetime) throws ParseException {
        List<Date> beginEndDay = GetStartAndEndDay(datetime);
        List<MonitoringData202009> UpAndDownTimes = new ArrayList<>();
        List<MonitoringData202009> machineActivityOnDay = monitoringData202009Repository.findByNameAndDate(name, beginEndDay.get(0), beginEndDay.get(beginEndDay.size()-1));
        for(int i = 0; i < machineActivityOnDay.size(); i++){
            if(i != 0) {
                var current = machineActivityOnDay.get(i);
                var previous = machineActivityOnDay.get(i-1);

                long diffInMillies = Math.abs(current.getTimestamp().getTime() - previous.getTimestamp().getTime());
                long diffInSeconds = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                if(diffInSeconds >= (previous.getShotTime() * 6)) {
                    UpAndDownTimes.add(previous);
                    UpAndDownTimes.add(current);
                }
            }
        }
        return UpAndDownTimes;
    }

    @Override
    public boolean CheckCurrentMachineState(String name, String timestamp) throws ParseException {
        Date givenTimestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(timestamp);
        Pageable paging = PageRequest.of(0, 1);
        MonitoringData202009 lastShotBeforeTimestamp = monitoringData202009Repository.findShotBeforeTimestamp(name, givenTimestamp, paging).stream().findFirst().get();

        long maxDuration = (long) (lastShotBeforeTimestamp.getShotTime() * 6);
        long diffInMillies = Math.abs(givenTimestamp.getTime() - lastShotBeforeTimestamp.getTimestamp().getTime());
        long diff = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        return diff < maxDuration;
    }

//    public MonitoringData202009 GetLastShotTime(String name, String datetime) throws ParseException {
//        Date lastTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(datetime);
//
//    }

    @Override
    public List<MonitoringData202009> GetAllFromComponents(List<ProductionData> productionData) {
        return null;
    }

    public List<Date> GetStartAndEndDay(String datetime) throws ParseException {
        List<Date> dayList = new ArrayList<>(2);
        long start1 = System.nanoTime();
        Date beginDay = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(datetime);
        dayList.add(beginDay);
        Calendar c = Calendar.getInstance();
        c.setTime(beginDay);
        c.add(Calendar.DATE, 1);
        Date endDay = c.getTime();
        dayList.add(endDay);
        long end1 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: "+ (end1-start1));
        return dayList;
    }
}

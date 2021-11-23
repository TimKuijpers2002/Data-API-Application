package Q3Project.DataAPIApplication.Services;

import Q3Project.DataAPIApplication.Interface.IMonitoringDataService;
import Q3Project.DataAPIApplication.Model.*;
import Q3Project.DataAPIApplication.Repository.MachineMonitoringPoortenRepository;
import Q3Project.DataAPIApplication.Repository.MonitoringData202009Repository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MachineMonitoringService implements IMonitoringDataService {

    private final MonitoringData202009Repository monitoringData202009Repository;
    private final MachineMonitoringPoortenRepository machineMonitoringPoortenRepository;

    public MachineMonitoringService(MonitoringData202009Repository monitoringData202009Repository, MachineMonitoringPoortenRepository machineMonitoringPoortenRepository) {
        this.monitoringData202009Repository = monitoringData202009Repository;
        this.machineMonitoringPoortenRepository = machineMonitoringPoortenRepository;
    }

    @Override
    public List<MonitoringData202009> GetAllFromMachine(String name) {
        return monitoringData202009Repository.findByName(name);
    }

    @Override
    public List<MonitoringData202009> GetAllFromMachinePerDay(String machineName, String datetime) throws ParseException {
        long start1 = System.nanoTime();
        Date beginDay = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(datetime);
        Calendar c = Calendar.getInstance();
        c.setTime(beginDay);
        c.add(Calendar.DATE, 1);
        Date endDay = c.getTime();
        long end1 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: "+ (end1-start1));
        return monitoringData202009Repository.findByNameAndDate(machineName, beginDay, endDay);
    }

    @Override
    public List<MonitoringData202009> GetAllFromComponents(List<ProductionData> productionData) {
        return null;
    }
}

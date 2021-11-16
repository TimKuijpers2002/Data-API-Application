package Q3Project.DataAPIApplication.Services;

import Q3Project.DataAPIApplication.Interface.IMonitoringDataService;
import Q3Project.DataAPIApplication.Model.MachineMonitoringPoorten;
import Q3Project.DataAPIApplication.Model.MonitoringData202009;
import Q3Project.DataAPIApplication.Model.ProductionData;
import Q3Project.DataAPIApplication.Model.Treeview;
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
    public List<MonitoringData202009> GetAllFromMachine(int board, int port) {
        return monitoringData202009Repository.findByBoardPort(board, port);
    }

    @Override
    public List<MonitoringData202009> GetAllFromMachinePerDay(String machineName, String datetime) throws ParseException {
        MachineMonitoringPoorten machineInsight = machineMonitoringPoortenRepository.findByName(machineName).stream().findFirst().get();
        List<MonitoringData202009> dataFromOneMachine = monitoringData202009Repository.findByBoardPort(machineInsight.getBoard(), machineInsight.getPort());
        Date requestedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(datetime);
        dataFromOneMachine.removeIf(item -> item.getTimestamp().before(requestedDate));
        Calendar c = Calendar.getInstance();
        c.setTime(requestedDate);
        c.add(Calendar.DATE, 1);
        Date finalRequestedDate = c.getTime();
        dataFromOneMachine.removeIf(item -> item.getTimestamp().after(finalRequestedDate));
        return dataFromOneMachine;
    }

    @Override
    public List<MonitoringData202009> GetAllFromComponents(List<ProductionData> productionData) {
        return null;
    }
}

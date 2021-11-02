package Q3Project.DataAPIApplication.Services;

import Q3Project.DataAPIApplication.Interface.IMachineAndComponentService;
import Q3Project.DataAPIApplication.Model.MonitoringData202009;
import Q3Project.DataAPIApplication.Repository.MonitoringData202009Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineMonitoringService implements IMachineAndComponentService<MonitoringData202009> {

    private final MonitoringData202009Repository monitoringData202009Repository;

    public MachineMonitoringService(MonitoringData202009Repository monitoringData202009Repository) {
        this.monitoringData202009Repository = monitoringData202009Repository;
    }

    @Override
    public List<MonitoringData202009> GetAll() {
        return monitoringData202009Repository.findAll();

    }

    @Override
    public List<MonitoringData202009> GetAllFromMachines() {
        List<MonitoringData202009> allTreeviews = GetAll();
        //allTreeviews.removeIf(item -> item.getTreeviewSoortId() != TreeviewTypes.MACHINE.treeviewSoortId);
        return allTreeviews;
    }

    @Override
    public List<MonitoringData202009> GetAllFromComponents() {
        List<MonitoringData202009> allTreeviews = GetAll();
        //allTreeviews.removeIf(item -> item.getTreeviewSoortId() != TreeviewTypes.COMPONENT.treeviewSoortId);
        return allTreeviews;
    }
}

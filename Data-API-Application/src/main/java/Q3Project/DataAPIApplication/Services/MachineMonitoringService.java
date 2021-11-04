package Q3Project.DataAPIApplication.Services;

import Q3Project.DataAPIApplication.Interface.IMonitoringDataService;
import Q3Project.DataAPIApplication.Model.MonitoringData202009;
import Q3Project.DataAPIApplication.Model.ProductionData;
import Q3Project.DataAPIApplication.Repository.MonitoringData202009Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineMonitoringService /*implements IMonitoringDataService*/ {

    private final MonitoringData202009Repository monitoringData202009Repository;
    private final ProductionDataService productionDataService;

    public MachineMonitoringService(MonitoringData202009Repository monitoringData202009Repository, ProductionDataService productionDataService) {
        this.monitoringData202009Repository = monitoringData202009Repository;
        this.productionDataService = productionDataService;
    }

    public List<MonitoringData202009> GetAll() {
        return monitoringData202009Repository.findAll();
    }

    //@Override
    //public List<MonitoringData202009> GetAllByBoardPort(long treeviewId) {
        //List<ProductionData> productionDatas = productionDataService.GetAllByBoardPort(board, port);
    //}

    //@Override
    public List<MonitoringData202009> GetAllFromMachines() {
        List<MonitoringData202009> allTreeviews = GetAll();
        //allTreeviews.removeIf(item -> item.getTreeviewSoortId() != TreeviewTypes.MACHINE.treeviewSoortId);
        return allTreeviews;
    }

    //@Override
    public List<MonitoringData202009> GetAllFromComponents() {
        List<MonitoringData202009> allTreeviews = GetAll();
        //allTreeviews.removeIf(item -> item.getTreeviewSoortId() != TreeviewTypes.COMPONENT.treeviewSoortId);
        return allTreeviews;
    }
}

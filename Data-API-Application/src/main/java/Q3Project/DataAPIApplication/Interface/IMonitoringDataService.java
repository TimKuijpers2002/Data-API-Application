package Q3Project.DataAPIApplication.Interface;

import Q3Project.DataAPIApplication.Model.MonitoringData202009;

import java.util.List;

public interface IMonitoringDataService {

    List<MonitoringData202009> GetAllByBoardPort(long treeviewId);
    List<MonitoringData202009> GetAllFromMachines();
    List<MonitoringData202009> GetAllFromComponents();
}

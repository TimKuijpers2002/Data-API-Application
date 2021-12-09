package Q3Project.DataAPIApplication.Interface;

import Q3Project.DataAPIApplication.Model.MonitoringData202009;
import Q3Project.DataAPIApplication.Model.ProductionData;
import Q3Project.DataAPIApplication.Model.ReturnableMonitoringData;

import java.text.ParseException;
import java.util.List;

public interface IMonitoringDataService {

    List<MonitoringData202009> GetAllFromMachine(String name);
    List<MonitoringData202009> GetAllFromMachinePerDay(String machineName, String datetime) throws ParseException;
    List<MonitoringData202009> GetAllFromComponents(List<ProductionData> productionData);
}

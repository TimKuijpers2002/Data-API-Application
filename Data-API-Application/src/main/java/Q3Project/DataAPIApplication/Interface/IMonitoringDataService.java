package Q3Project.DataAPIApplication.Interface;

import Q3Project.DataAPIApplication.Model.MonitoringData202009;
import Q3Project.DataAPIApplication.Model.ProductionData;

import java.text.ParseException;
import java.util.List;

public interface IMonitoringDataService {

    List<MonitoringData202009> GetAllFromMachine(int board, int port);
    List<MonitoringData202009> GetAllFromMachinePerDay(int board, int port, String datetime) throws ParseException;
    List<MonitoringData202009> GetAllFromComponents(List<ProductionData> productionData);
}

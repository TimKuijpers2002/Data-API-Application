package Q3Project.DataAPIApplication.Services;

import Q3Project.DataAPIApplication.Model.MachineMonitoringPoorten;
import Q3Project.DataAPIApplication.Repository.MachineMonitoringPoortenRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MachineMonitoringPoortenService {

    private final MachineMonitoringPoortenRepository machineMonitoringPoortenRepository;

    public MachineMonitoringPoortenService(MachineMonitoringPoortenRepository machineMonitoringPoortenRepository){
        this.machineMonitoringPoortenRepository = machineMonitoringPoortenRepository;
    }

    public List<MachineMonitoringPoorten> GetAllMachines(){

        List<MachineMonitoringPoorten> allMachines = machineMonitoringPoortenRepository.findAll();
        allMachines.removeIf(item -> item.getName() == null);
        return allMachines;
    }

    public MachineMonitoringPoorten GetByName(String name){
        return machineMonitoringPoortenRepository.findByName(name);
    }
}

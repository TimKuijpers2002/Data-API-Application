package Q3Project.DataAPIApplication.Services;

import Q3Project.DataAPIApplication.Enums.TreeviewTypes;
import Q3Project.DataAPIApplication.Interface.ITreeviewService;
import Q3Project.DataAPIApplication.Model.MachineMonitoringPoorten;
import Q3Project.DataAPIApplication.Model.ProductionData;
import Q3Project.DataAPIApplication.Model.Treeview;
import Q3Project.DataAPIApplication.Repository.TreeviewRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TreeviewService implements ITreeviewService {

    private final TreeviewRepository treeviewRepository;
    private final ProductionDataService productionDataService;
    private final MachineMonitoringPoortenService machineMonitoringPoortenService;

    public TreeviewService(TreeviewRepository treeviewRepository, ProductionDataService productionDataService, MachineMonitoringPoortenService machineMonitoringPoortenService) {
        this.treeviewRepository = treeviewRepository;
        this.productionDataService = productionDataService;
        this.machineMonitoringPoortenService = machineMonitoringPoortenService;
    }

    @Override
    public Treeview GetByTreeviewId(long treeviewId) {
        return treeviewRepository.findById(treeviewId).orElse(null);
    }

    @Override
    public List<Treeview> GetAll() {
        return treeviewRepository.findAll();
    }

    @Override
    public List<Treeview> GetAllFromMachines() {
        List<MachineMonitoringPoorten> allMachines = machineMonitoringPoortenService.GetAllMachines();
        List<Treeview> allTreeview = GetAll();
        List<Treeview> allConfirmedMachines = new ArrayList<>();
        for(MachineMonitoringPoorten currentMachine: allMachines){
            for(Treeview treeview: allTreeview){
                if(Objects.equals(treeview.getName(), currentMachine.getName())){
                    allConfirmedMachines.add(treeview);
                }
            }
        }
        return allConfirmedMachines;
    }

    @Override
    public List<Treeview> GetAllFromComponents() {
        List<Treeview> allTreeviews = GetAll();
        allTreeviews.removeIf(item -> item.getTreeviewSoortId() != TreeviewTypes.COMPONENT.treeviewSoortId);
        return allTreeviews;
    }

    @Override
    public List<Treeview> GetAllComponentsFromMachine(long treeviewId) {
        List<MachineMonitoringPoorten> allMachines = machineMonitoringPoortenService.GetAllMachines();
        allMachines.removeIf
                (item -> !Objects.equals(item.getName(), GetByTreeviewId(treeviewId).getName()));
        MachineMonitoringPoorten machine = allMachines.stream().findFirst().get();
        List<ProductionData> allComponentsProductions = productionDataService
                .GetComponentsFromMachine(machine.getBoard(), machine.getPort());
        Set<Long> allTreeviewIds = new HashSet<>();
        for(ProductionData currentData: allComponentsProductions){
            allTreeviewIds.add((long) currentData.getTreeviewId());
            allTreeviewIds.add((long) currentData.getTreeview2Id());
        }
        allTreeviewIds.removeIf(item -> item == 0);
        return treeviewRepository.findAllById(allTreeviewIds);
    }
}

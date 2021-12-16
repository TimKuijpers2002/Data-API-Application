package Q3Project.DataAPIApplication.Services;

import Q3Project.DataAPIApplication.Interface.ITreeviewService;
import Q3Project.DataAPIApplication.Model.MachineMonitoringPoorten;
import Q3Project.DataAPIApplication.Model.ProductionData;
import Q3Project.DataAPIApplication.Model.Treeview;
import Q3Project.DataAPIApplication.Repository.ProductionDataRepository;
import Q3Project.DataAPIApplication.Repository.TreeviewRepository;
import com.sun.source.tree.Tree;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.function.Predicate;

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
    public List<Treeview> GetAllMachines() {
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
    public List<Treeview> GetAllComponents() {
        List<Treeview> allTreeviews = treeviewRepository.findByTreeViewTypeId(2);
        return allTreeviews;
    }

    @Override
    public List<Treeview> GetAllComponentsFromMachine(String treeviewName) {
        MachineMonitoringPoorten machine = machineMonitoringPoortenService.GetByName(treeviewName);
        List<ProductionData> allComponentsProductions = productionDataService
                .GetComponentsFromMachine(machine.getBoard(), machine.getPort());
        return treeviewRepository.findAllById(GetTreeviewsByName(allComponentsProductions));
    }

    @Override
    public List<Treeview> GetComponentHistoryFromMachine(String treeviewName, String dateTime) throws ParseException {
        MachineMonitoringPoorten machine = machineMonitoringPoortenService.GetByName(treeviewName);

        Set<ProductionData> allComponentsProductions = productionDataService
                .GetComponentsFromMachineOnDate(machine.getBoard(), machine.getPort(), dateTime);
        Set<Long> allComponents = GetTreeviewsByName(new ArrayList<>(allComponentsProductions));
        return treeviewRepository.findAllById(allComponents);
    }

    public Set<Long> GetTreeviewsByName(List<ProductionData> productionDataList){
        Set<Long> allTreeviewIds = new HashSet<>();
        for(ProductionData currentData: productionDataList){
            allTreeviewIds.add((long) currentData.getTreeviewId());
            allTreeviewIds.add((long) currentData.getTreeview2Id());
        }
        allTreeviewIds.removeIf(item -> item == 0);
        return allTreeviewIds;
    }

    public MachineMonitoringPoorten GetMachinesByComponentId(ProductionData productionData){
        List<MachineMonitoringPoorten> allMachines = machineMonitoringPoortenService.GetAllMachines();
        allMachines.removeIf(item -> item.getBoard() != productionData.getBoard());
        allMachines.removeIf(item -> item.getPort() != productionData.getPort());
        allMachines.removeIf(item -> item.getName() == "Hastamat");
        return allMachines.stream().findFirst().get();
    }

    @Override
    public Set<Treeview> GetMachineHistoryByComponentName(String componentName)
    {
        Treeview component = treeviewRepository.findByName(componentName);
        List<ProductionData> allProductionData = productionDataService.GetProductionDataFromComponent(component.getTreeviewid());
        List<MachineMonitoringPoorten> machinesFromComponent = new ArrayList<>();
        for (ProductionData currentData: allProductionData) {
            var machine = GetMachinesByComponentId(currentData);
            if (machine != null) {
                machinesFromComponent.add(machine);
            }
        }
        List<Treeview> allTreeview = new ArrayList<>();
        for (MachineMonitoringPoorten currentMachine: machinesFromComponent)
        {
            allTreeview.add(treeviewRepository.findByName(currentMachine.getName()));
        }
        return new HashSet<>(allTreeview);
    }
}

package Q3Project.DataAPIApplication.Services;

import Q3Project.DataAPIApplication.Enums.TreeviewTypes;
import Q3Project.DataAPIApplication.Interface.ITreeviewService;
import Q3Project.DataAPIApplication.Model.MachineMonitoringPoorten;
import Q3Project.DataAPIApplication.Model.ProductionData;
import Q3Project.DataAPIApplication.Model.Treeview;
import Q3Project.DataAPIApplication.Repository.TreeviewRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public List<Treeview> GetAllComponentsFromMachine(String treeviewName) {
        MachineMonitoringPoorten machine = machineMonitoringPoortenService.GetByName(treeviewName);
        List<ProductionData> allComponentsProductions = productionDataService
                .GetComponentsFromMachine(machine.getBoard(), machine.getPort());
        return treeviewRepository.findAllById(GetTreeviewsByName(allComponentsProductions));
    }

    @Override
    public List<Treeview> GetHistoryComponentsFromMachine(String treeviewName, String date, String time) throws ParseException {
        MachineMonitoringPoorten machine = machineMonitoringPoortenService.GetByName(treeviewName);

        Date currentDay = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        Date currentTime = new SimpleDateFormat("HH:mm:ss").parse(time);
        List<ProductionData> allComponentsProductions = productionDataService
                .GetComponentsFromMachineOnDate(machine.getBoard(), machine.getPort(), currentDay, currentTime);
        Set<Long> allComponents = GetTreeviewsByName(allComponentsProductions);
        return treeviewRepository.findAllById(GetTreeviewsByName(allComponentsProductions));
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
}

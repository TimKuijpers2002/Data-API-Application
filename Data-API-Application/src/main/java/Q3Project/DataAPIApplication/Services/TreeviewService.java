package Q3Project.DataAPIApplication.Services;

import Q3Project.DataAPIApplication.Interface.ITreeviewService;
import Q3Project.DataAPIApplication.Model.MachineMonitoringPoorten;
import Q3Project.DataAPIApplication.Model.MonitoringData202009;
import Q3Project.DataAPIApplication.Model.ProductionData;
import Q3Project.DataAPIApplication.Model.Treeview;
import Q3Project.DataAPIApplication.Repository.MonitoringData202009Repository;
import Q3Project.DataAPIApplication.Repository.ProductionDataRepository;
import Q3Project.DataAPIApplication.Repository.TreeviewRepository;
import com.sun.source.tree.Tree;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;

@Service
public class TreeviewService implements ITreeviewService {

    private final TreeviewRepository treeviewRepository;
    private final ProductionDataService productionDataService;
    private final MachineMonitoringPoortenService machineMonitoringPoortenService;
    private final MonitoringData202009Repository MonitoringDataRepository;

    public TreeviewService(TreeviewRepository treeviewRepository, ProductionDataService productionDataService, MachineMonitoringPoortenService machineMonitoringPoortenService, MonitoringData202009Repository monitoringDataRepository) {
        this.treeviewRepository = treeviewRepository;
        this.productionDataService = productionDataService;
        this.machineMonitoringPoortenService = machineMonitoringPoortenService;
        this.MonitoringDataRepository = monitoringDataRepository;
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
        List<Treeview> allTreeviews = GetAll();
        List<MachineMonitoringPoorten> allMachines = machineMonitoringPoortenService.GetAllMachines();
        for(MachineMonitoringPoorten machine: allMachines){
            allTreeviews.removeIf(treeview -> treeview.getName().equals(machine.getName()));        }
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

    @Override
    public int ComponentTotalShotCount(String name) throws ParseException {
        List<MonitoringData202009> datalist = new ArrayList<>();

        var treeview = treeviewRepository.findByName(name);
        var productionsdatas =productionDataService.GetBoardAndPortByTreeviewId(treeview.getTreeviewid());
        for(ProductionData data : productionsdatas)
        {
            Date beginDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(data.getStartDate() +'T'+ data.getStartTime());
            Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse((data.getEndDate() + 'T' + data.getEndTime()));
            datalist.addAll(MonitoringDataRepository.FindByBoardAndPort(data.getBoard(),data.getPort(),beginDate, endDate));
        }

        return datalist.size();
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

    public List<MachineMonitoringPoorten> GetMachinesByComponentId(List<ProductionData> productionDataList){
        List<MachineMonitoringPoorten> allMachines = machineMonitoringPoortenService.GetAllMachines();
        for(ProductionData currentData: productionDataList){
            allMachines.removeIf(machine -> machine.getBoard() != currentData.getBoard() || machine.getPort() != currentData.getPort());
        }
        return allMachines;
    }

    @Override
    public Set<Treeview> GetMachineHistoryByComponentName(String componentName)
    {
        Treeview component = treeviewRepository.findByName(componentName);
        List<ProductionData> allProductionData = productionDataService.GetProductionDataFromComponent(component.getTreeviewid());
        List<MachineMonitoringPoorten> allMachines = GetMachinesByComponentId(allProductionData);
        List<Treeview> allTreeview = GetAllMachines();
        for (MachineMonitoringPoorten currentMachine: allMachines)
        {
            Predicate<Treeview> condition = treeview -> !treeview.getName().equals(currentMachine.getName());
            allTreeview.removeIf(condition);
        }
        return new HashSet<>(allTreeview);
    }
}

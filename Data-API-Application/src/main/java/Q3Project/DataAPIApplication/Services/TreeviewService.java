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
        List<Treeview> allTreeviews = treeviewRepository.findByTreeViewTypeId(2);
        return allTreeviews;
    }

    @Override
    public List<Treeview> GetAllComponentsFromMachine(String treeviewName) {
        MachineMonitoringPoorten machine = machineMonitoringPoortenService.GetByName(treeviewName);
        Set<ProductionData> allComponentsProductions = productionDataService
                .GetComponentsFromMachine(machine.getBoard(), machine.getPort());
        Set<Treeview> allComponents = new HashSet<>(treeviewRepository.findAllById(GetTreeviewsByName(new ArrayList<>(allComponentsProductions))));
        return new ArrayList<>(allComponents);
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
    public List<Integer> ComponentTotalShotCount(String name, String date) throws ParseException {
        Date converteddate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        List<MonitoringData202009> datalist = new ArrayList<>();
        List<Integer> shotsPerWeek = new ArrayList<>();
        List<Date> weeks = new ArrayList<>();
        weeks.add(converteddate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(converteddate);
        cal.add(Calendar.DATE, -7);
        weeks.add(cal.getTime());
        //Date firstweek = cal.getTime();
        cal.add(Calendar.DATE, -7);
        weeks.add(cal.getTime());
        //Date secondweek = cal.getTime();
        cal.add(Calendar.DATE, -7);
        weeks.add(cal.getTime());
        Date thirthweek = cal.getTime();

        var treeview = treeviewRepository.findByName(name);
        var productionsdatas =productionDataService.GetBoardAndPortByTreeviewId(treeview.getTreeviewid());
        for(ProductionData data : productionsdatas)
        {
            Date beginDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(data.getStartDate() +'T'+ data.getStartTime());
            Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(data.getEndDate() + 'T' + data.getEndTime());
            if (beginDate.before(converteddate) && endDate.after(thirthweek)) {
                datalist.addAll(MonitoringDataRepository.FindByBoardAndPort(data.getBoard(), data.getPort(), beginDate, endDate));
            }
            else if (beginDate.before(converteddate))
            {
                datalist.addAll(MonitoringDataRepository.FindByBoardAndPort(data.getBoard(), data.getPort(), converteddate, endDate));
            }
            else if (endDate.after(thirthweek)) {
                datalist.addAll(MonitoringDataRepository.FindByBoardAndPort(data.getBoard(), data.getPort(), beginDate, converteddate));
            }
        }

        List<MonitoringData202009> copylist = new ArrayList<>(datalist);

        for (var i = 0; i < 3; i++)
        {
            var x = i;
            datalist.removeIf(item -> item.getTimestamp().before(weeks.get(x)));
            datalist.removeIf(item -> item.getTimestamp().after(weeks.get(x+1)));
            shotsPerWeek.add(datalist.size());
            datalist = copylist;
        }
        return shotsPerWeek;
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

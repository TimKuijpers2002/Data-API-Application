package Q3Project.DataAPIApplication.Services;

import Q3Project.DataAPIApplication.Model.ProductionData;
import Q3Project.DataAPIApplication.Repository.ProductionDataRepository;
import Q3Project.DataAPIApplication.Repository.TreeviewRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductionDataService {

    private final ProductionDataRepository productionDataRepository;
    private final TreeviewRepository treeviewRepository;

    public ProductionDataService(ProductionDataRepository productionDataRepository, TreeviewRepository treeviewRepository) {
        this.productionDataRepository = productionDataRepository;
        this.treeviewRepository = treeviewRepository;
    }

    public List<ProductionData> GetProductionDataFromComponent(long componentId)
    {
        List<ProductionData> allProductionData = productionDataRepository.findAll();
        allProductionData.removeIf(item -> item.getTreeviewId() != componentId || item.getTreeview2Id() != componentId);
        return allProductionData;
    }

    public List<ProductionData> GetComponentsFromMachine(int board, int port) {
        List<ProductionData> allProductionData = productionDataRepository.findAll();
        allProductionData.removeIf(item -> item.getBoard() != board && item.getPort() != port);
        return allProductionData;
    }

    public Set<ProductionData> GetComponentsFromMachineOnDate(int board, int port, String dateTime) throws ParseException {
        Date currentDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse((dateTime));
        List<ProductionData> allForBoardAndPort = productionDataRepository.findByBP(board, port);
        Set<ProductionData> allForCorrectDate = new HashSet<>();
        for(ProductionData currentData : allForBoardAndPort){
            String test = (currentData.getStartDate() + 'T' + currentData.getStartTime());
            Date beginDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse((currentData.getStartDate() + 'T' + currentData.getStartTime()));
            Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse((currentData.getEndDate() + 'T' + currentData.getEndTime()));
            if(!beginDate.after(currentDate) && !endDate.before(currentDate)){
                allForCorrectDate.add(currentData);
            }
        }
        return allForCorrectDate;
    }
}

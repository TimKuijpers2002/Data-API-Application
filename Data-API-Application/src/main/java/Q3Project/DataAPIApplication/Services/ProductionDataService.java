package Q3Project.DataAPIApplication.Services;

import Q3Project.DataAPIApplication.Model.ProductionData;
import Q3Project.DataAPIApplication.Repository.ProductionDataRepository;
import Q3Project.DataAPIApplication.Repository.TreeviewRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        List<ProductionData> correctProductionData = new ArrayList<>();
        for (ProductionData currentData: allProductionData){
            if (currentData.getTreeviewId() == componentId || currentData.getTreeview2Id() == componentId)
            {
                correctProductionData.add(currentData);
            }
        }
        /*allProductionData.removeIf(item -> item.getTreeviewId() != componentId );
        allProductionData.removeIf(item -> item.getTreeview2Id() != componentId);*/
        return correctProductionData;
    }

    public Set<ProductionData> GetComponentsFromMachine(int board, int port) {
        List<ProductionData> allProductionData = productionDataRepository.findAll();
        allProductionData.removeIf(item -> item.getBoard() != board);
        allProductionData.removeIf(item -> item.getPort() != port);
        return new HashSet<>(allProductionData);
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

    public List<ProductionData>GetBoardAndPortByTreeviewId(long treeviewid)
    {
        return productionDataRepository.findByTreeviewId((int)treeviewid);
    }
}

package Q3Project.DataAPIApplication.Services;

import Q3Project.DataAPIApplication.Enums.TreeviewTypes;
import Q3Project.DataAPIApplication.Model.ProductionData;
import Q3Project.DataAPIApplication.Model.Treeview;
import Q3Project.DataAPIApplication.Repository.ProductionDataRepository;
import Q3Project.DataAPIApplication.Repository.TreeviewRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductionDataService {

    private final ProductionDataRepository productionDataRepository;
    private final TreeviewRepository treeviewRepository;

    public ProductionDataService(ProductionDataRepository productionDataRepository, TreeviewRepository treeviewRepository) {
        this.productionDataRepository = productionDataRepository;
        this.treeviewRepository = treeviewRepository;
    }

    public List<ProductionData> GetComponentsFromMachine(int board, int port) {
        List<ProductionData> allProductionData = productionDataRepository.findAll();
        allProductionData.removeIf(item -> item.getBoard() != board);
        allProductionData.removeIf(item -> item.getPort() != port);
        return allProductionData;
    }

    public List<ProductionData> GetComponentsFromMachineOnDate(int board, int port, Date date, Date time){
        return productionDataRepository.findByBPAndDate(board, port, date, time);
    }
}

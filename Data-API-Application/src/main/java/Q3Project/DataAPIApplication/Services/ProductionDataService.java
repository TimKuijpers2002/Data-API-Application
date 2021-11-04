package Q3Project.DataAPIApplication.Services;

import Q3Project.DataAPIApplication.Model.MonitoringData202009;
import Q3Project.DataAPIApplication.Model.ProductionData;
import Q3Project.DataAPIApplication.Repository.ProductionDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionDataService {

    private final ProductionDataRepository productionDataRepository;

    public ProductionDataService(ProductionDataRepository productionDataRepository) {
        this.productionDataRepository = productionDataRepository;
    }

    public List<ProductionData> GetAllByBoardPort(long board, long port) {
        List<ProductionData> allProductionData = productionDataRepository.findAll();
        allProductionData.removeIf(item -> item.getBoard() != board);
        allProductionData.removeIf(item -> item.getPort() != port);
        return allProductionData;
    }
}

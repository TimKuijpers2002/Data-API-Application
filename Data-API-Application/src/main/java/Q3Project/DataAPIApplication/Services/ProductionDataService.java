package Q3Project.DataAPIApplication.Services;

import Q3Project.DataAPIApplication.Model.ProductionData;
import Q3Project.DataAPIApplication.Repository.ProductionDataRepository;
import Q3Project.DataAPIApplication.Repository.TreeviewRepository;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public List<ProductionData> GetComponentsFromMachineOnDate(int board, int port, String date, String time) throws ParseException {
        Date currentDay = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime());
        Time currentTime = new Time(new SimpleDateFormat("HH:mm:ss").parse(time).getTime());

        return productionDataRepository.findByBPAndDate(board, port, currentDay, currentTime);
    }
}

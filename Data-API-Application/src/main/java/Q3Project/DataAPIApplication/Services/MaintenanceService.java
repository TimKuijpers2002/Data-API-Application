package Q3Project.DataAPIApplication.Services;

import Q3Project.DataAPIApplication.Interface.IMaintenancePlanner;
import Q3Project.DataAPIApplication.Model.Maintenance;
import Q3Project.DataAPIApplication.Repository.MaintenanceRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MaintenanceService implements IMaintenancePlanner {

    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceService(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public List<Maintenance> GetAll() {
        return maintenanceRepository.findAll();
    }

    @Override
    public boolean Create(Maintenance maintenance) {
        maintenanceRepository.save(maintenance);
        return false;
    }

    @Override
    public boolean Update(Maintenance entityOriginal, Maintenance entityDetails) {
        return false;
    }

    @Override
    public void Delete(long id) {

    }

    @Override
    public boolean CheckDoubleTimeDuration(Maintenance maintenance) {
        return false;
    }
}

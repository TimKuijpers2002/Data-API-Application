package Q3Project.DataAPIApplication.Controller;

import Q3Project.DataAPIApplication.CustomException.ResourceNotFoundException;
import Q3Project.DataAPIApplication.Interface.IMaintenancePlanner;
import Q3Project.DataAPIApplication.Model.Maintenance;
import Q3Project.DataAPIApplication.Repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
public class MaintenanceController {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Qualifier("maintenanceService")
    @Autowired
    private IMaintenancePlanner service;

    @Transactional
    @GetMapping("/maintenance/{id}")
    public ResponseEntity<?> GetMaintenanceById(@PathVariable(value = "id") long maintenance_id) throws ResourceNotFoundException {
        Maintenance maintenance = maintenanceRepository.findById(maintenance_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Maintenance could not be found for id:" + maintenance_id));
        return ResponseEntity.ok().body(maintenance);
    }

    @Transactional
    @GetMapping("/maintenance")
    public ResponseEntity<List<Maintenance>> GetAllMaintenances() {
        try {
            List<Maintenance> maintenanceList = service.GetAll();
            return ResponseEntity.ok().body(maintenanceList);
        } catch (
                NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PostMapping("/maintenance")
    public ResponseEntity<?> CreateMaintenance(@RequestBody Maintenance maintenance) {
        boolean hasError = service.Create(maintenance);
        if (hasError) {
            return ResponseEntity.badRequest().body("An error has occurred, time already in use for another maintenance!");
        } else {
            return ResponseEntity.ok(maintenance);
        }
    }

    @Transactional
    @PutMapping("/maintenance/{id}")
    public ResponseEntity<?> UpdateMaintenance(@RequestBody Maintenance maintenanceDetails, @PathVariable(value = "id") long maintenance_id) throws Exception {
        Maintenance maintenance = maintenanceRepository.findById(maintenance_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Employee could not be found for id:" + maintenance_id));
        boolean hasError = service.Update(maintenance, maintenanceDetails);
        if(hasError){
            return ResponseEntity.badRequest().body("Maintenance already planned for date/time:" + maintenanceDetails.getDayOfMaintenance() + "for:" + maintenanceDetails.getMaintenanceTypeId());
        }
        return ResponseEntity.ok().body(maintenance);
    }

    @Transactional
    @DeleteMapping("/maintenance/{id}")
    public ResponseEntity<?> DeleteMaintenance(@PathVariable("id") long maintenance_id) throws ResourceNotFoundException{
        maintenanceRepository.findById(maintenance_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Maintenance could not be found for id:" + maintenance_id));
        service.Delete(maintenance_id);
        return ResponseEntity.ok().build();
    }

}

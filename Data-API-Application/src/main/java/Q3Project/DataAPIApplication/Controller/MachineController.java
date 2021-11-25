package Q3Project.DataAPIApplication.Controller;

import Q3Project.DataAPIApplication.Interface.ITreeviewService;
import Q3Project.DataAPIApplication.Model.Treeview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
public class MachineController {

    @Autowired
    private ITreeviewService service;

    @GetMapping("/machine/{id}")
    public Treeview GetMachineById(@PathVariable(value = "id") long treeviewId){
        return service.GetByTreeviewId(treeviewId);
    }

    @GetMapping("/machines")
    public List<Treeview> GetAllMachines(){
        return service.GetAllMachines();
    }
}

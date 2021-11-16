package Q3Project.DataAPIApplication.Controller;

import Q3Project.DataAPIApplication.Interface.ITreeviewService;
import Q3Project.DataAPIApplication.Model.Treeview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
public class ComponentController {

    @Autowired
    private ITreeviewService service;

    @GetMapping("/component/{id}")
    public Treeview GetComponentById(@PathVariable(value = "id") long treeviewId){
        return service.GetByTreeviewId(treeviewId);
    }

    @GetMapping("/components")
    public List<Treeview> GetAllComponents(){
        return service.GetAllFromComponents();
    }

    @GetMapping("/components/{name}")
    public List<Treeview> GetComponentsFromTreeviewById(@PathVariable(value = "name") String treeviewName){
        return service.GetAllComponentsFromMachine(treeviewName);
    }
}

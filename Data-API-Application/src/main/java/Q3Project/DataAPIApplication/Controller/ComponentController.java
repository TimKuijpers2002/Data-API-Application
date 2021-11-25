package Q3Project.DataAPIApplication.Controller;

import Q3Project.DataAPIApplication.Interface.ITreeviewService;
import Q3Project.DataAPIApplication.Model.Treeview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
        return service.GetAllComponents();
    }

    @GetMapping("/components/{name}")
    public List<Treeview> GetComponentsFromMachine(@PathVariable(value = "name") String treeviewName){
        return service.GetAllComponentsFromMachine(treeviewName);
    }

    @GetMapping("/components/{name}/{datetime}")
    public List<Treeview> GetComponentHistoryFromMachine(@PathVariable(value = "name") String treeviewName, @PathVariable(value = "datetime") String dateTime) throws ParseException {
        return service.GetComponentHistoryFromMachine(treeviewName, dateTime);
    }
}

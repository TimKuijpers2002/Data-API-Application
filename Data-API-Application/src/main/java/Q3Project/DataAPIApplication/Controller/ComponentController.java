package Q3Project.DataAPIApplication.Controller;

import Q3Project.DataAPIApplication.Interface.ITreeviewService;
import Q3Project.DataAPIApplication.Model.Treeview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
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
    public List<Treeview> GetComponentsFromMachine(@PathVariable(value = "name") String treeviewName){
        return service.GetAllComponentsFromMachine(treeviewName);
    }

    @GetMapping("/components/{name}/{datetime}")
    public List<Treeview> GetHistoryComponentsFromMachine(@PathVariable(value = "name") String treeviewName, @PathVariable(value = "datetime") String dateTime) throws ParseException {
        return service.GetHistoryComponentsFromMachine(treeviewName, dateTime);
    }

    //Without The s
    @GetMapping("/componentshots/{name}")
    public int ComponentShotCount(@PathVariable(value = "name") String name) throws ParseException {
        return service.ComponentTotalShotCount(name);
    }
}

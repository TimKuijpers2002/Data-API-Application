package Q3Project.DataAPIApplication.Controller;

import Q3Project.DataAPIApplication.Interface.ITreeviewService;
import Q3Project.DataAPIApplication.Model.Treeview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ComponentController {

    @Autowired
    private ITreeviewService service;

    @GetMapping("/components")
    public List<Treeview> GetAllComponents(){
        return service.GetAllComponents();
    }

}

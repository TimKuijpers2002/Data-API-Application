package Q3Project.DataAPIApplication.Controller;

import Q3Project.DataAPIApplication.Interface.IMachineAndComponentService;
import Q3Project.DataAPIApplication.Model.Treeview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TreeviewController {

    @Autowired
    private IMachineAndComponentService<Treeview> service;

    @GetMapping("/treeview")
    public List<Treeview> GetAllComponent(){
        return service.GetAll();
    }

}

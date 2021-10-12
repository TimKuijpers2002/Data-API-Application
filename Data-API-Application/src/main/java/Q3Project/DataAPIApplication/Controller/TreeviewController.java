package Q3Project.DataAPIApplication.Controller;

import Q3Project.DataAPIApplication.Interface.ITreeviewService;
import Q3Project.DataAPIApplication.Model.Treeview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TreeviewController {

    @Autowired
    private ITreeviewService service;

    @GetMapping("/treeview")
    public List<Treeview> GetAllTreeview(){
        return service.GetAllTreeview();
    }

}

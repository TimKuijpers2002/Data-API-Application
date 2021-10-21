package Q3Project.DataAPIApplication.Services;

import Q3Project.DataAPIApplication.Interface.ITreeviewService;
import Q3Project.DataAPIApplication.Model.Treeview;
import Q3Project.DataAPIApplication.Repository.TreeviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeviewService implements ITreeviewService {

    private final TreeviewRepository treeviewRepository;

    public TreeviewService(TreeviewRepository treeviewRepository) {
        this.treeviewRepository = treeviewRepository;
    }

    public List<Treeview> GetAllTreeviews() {
        return treeviewRepository.findAll();

    }

    @Override
    public List<Treeview> GetAllMachines() {
        List<Treeview> allTreeviews = GetAllTreeviews();
        int machineId = 16;
        allTreeviews.removeIf(item -> item.getTreeviewSoortId() != machineId);
        return allTreeviews;
    }

    @Override
    public List<Treeview> GetAllComponents() {
        List<Treeview> allTreeviews = GetAllTreeviews();
        int componentId = 2;
        allTreeviews.removeIf(item -> item.getTreeviewSoortId() != componentId);
        return allTreeviews;
    }
}

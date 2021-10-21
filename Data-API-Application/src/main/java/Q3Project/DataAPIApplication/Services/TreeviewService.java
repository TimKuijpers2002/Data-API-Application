package Q3Project.DataAPIApplication.Services;

import Q3Project.DataAPIApplication.Enums.TreeviewTypes;
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
        allTreeviews.removeIf(item -> item.getTreeviewSoortId() != TreeviewTypes.MACHINE.treeviewSoortId);
        return allTreeviews;
    }

    @Override
    public List<Treeview> GetAllComponents() {
        List<Treeview> allTreeviews = GetAllTreeviews();
        allTreeviews.removeIf(item -> item.getTreeviewSoortId() != TreeviewTypes.COMPONENT.treeviewSoortId);
        return allTreeviews;
    }
}

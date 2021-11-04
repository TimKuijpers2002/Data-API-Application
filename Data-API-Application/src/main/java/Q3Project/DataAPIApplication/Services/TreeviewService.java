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

    @Override
    public Treeview GetByTreeviewId(long treeviewId) {
        return treeviewRepository.findById(treeviewId).get();
    }

    @Override
    public List<Treeview> GetAll() {
        return treeviewRepository.findAll();
    }

    @Override
    public List<Treeview> GetAllFromMachines() {
        List<Treeview> allTreeviews = GetAll();
        allTreeviews.removeIf(item -> item.getTreeviewSoortId() != TreeviewTypes.MACHINE.treeviewSoortId);
        return allTreeviews;
    }

    @Override
    public List<Treeview> GetAllFromComponents() {
        List<Treeview> allTreeviews = GetAll();
        allTreeviews.removeIf(item -> item.getTreeviewSoortId() != TreeviewTypes.COMPONENT.treeviewSoortId);
        return allTreeviews;
    }
}

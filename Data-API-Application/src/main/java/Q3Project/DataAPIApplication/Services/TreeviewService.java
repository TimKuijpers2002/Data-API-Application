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

    @Override
    public List<Treeview> GetAllTreeview() {
        return treeviewRepository.findAll();

    }
}

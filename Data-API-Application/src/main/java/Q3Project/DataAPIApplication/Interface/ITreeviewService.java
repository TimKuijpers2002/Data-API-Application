package Q3Project.DataAPIApplication.Interface;

import Q3Project.DataAPIApplication.Model.Treeview;

import java.util.List;

public interface ITreeviewService {

    Treeview GetByTreeviewId(long treeviewId);
    List<Treeview> GetAll();
    List<Treeview> GetAllFromMachines();
    List<Treeview> GetAllFromComponents();
    List<Treeview> GetAllComponentsFromMachine(String treeviewName);
}

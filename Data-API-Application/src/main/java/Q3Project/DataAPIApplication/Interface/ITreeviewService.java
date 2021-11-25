package Q3Project.DataAPIApplication.Interface;

import Q3Project.DataAPIApplication.Model.Treeview;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ITreeviewService {

    Treeview GetByTreeviewId(long treeviewId);
    List<Treeview> GetAll();
    List<Treeview> GetAllMachines();
    List<Treeview> GetAllComponents();
    List<Treeview> GetAllComponentsFromMachine(String treeviewName);
    List<Treeview> GetComponentHistoryFromMachine(String treeviewName, String dateTime) throws ParseException;
}

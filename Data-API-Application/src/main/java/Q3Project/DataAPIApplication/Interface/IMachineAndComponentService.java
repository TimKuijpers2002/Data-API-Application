package Q3Project.DataAPIApplication.Interface;

import Q3Project.DataAPIApplication.Model.Treeview;

import java.util.List;

public interface IMachineAndComponentService<T> {

    List<T> GetAll();
    List<T> GetAllFromMachines();
    List<T> GetAllFromComponents();
}

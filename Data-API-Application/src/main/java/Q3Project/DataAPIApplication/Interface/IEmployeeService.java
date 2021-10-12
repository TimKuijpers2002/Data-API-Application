package Q3Project.DataAPIApplication.Interface;

import Q3Project.DataAPIApplication.Model.Employee;

public interface IEmployeeService extends IGeneric<Employee> {

    boolean CheckDoubleEmail(Employee employee);
}

package Q3Project.DataAPIApplication.Services;

import Q3Project.DataAPIApplication.Interface.IEmployeeService;
import Q3Project.DataAPIApplication.Model.Employee;
import Q3Project.DataAPIApplication.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean Create(Employee employee){
        if(CheckDoubleEmail(employee)){
            return CheckDoubleEmail(employee);
        }
        employeeRepository.save(employee);
        return false;
    }

    @Override
    public List<Employee> GetAll(){
        return employeeRepository.findAll();
    }

    @Override
    public boolean Update(Employee employeeOriginal, Employee employeeDetails){
        if(CheckDoubleEmail(employeeDetails)){
            return CheckDoubleEmail(employeeDetails);
        }
        employeeOriginal.setName(employeeDetails.getName());
        employeeOriginal.setEmail(employeeDetails.getEmail());
        employeeOriginal.setPassword(employeeDetails.getPassword());
        employeeOriginal.setRoles(employeeDetails.getRoles());
        employeeRepository.save(employeeOriginal);
        return false;
    }

    @Override
    public void Delete(long employeeId){
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public boolean CheckDoubleEmail(Employee employee){
        boolean doubleValues = false;
        var getAllEmployee = employeeRepository.findAll();

        for (var item :getAllEmployee) {
            if (employee.getEmail().equals(item.getEmail())) {
                doubleValues = true;
                 break;
            }
        }
        return doubleValues;
    }
}

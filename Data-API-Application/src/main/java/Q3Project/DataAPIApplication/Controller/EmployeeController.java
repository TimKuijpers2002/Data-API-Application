package Q3Project.DataAPIApplication.Controller;

import Q3Project.DataAPIApplication.Interface.IEmployeeService;
import Q3Project.DataAPIApplication.Interface.IGeneric;
import Q3Project.DataAPIApplication.Model.Employee;
import Q3Project.DataAPIApplication.Repository.EmployeeRepository;
import Q3Project.DataAPIApplication.CustomException.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Qualifier("employeeService")
    @Autowired
    private IEmployeeService service;

    @Transactional
    @GetMapping("/employees/{id}")
    public ResponseEntity<?> GetEmployeeById(@PathVariable(value = "id") long employee_id) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employee_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Employee could not be found for id:" + employee_id));
        return ResponseEntity.ok().body(employee);
    }

    @Transactional
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> GetAllEmployee() {
        try {
            List<Employee> employeeList = service.GetAll();
            return ResponseEntity.ok().body(employeeList);
        } catch (
                NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PostMapping("/employees")
    public ResponseEntity<?> CreateEmployee(@RequestBody Employee employee) {
        boolean hasError = service.Create(employee);
        if (hasError) {
            return ResponseEntity.badRequest().body("An error has occurred, email already connected to an account!");
        } else {
            return ResponseEntity.ok(employee);
        }
    }

    @Transactional
    @PutMapping("/employees/{id}")
    public ResponseEntity<?> UpdateEmployee(@RequestBody Employee employeeDetails, @PathVariable(value = "id") long employee_id) throws Exception {
        Employee employee = employeeRepository.findById(employee_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Employee could not be found for id:" + employee_id));
        boolean hasError = service.Update(employee, employeeDetails);
        if(hasError){
            return ResponseEntity.badRequest().body("Employee already exist for email:" + employeeDetails.getEmail());
        }
        return ResponseEntity.ok().body(employee);
    }

    @Transactional
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> DeleteEmployee(@PathVariable("id") long employee_id) throws ResourceNotFoundException{
        employeeRepository.findById(employee_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Employee could not be found for id:" + employee_id));
        employeeRepository.deleteById(employee_id);
        return ResponseEntity.ok().build();
    }
}

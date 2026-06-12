package com.academy.course.mapper;

import com.academy.course.dto.EmployeeDTO;
import com.academy.course.exception.EmptyEntityException;
import com.academy.course.model.Employee;
import com.academy.course.service.validator.EmptyFieldValidator;

import java.util.HashSet;
import java.util.Set;

public class EmployeeMapper implements Mapper<Employee, EmployeeDTO>, EmptyFieldValidator<Object> {

    private final OrderMapper orderMapper;

    public EmployeeMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }


    @Override
    public EmployeeDTO mapToDTO(Employee entity) {
        validateField(entity);
        return EmployeeDTO.builder()
                .id(entity.getId())
                .role(entity.getRole())
                .login(entity.getLogin())
                .orderDTOs(orderMapper.mapToSetDTOS(entity.getOrders()))
                .build();
    }

    @Override
    public Employee mapToEntity(EmployeeDTO dto) {
        validateField(dto);
        return Employee.builder()
                .orders(orderMapper.mapToSetEntities(dto.getOrderDTOs()))
                .role(dto.getRole())
                .login(dto.getLogin())
                .build();
    }

    @Override
    public Set<Employee> mapToSetEntities(Set<EmployeeDTO> dtoSet) {
        Set<Employee> list = new HashSet<>();
        for (EmployeeDTO employeeDTO : dtoSet){
            Employee employee = Employee.builder()
                    .role(employeeDTO.getRole())
                    .login(employeeDTO.getLogin())
                    .build();
            list.add(employee);
        }
        return list;
    }

    @Override
    public Set<EmployeeDTO> mapToSetDTOS(Set<Employee> entitySet) {
        Set<EmployeeDTO> set = new HashSet<>();
        for (Employee employee : entitySet){
            EmployeeDTO employeeDTO = EmployeeDTO.builder()
                    .id(employee.getId())
                    .role(employee.getRole())
                    .login(employee.getLogin())
                    .orderDTOs(orderMapper.mapToSetDTOS(employee.getOrders()))
                    .build();
            set.add(employeeDTO);
        }
        return set;
    }



    @Override
    public void validateField(Object object) {
        if (object == null) {
            throw new EmptyEntityException(object);
        }
    }
}

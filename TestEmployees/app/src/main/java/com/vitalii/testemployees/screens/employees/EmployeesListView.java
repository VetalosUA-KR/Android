package com.vitalii.testemployees.screens.employees;

import com.vitalii.testemployees.pojo.Employee;

import java.util.List;

public interface EmployeesListView {

    void showData(List<Employee> employees);
    void showError();
}

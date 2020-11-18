package com.vitalii.testemployees.screens.employees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.vitalii.testemployees.R;
import com.vitalii.testemployees.adapters.EmployeeAdapter;
import com.vitalii.testemployees.api.ApiFactory;
import com.vitalii.testemployees.api.ApiService;
import com.vitalii.testemployees.pojo.Employee;
import com.vitalii.testemployees.pojo.EmployeeResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmployeeListActivity extends AppCompatActivity implements EmployeesListView {

    private RecyclerView recyclerViewEmployees;
    private EmployeeAdapter adapter;
    private EmployeeListPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new EmployeeListPresenter(this);

        recyclerViewEmployees = findViewById(R.id.recyclerViewEmployees);
        adapter = new EmployeeAdapter();
        adapter.setEmployees(new ArrayList<>());
        recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEmployees.setAdapter(adapter);
        presenter.loadData();

//        List<Employee> employees = new ArrayList<>();
//        Employee employee1 = new Employee();
//        Employee employee2 = new Employee();
//        employee1.setName("Vitalii");
//        employee2.setName("Denis");
//        employee1.setLName("Voitenko");
//        employee2.setLName("Non");
//        employees.add(employee1);
//        employees.add(employee2);
//        adapter.setEmployees(employees);

    }

    public void showData(List<Employee> employees) {
        adapter.setEmployees(employees);
    }

    public void showError() {
        Toast.makeText(this, "Some error", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        presenter.disposeDisposable();
        super.onDestroy();
    }
}
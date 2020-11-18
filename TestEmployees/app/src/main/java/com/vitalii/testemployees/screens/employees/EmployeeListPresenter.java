package com.vitalii.testemployees.screens.employees;

import android.widget.Toast;

import com.vitalii.testemployees.api.ApiFactory;
import com.vitalii.testemployees.api.ApiService;
import com.vitalii.testemployees.pojo.EmployeeResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmployeeListPresenter {

    private CompositeDisposable compositeDisposable;
    private EmployeesListView view;

    public EmployeeListPresenter(EmployeesListView view) {
        this.view = view;
    }

    public void loadData() {
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        compositeDisposable = new CompositeDisposable();
        Disposable disposable = apiService.getEmployees()
                //показываем в каком потоке делаем запрос к БД, все обращения к БД или скачивание с интернета делается в "Schedulers.io()"
                .subscribeOn(Schedulers.io())
                //указваем в каком потоке мы будетм принимать данные(в главном потоке)
                .observeOn(AndroidSchedulers.mainThread())
                //указываем что делать когда получим данные
                .subscribe(new Consumer<EmployeeResponse>() {
                    @Override
                    //Выполняется в случаее если данные были загружены успешно
                    public void accept(EmployeeResponse employeeResponse) throws Exception {
                        view.showData(employeeResponse.getResponse());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    //Исключение, если загрузка прошла не успешно
                    public void accept(Throwable throwable) throws Exception {
                        view.showError();
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void disposeDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}

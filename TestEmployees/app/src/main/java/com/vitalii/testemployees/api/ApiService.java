package com.vitalii.testemployees.api;

import com.vitalii.testemployees.pojo.EmployeeResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET("testTask.json")
    Observable<EmployeeResponse> getEmployees();
}

package de.idnow.sampleproject.utils;

import java.util.List;

import de.idnow.sampleproject.model.Task;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sean on 12/29/17.
 */

public interface TodoListService {

    @GET("api/tasks")
    Call<List<Task>> getTasks();

}

package de.idnow.sampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.idnow.R;
import de.idnow.sampleproject.model.Task;
import de.idnow.sampleproject.utils.APIClient;
import de.idnow.sampleproject.utils.TodoListService;
import retrofit2.Call;

public class TodoListActivity extends AppCompatActivity implements TodoDialog.TodoDialogListener, TodoListAdapter.ClickListener {

    @BindView(R.id.tuanTextView)
    TextView tuanTextView;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.todo_popup_button)
    ImageButton popupButton;

    private List<Task> todoList = new ArrayList<>();
    private TodoListAdapter todoListAdapter;
    private TodoListService todoListService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        ButterKnife.bind(this);

        // Get email for welcome message
        Intent intent = getIntent();
        final String message = intent.getStringExtra(TuanActivity.TUAN_MESSAGE);
        tuanTextView.setText(message);

        // Create RecylcleListAdapter
        todoListAdapter = new TodoListAdapter(todoList);
        todoListAdapter.setClickListener(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // adding inbuilt divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp
        // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(todoListAdapter);

        prepareTodoList();

//        todoList.remove(3);

//        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                Toast.makeText(TodoListActivity.this, "Single Click on position:" +position, Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//                Toast.makeText(TodoListActivity.this, "Long press on position:" +position, Toast.LENGTH_LONG).show();
//                todoList.remove(position);
//                todoListAdapter.notifyDataSetChanged();
//            }
//        }));

        // Set Add to do list's tasks event
//        todoButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String todoNameText = todoName.getText().toString();
//                String todoDescriptionText = todoDescription.getText().toString();
//                if (!todoNameText.isEmpty() && !todoDescriptionText.isEmpty()) {
//                    todoList.add(new Task(todoNameText, todoDescriptionText));
//                    todoListAdapter.notifyDataSetChanged();
//                } else {
//                    Toast.makeText(TodoListActivity.this, "Name and description cannot be empty !", 10).show();
//                }
//            }
//        });

        // Initialize TodoService
        todoListService = APIClient.getClient().create(TodoListService.class);

        // GET tasks from TodoService
        Call<List<Task>> call = todoListService.getTasks();
//        call.enqueue(new Callback<List<Task>>() {
//            @Override
//            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
//                todoList.addAll(response.body());
//                Log.d("Android", response.toString());
//                todoListAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<List<Task>> call, Throwable t) {
//                call.cancel();
//                Log.d("Android", t.getMessage());
//            }
//        });


        // Open task creation dialog
        popupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTodoDialog();
            }
        });
    }


    private void openTodoDialog() {
        TodoDialog todoDialog = new TodoDialog();
        todoDialog.show(getSupportFragmentManager(), "fdasf");
    }

    private void prepareTodoList() {
        Task task = new Task("eat", "Fucking eating, Bitch !");
        todoList.add(task);

        task = new Task("sleep", "Fucking sleeping, Bitch !");
        todoList.add(task);

        task = new Task("code", "Fucking coding, Bitch !");
        todoList.add(task);

        todoListAdapter.notifyDataSetChanged();
    }

    @Override
    public void saveTaskData(String name, String description) {
        todoList.add(new Task(name, description));
        todoListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view, int position) {
        todoList.remove(position);
        todoListAdapter.notifyDataSetChanged();
        Toast.makeText(TodoListActivity.this, "Single Click on position:" +position, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLongClick(View view, int position) {
        Toast.makeText(TodoListActivity.this, "Long Click on position:" +position, Toast.LENGTH_LONG).show();
    }

//    public static interface ClickListener {
//        public void onClick(View view, int position);
//        public  void onLongClick(View view, int position);
//    }
//
//    public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
//
//        private ClickListener clickListener;
//        private GestureDetector gestureDetector;
//
//        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
//
//            this.clickListener = clickListener;
//            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
//                @Override
//                public boolean onSingleTapUp(MotionEvent e) {
//                    return true;
//                }
//
//                @Override
//                public void onLongPress(MotionEvent e) {
//                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
//                    if (child != null && clickListener != null) {
//                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
//                    }
//                }
//            });
//
//        }
//
//        @Override
//        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
//            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
//                clickListener.onClick(child, rv.getChildAdapterPosition(child));
//            }
//            return false;
//        }
//
//        @Override
//        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//
//        }
//
//        @Override
//        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//        }
//    }
}

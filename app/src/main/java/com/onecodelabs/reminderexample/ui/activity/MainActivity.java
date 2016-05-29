package com.onecodelabs.reminderexample.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.onecodelabs.reminder.Remindable;
import com.onecodelabs.reminder.Reminder;
import com.onecodelabs.reminder.bundle.ReminderBundle;
import com.onecodelabs.reminderexample.R;
import com.onecodelabs.reminderexample.ReminderApplication;
import com.onecodelabs.reminderexample.adapter.QuestionAdapter;
import com.onecodelabs.reminderexample.model.Question;
import com.onecodelabs.reminderexample.model.Questions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Remindable {

    private static final String SNAPSHOT_DATASET = "DATASET";
    private static final long MILLIS_HOUR = 1000 * 60 * 60;

    private ListView listView;
    private QuestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUi();
        init();
        Reminder.remind(this);
    }

    private void setUi() {
        this.listView = (ListView) findViewById(R.id.activity_main_listview);
    }

    private void init() {
        adapter = new QuestionAdapter(this);
        listView.setAdapter(adapter);
    }

    private void fetchQuestions() {
        ReminderApplication
                .getInstance()
                .getQuestionService()
                .getQuestions()
                .enqueue(new Callback<List<Question>>() {
                    @Override
                    public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                        List<Question> questions = response.body();

                        //remove old data
                        adapter.clear();

                        //add fresh data
                        adapter.appendBottomAll(questions);
                    }

                    @Override
                    public void onFailure(Call<List<Question>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void saveSnapshot(ReminderBundle snapshot) {
        snapshot.put(SNAPSHOT_DATASET, new Questions(adapter.getDataSet()));
    }

    @Override
    public void onSnapshotAvailable(ReminderBundle snapshot) {
        List<Question> questions = snapshot.get(SNAPSHOT_DATASET, Questions.class).getQuestions();
        adapter.clear();
        adapter.appendBottomAll(questions);

        //Check how old the snapshot is. Fetch new content if it's 8 hours old or more.
        if (snapshot.isMillisecondsOld(8 * MILLIS_HOUR)) {
            fetchQuestions();
        }
    }

    @Override
    public void onSnapshotNotFound() {
        fetchQuestions();
    }
}

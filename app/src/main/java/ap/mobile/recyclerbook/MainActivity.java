package ap.mobile.recyclerbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Student> students;
    private BookAdapter bookAdapter;
    private Button btAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Student m1 = new Student("Si A", "a@email.com", "0811");
        Student m2 = new Student("Si B", "b@email.com", "0812");
        Student m3 = new Student("Si C", "c@email.com", "0813");
        Student m4 = new Student("Si D", "d@email.com", "0814");
        Student m5 = new Student("Si E", "e@email.com", "0815");

        this.students = new ArrayList<>();
        students.add(m1);
        students.add(m2);
        students.add(m3);
        students.add(m4);
        students.add(m5);

        // initialize RecyclerView
        RecyclerView rvBook = (RecyclerView) this.findViewById(R.id.rv_book);

        // initialize BookAdapter
        this.bookAdapter = new BookAdapter(this, students);

        // set adapter to view
        rvBook.setAdapter(bookAdapter);

        // set view layout
        rvBook.setLayoutManager(new LinearLayoutManager(this));

        this.btAdd = (Button) this.findViewById(R.id.bt_add);

        this.btAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_add:
                //add data to last stack
                this.students.add(new Student("Si X", "x@email.com", "0899"));

                //refresh adapter data changed
                this.bookAdapter.notifyDataSetChanged();

                break;
        }
    }
}

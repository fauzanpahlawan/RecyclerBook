package ap.mobile.recyclerbook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.RowHolder> {


    private ArrayList<Student> students;
    private LayoutInflater inflater;

    public BookAdapter(Context context, ArrayList<Student> students) {
        this.inflater = LayoutInflater.from(context);
        this.students = students;
    }


    @Override
    public BookAdapter.RowHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //infate View as holder from XML
        View holder = this.inflater.inflate(R.layout.book_item,
                parent, false);

        return new RowHolder(holder);
    }

    @Override
    public void onBindViewHolder(BookAdapter.RowHolder holder, int position) {

        Student m = this.students.get(position);

        // map Student Attribute holder
        holder.tvName.setText(m.name);
        holder.tvPhone.setText(m.phone);
        holder.tvEmail.setText(m.email);

        // saving index of holder
        holder.position = position;

    }

    @Override
    public int getItemCount() {
        return this.students.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final Context context;
        private TextView tvName;
        private TextView tvPhone;
        private TextView tvEmail;
        public int position;


        public RowHolder(View itemView) {


            super(itemView);
            context = itemView.getContext();

            this.tvName = itemView.findViewById(R.id.tv_name);
            this.tvPhone = itemView.findViewById(R.id.tv_phone);
            this.tvEmail = itemView.findViewById(R.id.tv_email);

            this.tvName.setOnClickListener(this);
            this.tvPhone.setOnClickListener(this);
            this.tvEmail.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.tv_name:
                    intentName(tvName.getText().toString());
                    break;
                case R.id.tv_phone:
                    intentPhone(tvPhone.getText().toString());
                    break;
                case R.id.tv_email:
                    intentEmail(tvEmail.getText().toString());
                    break;
            }
        }

        void intentName(String name) {
            Uri uri = Uri.parse("http://www.google.com/#q=" + name);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            }
        }

        void intentPhone(String phone) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phone));
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            }
        }

        void intentEmail(String email) {
            String mailto = "mailto:" + email;
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse(mailto));
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            }
        }
    }
}

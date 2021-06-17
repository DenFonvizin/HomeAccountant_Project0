package android.example.midterm;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.example.midterm.db.DatabaseOpenHelper;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.MyViewHolder2> {

    Context nContext2;
    List<RawInfo> infoList2;
    Dialog dialog;
    String table_name;

    public RecyclerViewAdapter2(Context nContext2, List<RawInfo> infoList2){
        this.nContext2 = nContext2;
        this.infoList2 = infoList2;
    }

    public RecyclerViewAdapter2(Context nContext2, List<RawInfo> infoList2, String table_name){
        this.nContext2 = nContext2;
        this.infoList2 = infoList2;
        this.table_name = table_name;
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(nContext2).inflate(R.layout.row2, parent, false);
        MyViewHolder2 vHolder2 = new MyViewHolder2(v);

        dialog = new Dialog(nContext2);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        vHolder2.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView dialog_title = (TextView) dialog.findViewById(R.id.dialog_title);
                TextView dialog_amount = (TextView) dialog.findViewById(R.id.dialog_amount);
                TextView dialog_number = (TextView) dialog.findViewById(R.id.dialog_icon_number);
                ImageView imageView = (ImageView) dialog.findViewById(R.id.image_of_check);
                ImageButton imageButton = (ImageButton) dialog.findViewById(R.id.button_delete);

                dialog_title.setText(infoList2.get(vHolder2.getAdapterPosition()).getTitle());
                dialog_amount.setText(infoList2.get(vHolder2.getAdapterPosition()).getAmount() + " $");
                dialog_number.setText(Integer.toString(vHolder2.getAdapterPosition() + 1));

                if(infoList2.get(vHolder2.getAdapterPosition()).getPhoto() != null){
                    imageView.setImageURI(Uri.fromFile(new File(infoList2.get(vHolder2.getAdapterPosition()).getPhoto())));
                }

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent activity2intent = new Intent(nContext2, CameraWork.class);
                        activity2intent.putExtra("Session_ID", vHolder2.getAdapterPosition());
                        activity2intent.putExtra("Table_name", table_name);
                        activity2intent.putExtra("_ID", infoList2.get(vHolder2.getAdapterPosition()).get_ID());
                        nContext2.startActivity(activity2intent);
                        dialog.dismiss();
                    }
                });

                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseOpenHelper access = new DatabaseOpenHelper(dialog.getContext());
                        access.deleteByID(table_name, infoList2.get(vHolder2.getAdapterPosition()).get_ID());
                        Toast.makeText(v.getContext(), "Deleted!", Toast.LENGTH_SHORT). show();
                        dialog.dismiss();
                        ((Activity_List_DB) nContext2).onResume();
                    }
                });

                dialog.show();
            }
        });

        return vHolder2;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position){
        RawInfo info = infoList2.get(position);
        holder.numberView.setText(Integer.toString(position + 1));
        holder.titleView.setText(infoList2.get(position).getTitle());
        holder.amountView.setText(infoList2.get(position).getAmount() + " $");
        holder.dateView.setText(infoList2.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return infoList2.size();
    }

    public static class MyViewHolder2 extends RecyclerView.ViewHolder{

        private LinearLayout linearLayout;

        private TextView titleView;

        private TextView amountView;

        private TextView numberView;

        private TextView dateView;


        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.row_id_2);
            titleView = (TextView) itemView.findViewById(R.id.list_title_2);
            amountView = (TextView) itemView.findViewById(R.id.list_amount_2);
            numberView = (TextView) itemView.findViewById(R.id.number_list);
            dateView = (TextView) itemView.findViewById(R.id.list_date_2);

        }
    }
}

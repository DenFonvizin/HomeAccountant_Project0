package android.example.midterm;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder>{

    Context nContext;
    List<RawInfo> infoList;
    int icons[];

    public RecycleViewAdapter(Context nContext, List<RawInfo> infoList, int icons[]) {
        this.nContext = nContext;
        this.infoList = infoList;
        this.icons = icons;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(nContext).inflate(R.layout.row, parent, false);
        MyViewHolder vHolder = new MyViewHolder(v);

        vHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity2intent = new Intent(nContext, Activity_List_DB.class);
                activity2intent.putExtra("Session_ID", vHolder.getAdapterPosition());
                activity2intent.putExtra("Table_name", infoList.get(vHolder.getAdapterPosition()).getTitle());
                nContext.startActivity(activity2intent);
            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleView.setText(infoList.get(position).getTitle());
        holder.amountView.setText(infoList.get(position).getAmount() + " $");
        holder.imageView.setImageResource(icons[position]);
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout linearLayout;

        private TextView titleView;

        private TextView amountView;

        private ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.row_id);
            titleView = (TextView) itemView.findViewById(R.id.list_title);
            amountView = (TextView) itemView.findViewById(R.id.list_amount);
            imageView = (ImageView) itemView.findViewById(R.id.icon_title);
        }

    }
}

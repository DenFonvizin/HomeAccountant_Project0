package android.example.midterm;

import android.example.midterm.db.DatabaseOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CurrentMonthFragment extends Fragment {
    View view;

    RecyclerView recyclerView;

    private List<RawInfo> raws;

    DatabaseOpenHelper access;

    int icons[] = {R.drawable.category_house, R.drawable.category_health, R.drawable.category_transport, R.drawable.category_car,
            R.drawable.category_family, R.drawable.category_food, R.drawable.category_clothes, R.drawable.category_sport,
            R.drawable.category_ent, R.drawable.category_service, R.drawable.category_edu, R.drawable.category_others};

    public CurrentMonthFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        raws = new ArrayList<RawInfo>();

        access = new DatabaseOpenHelper(getActivity());

        raws.add(new RawInfo("Household", access.getTotalAmountCur("Household")));
        raws.add(new RawInfo("Health", access.getTotalAmountCur("Health")));
        raws.add(new RawInfo("Transport", access.getTotalAmountCur("Transport")));
        raws.add(new RawInfo("Car", access.getTotalAmountCur("Car")));
        raws.add(new RawInfo("Family", access.getTotalAmountCur("Family")));
        raws.add(new RawInfo("Food", access.getTotalAmountCur("Food")));
        raws.add(new RawInfo("Clothes", access.getTotalAmountCur("Clothes")));
        raws.add(new RawInfo("Sport", access.getTotalAmountCur("Sport")));
        raws.add(new RawInfo("Entertainment", access.getTotalAmountCur("Entertainment")));
        raws.add(new RawInfo("Service", access.getTotalAmountCur("Service")));
        raws.add(new RawInfo("Education", access.getTotalAmountCur("Education")));
        raws.add(new RawInfo("Others", access.getTotalAmountCur("Others")));

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        view = inflater.inflate(R.layout.current_month_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);

        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(getContext(), raws, icons);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));

        recyclerView.setAdapter(recycleViewAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        raws = new ArrayList<RawInfo>();

        access = new DatabaseOpenHelper(getActivity());

        raws.add(new RawInfo("Household", access.getTotalAmountCur("Household")));
        raws.add(new RawInfo("Health", access.getTotalAmountCur("Health")));
        raws.add(new RawInfo("Transport", access.getTotalAmountCur("Transport")));
        raws.add(new RawInfo("Car", access.getTotalAmountCur("Car")));
        raws.add(new RawInfo("Family", access.getTotalAmountCur("Family")));
        raws.add(new RawInfo("Food", access.getTotalAmountCur("Food")));
        raws.add(new RawInfo("Clothes", access.getTotalAmountCur("Clothes")));
        raws.add(new RawInfo("Sport", access.getTotalAmountCur("Sport")));
        raws.add(new RawInfo("Entertainment", access.getTotalAmountCur("Entertainment")));
        raws.add(new RawInfo("Service", access.getTotalAmountCur("Service")));
        raws.add(new RawInfo("Education", access.getTotalAmountCur("Education")));
        raws.add(new RawInfo("Others", access.getTotalAmountCur("Others")));

        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(getContext(), raws, icons);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));

        recyclerView.setAdapter(recycleViewAdapter);
    }
}

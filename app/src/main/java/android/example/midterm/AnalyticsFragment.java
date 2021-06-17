package android.example.midterm;

import android.example.midterm.db.DatabaseOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.List;

public class AnalyticsFragment extends Fragment {

    List<RawInfo> raws;
    List<RawInfo> rawsCur;

    PieChart pieChart;

    View view;

    TextView textView1, textView2, textView3, textView4, textView5, textView6;
    TextView textView7, textView8, textView9, textView10, textView11, textView12;

    DatabaseOpenHelper access;

    public AnalyticsFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        raws = new ArrayList<RawInfo>();
        rawsCur = new ArrayList<RawInfo>();
        int icons[] = {R.drawable.category_house, R.drawable.category_health, R.drawable.category_transport, R.drawable.category_car,
                R.drawable.category_family, R.drawable.category_food, R.drawable.category_clothes, R.drawable.category_sport,
                R.drawable.category_ent, R.drawable.category_service, R.drawable.category_edu, R.drawable.category_others};

        access = new DatabaseOpenHelper(getActivity());

        raws.add(new RawInfo("Household", access.getTotalAmount("Household")));
        raws.add(new RawInfo("Health", access.getTotalAmount("Health")));
        raws.add(new RawInfo("Transport", access.getTotalAmount("Transport")));
        raws.add(new RawInfo("Car", access.getTotalAmount("Car")));
        raws.add(new RawInfo("Family", access.getTotalAmount("Family")));
        raws.add(new RawInfo("Food", access.getTotalAmount("Food")));
        raws.add(new RawInfo("Clothes", access.getTotalAmount("Clothes")));
        raws.add(new RawInfo("Sport", access.getTotalAmount("Sport")));
        raws.add(new RawInfo("Entertainment", access.getTotalAmount("Entertainment")));
        raws.add(new RawInfo("Service", access.getTotalAmount("Service")));
        raws.add(new RawInfo("Education", access.getTotalAmount("Education")));
        raws.add(new RawInfo("Others", access.getTotalAmount("Others")));

        rawsCur.add(new RawInfo("Household", access.getTotalAmountCur("Household")));
        rawsCur.add(new RawInfo("Health", access.getTotalAmount("Health")));
        rawsCur.add(new RawInfo("Transport", access.getTotalAmount("Transport")));
        rawsCur.add(new RawInfo("Car", access.getTotalAmount("Car")));
        rawsCur.add(new RawInfo("Family", access.getTotalAmount("Family")));
        rawsCur.add(new RawInfo("Food", access.getTotalAmount("Food")));
        rawsCur.add(new RawInfo("Clothes", access.getTotalAmount("Clothes")));
        rawsCur.add(new RawInfo("Sport", access.getTotalAmount("Sport")));
        rawsCur.add(new RawInfo("Entertainment", access.getTotalAmount("Entertainment")));
        rawsCur.add(new RawInfo("Service", access.getTotalAmount("Service")));
        rawsCur.add(new RawInfo("Education", access.getTotalAmount("Education")));
        rawsCur.add(new RawInfo("Others", access.getTotalAmount("Others")));

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.analytics_fragment, container, false);

        textView1 = (TextView) view.findViewById(R.id.textView1);
        textView2 = (TextView) view.findViewById(R.id.textView2);
        textView3 = (TextView) view.findViewById(R.id.textView3);
        textView4 = (TextView) view.findViewById(R.id.textView4);
        textView5 = (TextView) view.findViewById(R.id.textView5);
        textView6 = (TextView) view.findViewById(R.id.textView6);

        textView7 = (TextView) view.findViewById(R.id.textView7);
        textView8 = (TextView) view.findViewById(R.id.textView8);
        textView9 = (TextView) view.findViewById(R.id.textView9);
        textView10 = (TextView) view.findViewById(R.id.textView10);
        textView11 = (TextView) view.findViewById(R.id.textView11);
        textView12 = (TextView) view.findViewById(R.id.textView12);

        pieChart = (PieChart) view.findViewById(R.id.piechart);

        setData();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        raws = new ArrayList<RawInfo>();
        rawsCur = new ArrayList<RawInfo>();

        access = new DatabaseOpenHelper(getActivity());

        raws.add(new RawInfo("Household", access.getTotalAmount("Household")));
        raws.add(new RawInfo("Health", access.getTotalAmount("Health")));
        raws.add(new RawInfo("Transport", access.getTotalAmount("Transport")));
        raws.add(new RawInfo("Car", access.getTotalAmount("Car")));
        raws.add(new RawInfo("Family", access.getTotalAmount("Family")));
        raws.add(new RawInfo("Food", access.getTotalAmount("Food")));
        raws.add(new RawInfo("Clothes", access.getTotalAmount("Clothes")));
        raws.add(new RawInfo("Sport", access.getTotalAmount("Sport")));
        raws.add(new RawInfo("Entertainment", access.getTotalAmount("Entertainment")));
        raws.add(new RawInfo("Service", access.getTotalAmount("Service")));
        raws.add(new RawInfo("Education", access.getTotalAmount("Education")));
        raws.add(new RawInfo("Others", access.getTotalAmount("Others")));

        rawsCur.add(new RawInfo("Household", access.getTotalAmountCur("Household")));
        rawsCur.add(new RawInfo("Health", access.getTotalAmount("Health")));
        rawsCur.add(new RawInfo("Transport", access.getTotalAmount("Transport")));
        rawsCur.add(new RawInfo("Car", access.getTotalAmount("Car")));
        rawsCur.add(new RawInfo("Family", access.getTotalAmount("Family")));
        rawsCur.add(new RawInfo("Food", access.getTotalAmount("Food")));
        rawsCur.add(new RawInfo("Clothes", access.getTotalAmount("Clothes")));
        rawsCur.add(new RawInfo("Sport", access.getTotalAmount("Sport")));
        rawsCur.add(new RawInfo("Entertainment", access.getTotalAmount("Entertainment")));
        rawsCur.add(new RawInfo("Service", access.getTotalAmount("Service")));
        rawsCur.add(new RawInfo("Education", access.getTotalAmount("Education")));
        rawsCur.add(new RawInfo("Others", access.getTotalAmount("Others")));

        setData();
    }

    private void setData(){

        int total = 0;
        long[] percentages = new long[12];
        for(int i = 0; i < rawsCur.size(); i++){
           total = total + rawsCur.get(i).getAmountint();
        }

        for(int i = 0; i < rawsCur.size(); i++){
            if(rawsCur.get(i).getAmountint() == 0){
                percentages[i] = 0;
            }
            else {
                percentages[i] = Math.round(100.0 * Double.parseDouble(rawsCur.get(i).getAmount()) / total);
            }
            Log.d("Analytics Fragment", String.valueOf(percentages));
        }

        pieChart.clearChart();

        pieChart.addPieSlice(
                new PieModel(
                        "Household",
                        raws.get(0).getAmountint(),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Health",
                        raws.get(1).getAmountint(),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Transport",
                        raws.get(2).getAmountint(),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "Car",
                        raws.get(3).getAmountint(),
                        Color.parseColor("#29B6F6")));
        pieChart.addPieSlice(
                new PieModel(
                        "Family",
                        raws.get(4).getAmountint(),
                        Color.parseColor("#4D13E1")));
        pieChart.addPieSlice(
                new PieModel(
                        "Food",
                        raws.get(5).getAmountint(),
                        Color.parseColor("#74350C")));
        pieChart.addPieSlice(
                new PieModel(
                        "Clothes",
                        raws.get(6).getAmountint(),
                        Color.parseColor("#EB00F3")));
        pieChart.addPieSlice(
                new PieModel(
                        "Sport",
                        raws.get(7).getAmountint(),
                        Color.parseColor("#1FDEC3")));
        pieChart.addPieSlice(
                new PieModel(
                        "Entertainment",
                        raws.get(8).getAmountint(),
                        Color.parseColor("#664E32")));
        pieChart.addPieSlice(
                new PieModel(
                        "Service",
                        raws.get(9).getAmountint(),
                        Color.parseColor("#FAFC99")));
        pieChart.addPieSlice(
                new PieModel(
                        "Education",
                        raws.get(10).getAmountint(),
                        Color.parseColor("#91E91F")));
        pieChart.addPieSlice(
                new PieModel(
                        "Other",
                        raws.get(11).getAmountint(),
                        Color.parseColor("#1E4F05")));

        pieChart.setInnerValueString("TOTAL");
        pieChart.setInnerValueSize(48);
        pieChart.setUseInnerValue(true);

        pieChart.startAnimation();

        textView1.setText(String.valueOf(percentages[0]) + " %");
        textView2.setText(String.valueOf(percentages[1]) + " %");
        textView3.setText(String.valueOf(percentages[2]) + " %");
        textView4.setText(String.valueOf(percentages[3]) + " %");
        textView5.setText(String.valueOf(percentages[4]) + " %");
        textView6.setText(String.valueOf(percentages[5]) + " %");
        textView7.setText(String.valueOf(percentages[6]) + " %");
        textView8.setText(String.valueOf(percentages[7]) + " %");
        textView9.setText(String.valueOf(percentages[8]) + " %");
        textView10.setText(String.valueOf(percentages[9]) + " %");
        textView11.setText(String.valueOf(percentages[10]) + " %");
        textView12.setText(String.valueOf(percentages[11]) + " %");
    }
}

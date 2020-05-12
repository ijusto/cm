package pt.ua.nextweather.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.Objects;

import pt.ua.nextweather.R;
import pt.ua.nextweather.datamodel.City;

public class CityListAdapter extends
        RecyclerView.Adapter<CityListAdapter.CityViewHolder>  {

    private ItemClickListener clickListener;
    private HashMap<String, City> mCityList;
    private Context mContext;
    /* To create a View for a list item, the CityListAdapter needs to inflate the XML for a list
       item. You use a layout inflator for that job. LayoutInflator reads a layout XML description
       and converts it into the corresponding View items. */
    private LayoutInflater mInflater;


    public CityListAdapter(Context context, HashMap<String, City> cityList) {
        mInflater = LayoutInflater.from(context);
        this.mCityList = cityList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public CityListAdapter.CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.citylist_item,
                parent, false);
        return new CityViewHolder(mItemView, this);
    }

    /* The onBindViewHolder() method connects your data to the view holder. */
    @Override
    public void onBindViewHolder(@NonNull CityListAdapter.CityViewHolder holder, int position) {
        String mCurrent = "";
        int p = 0;
        for(String key : mCityList.keySet()){
            if(p == position){
                mCurrent = Objects.requireNonNull(mCityList.get(key)).getLocal();
                break;
            }
            p++;
        }
        holder.cityItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mCityList == null ? 0 : mCityList.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    class CityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView cityItemView;
        final CityListAdapter mAdapter;

        public CityViewHolder(@NonNull View itemView, CityListAdapter adapter) {
            super(itemView);
            cityItemView = itemView.findViewById(R.id.city);
            itemView.setOnClickListener(this);
            this.mAdapter = adapter;
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }

    public void setmCityList(HashMap<String, City> cityList){
        this.mCityList = cityList;
    }
}
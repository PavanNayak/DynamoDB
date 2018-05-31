package com.wristcode.dynamoexample.adapter;


        import android.content.Context;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;
        import com.wristcode.dynamoexample.R;
        import com.wristcode.dynamoexample.model.Datas;
        import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    public static final String mypreference = "mypref";
    public  String cart_id = "-1";
    public static final String product_id = "product_id";

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private List<Datas> userList;
    private Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txttitle, txttimestamp1, txttimestamp2;


        public MyViewHolder(View view) {
            super(view);


            txttitle = (TextView) view.findViewById(R.id.txttitle);
            txttimestamp1 = (TextView) view.findViewById(R.id.txttimestamp1);
            txttimestamp2 = (TextView) view.findViewById(R.id.txttimestamp2);


        }
    }


        public CustomAdapter(Context mContext, List<Datas> userList) {
            this.mContext = mContext;
            this.userList = userList;

        }

        @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);











        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Datas data = userList.get(position);
        holder.txttitle.setText(data.getTitle());
        holder.txttimestamp1.setText(data.getTimeStamp());
       // holder.txttimestamp2.setText(data.getReviewed());


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }







    public void updateList(List<Datas> list){
        userList = list;
        notifyDataSetChanged();
    }



}
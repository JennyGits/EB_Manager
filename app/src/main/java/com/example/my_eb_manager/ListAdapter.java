package com.example.my_eb_manager;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder> {

    //ListAdapter(ArrayList<NotiItem> list) { items = list; }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView titleText;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleText);

            // 클릭 이벤트 처리
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), MyAlarmActivity.class);
                    i.putExtra("position", getAdapterPosition());
                    v.getContext().startActivity(i);
                    //v.getContext().overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);  // 화면 전환 애니메이션

                    Log.e("Recyclerview", "position = "+ getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.e("Recyclerview", "position = "+ getAdapterPosition());
                    return false;
                }
            });
        }

        public void onBind(NotiItem notiItem) {
            titleText.setText(notiItem.getTitle());
        }
    }
    public ArrayList<NotiItem> items = new ArrayList<>();
    //ListAdapter(ArrayList<DateItem> list) { mData = list; }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayOutInflater를 이용해서 원하는 레이아웃을 띄워줍니다.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_noti, parent, false);    // 에러 이유: appcompat 버전과 recyclerView 버전이 같아야 함!
        return new ListAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // ItemViewHolder가 생성되고 넣어야 할 코드들을 넣어준다.
        // 보통 onBind 함수 안에 모두 넣어줍니다.
        holder.onBind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(NotiItem notiItem) {
        // items에 DateItem객체 추가
        items.add(notiItem);
        // 추가 후 Adapter에 데이터가 변경된 것을 알림
        notifyDataSetChanged();
    }
}

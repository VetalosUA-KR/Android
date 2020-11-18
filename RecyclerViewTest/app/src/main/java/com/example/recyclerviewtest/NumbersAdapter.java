package com.example.recyclerviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.NumberViewHolder> {

    private static int viewHolderCount;
    private int numberItems;
    private Context parent;

    public NumbersAdapter(int numberOfItems, Context parent) {
        this.numberItems = numberOfItems;
        viewHolderCount = 0;
        this.parent = parent;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.number_list_item;

        //LayoutInflater это класс который позволяет их XML файлов создавать новые представления, то есть переводим объекты xml в джава код
        LayoutInflater inflater = LayoutInflater.from(context);

        //Первый аргумент наш инфлейтер должен знать из какого .xml файла мы хотим создать новое представление
        //Воторой аргумент, указываем какой елемент будет являтся родительским для созданого нами элемента
        //Третий, указывает на то нужно ли помещать созданый елемент внутрь родителя
        View view = inflater.inflate(layoutIdForListItem, parent, false);

        NumberViewHolder viewHolder = new NumberViewHolder(view);
        viewHolder.viewHolderIndex.setText("ViewHolder index: "+viewHolderCount);
        viewHolderCount++;
        return  viewHolder;
    }

    //Вызывается при прокрутке списка, то-есть когда нужно обновить данные
    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

    //Это обертка для элемента списка
    class NumberViewHolder extends RecyclerView.ViewHolder {

        //Соответствует двум TexView которые находятся в number_list_item.xml
        TextView listItemNumberView;
        TextView viewHolderIndex;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            listItemNumberView = itemView.findViewById(R.id.tv_number_item);
            viewHolderIndex = itemView.findViewById(R.id.tv_view_holder_number);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int positionIndex = getAdapterPosition();
                    Toast.makeText(parent, "Element: "+positionIndex+" was clicked!", Toast.LENGTH_SHORT).show();
                }
            });
        }

        void bind(int listIndex) {
            listItemNumberView.setText(String.valueOf(listIndex));
        }
    }
}

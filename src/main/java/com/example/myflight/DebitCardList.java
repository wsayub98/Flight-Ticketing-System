package com.example.myflight;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DebitCardList extends ArrayAdapter<DebitCard> {

    private Activity context;
    List<DebitCard> debitcard;

    public DebitCardList(Activity context, List<DebitCard> debitcard) {
        super(context, R.layout.layout_debitcard_list, debitcard);
        this.context = context;
        this.debitcard = debitcard;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_debitcard_list, null, true);

        TextView textViewNumber = (TextView) listViewItem.findViewById(R.id.card_info);

        DebitCard debitcard_pos = debitcard.get(position);
        textViewNumber.setText(debitcard_pos.getCard_number());

        return listViewItem;
    }
}

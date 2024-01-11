package com.fadlan.api.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.fadlan.api.R
import com.fadlan.api.data.GempaItem
import com.fadlan.api.data.ModelListGempa

class Adapter(val data: ModelListGempa?, val context: Activity, val _g: List<GempaItem?>)
    : ArrayAdapter<GempaItem>(context, R.layout.custom_listview, _g){

    override fun getView(position:Int, convertView: View?, parent :ViewGroup): View {
        val inflater = context.layoutInflater
        val rowview = inflater.inflate(R.layout.custom_listview, null,true)

//        var _idx= rowview.findViewById<TextView>(R.id.list_nomor)
        var _tgl= rowview.findViewById<TextView>(R.id.list_tgl)
        var _koordinat= rowview.findViewById<TextView>(R.id.list_koordinat)
        var _wilayah= rowview.findViewById<TextView>(R.id.list_wilayah)

//        _idx.setText("#"+(position+1).toString())
        _tgl.setText(data?.infogempa?.gempa?.get(position)?.tanggal)
        _koordinat.setText(data?.infogempa?.gempa?.get(position)?.coordinates)
        _wilayah.setText(data?.infogempa?.gempa?.get(position)?.wilayah)

        return rowview
    }
}
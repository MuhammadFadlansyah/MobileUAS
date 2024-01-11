package com.fadlan.api

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class AdapterUser(private val context: Activity, private val userList: List<User>) :
    ArrayAdapter<User>(context, R.layout.activity_list_user, userList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var rowView = convertView
        val inflater = context.layoutInflater

        if (rowView == null) {
            rowView = inflater.inflate(R.layout.custom_listuser, null, true)
            val viewHolder = ViewHolder(
                rowView.findViewById(R.id.email),
                rowView.findViewById(R.id.username),
                rowView.findViewById(R.id.name),
                rowView.findViewById(R.id.password)
            )
            rowView.tag = viewHolder
        }

        val viewHolder = rowView?.tag as ViewHolder
        val user = userList[position]

        viewHolder.email.text = user.email
        viewHolder.username.text = user.username
        viewHolder.name.text = user.name
        viewHolder.password.text = user.password

        return rowView
    }

    private class ViewHolder(val email: TextView, val username: TextView, val name: TextView, val password: TextView)
}
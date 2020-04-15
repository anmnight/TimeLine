package com.anxiao.timeline.expand.view

import android.util.SparseArray
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class RVBaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val views = SparseArray<View>()

    protected fun <V : View> retrieveView(viewId: Int): V {
        var view = views.get(viewId)
        if (view == null) {
            view = itemView.findViewById(viewId)
            views.put(viewId, view)
        }
        return view as V
    }

}
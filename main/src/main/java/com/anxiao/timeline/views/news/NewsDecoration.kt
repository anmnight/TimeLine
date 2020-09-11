package com.anxiao.timeline.views.news

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class NewsDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
//        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = 150
        outRect.right = 150
        outRect.top = 60
        outRect.bottom = 60

    }
}
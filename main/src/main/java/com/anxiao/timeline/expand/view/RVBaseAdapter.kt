package com.anxiao.timeline.expand.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.RuntimeException

abstract class RVBaseAdapter<C : Cell> : RecyclerView.Adapter<RVBaseViewHolder>() {

    private val mCells: List<Cell> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVBaseViewHolder {
        for (index: Int in (0..itemCount)) {
            if (mCells[index].getItemType() == viewType) {
                return mCells[index].onCreateViewHolder(parent, viewType)
            }
        }
        throw RuntimeException("wrong viewType")
    }

    override fun onBindViewHolder(holder: RVBaseViewHolder, position: Int) {
        mCells[position].bindViewHolder(holder, position)
    }

    override fun getItemViewType(position: Int): Int {
        return mCells[position].getItemType()
    }

    override fun getItemCount(): Int {
        return mCells.size
    }

    override fun onViewAttachedToWindow(holder: RVBaseViewHolder) {
        super.onViewAttachedToWindow(holder)
    }
}
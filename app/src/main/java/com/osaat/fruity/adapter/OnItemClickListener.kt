package com.osaat.fruity.adapter

import android.view.View

interface OnItemClickListener<T> {

    fun onItemClick(view: View, selectedItem: T)
 }

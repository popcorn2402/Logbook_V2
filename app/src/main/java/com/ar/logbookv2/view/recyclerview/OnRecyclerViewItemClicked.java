package com.ar.logbookv2.view.recyclerview;

import java.sql.Date;
import java.time.LocalDate;

public interface OnRecyclerViewItemClicked
{
    void onShortClick(int position);

    void onLongClick(int position, LocalDate date);
}
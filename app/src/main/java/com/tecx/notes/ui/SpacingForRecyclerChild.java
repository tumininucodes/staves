package com.tecx.notes.ui;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpacingForRecyclerChild extends RecyclerView.ItemDecoration {

    public int halfSpace;

    public SpacingForRecyclerChild(int space) {
        this.halfSpace = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getPaddingTop() != halfSpace) {
            parent.setPadding(halfSpace, halfSpace, halfSpace, halfSpace);
            parent.setClipToPadding(false);
        }

        outRect.top = halfSpace;
        outRect.left = halfSpace;
        outRect.right = halfSpace;
        outRect.bottom = halfSpace;

    }
}

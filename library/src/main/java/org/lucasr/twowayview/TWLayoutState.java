/*
 * Copyright (C) 2014 Lucas Rocha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lucasr.twowayview;

import android.graphics.Rect;
import android.util.Log;

import org.lucasr.twowayview.TWView.Flow;

import org.lucasr.twowayview.TWView.Orientation;

class TWLayoutState {
    private boolean mIsVertical;
    private Rect[] mRects;

    public TWLayoutState(Orientation orientation, int count) {
        setOrientation(orientation);
        mRects = new Rect[count];
        for (int i = 0; i < count; i++) {
            mRects[i] = new Rect();
        }
    }

    public void setOrientation(Orientation orientation) {
        mIsVertical = (orientation == Orientation.VERTICAL);
    }

    public void offset(int offset) {
        for (int i = 0; i < mRects.length; i++) {
            mRects[i].offset(mIsVertical ? 0 : offset,
                    mIsVertical ? offset : 0);
        }
    }

    public int getCount() {
        return mRects.length;
    }

    public void get(int index, Rect dest) {
        dest.set(mRects[index]);
    }

    public void set(int index, Rect state) {
        set(index, state.left, state.top, state.right, state.bottom);
    }

    public void set(int index, int l, int t, int r, int b) {
        final Rect rect = mRects[index];
        rect.left = l;
        rect.top = t;
        rect.right = r;
        rect.bottom = b;
    }

    public void add(int index, Flow flow, int dimension) {
        final Rect rect = mRects[index];

        if (flow == Flow.FORWARD) {
            if (mIsVertical) {
                rect.bottom += dimension;
            } else {
                rect.right += dimension;
            }
        } else {
            if (mIsVertical) {
                rect.top -= dimension;
            } else {
                rect.left -= dimension;
            }
        }
    }

    public void remove(int index, Flow flow, int dimension) {
        final Rect rect = mRects[index];

        if (flow == Flow.FORWARD) {
            if (mIsVertical) {
                rect.top += dimension;
            } else {
                rect.left += dimension;
            }
        } else {
            if (mIsVertical) {
                rect.bottom -= dimension;
            } else {
                rect.right -= dimension;
            }
        }
    }

    public int getOuterStartEdge() {
        // TODO: make this a lot more performant/efficient
        int outerStart = Integer.MAX_VALUE;
        for (int i = 0; i < mRects.length; i++) {
            final Rect rect = mRects[i];
            outerStart = Math.min(outerStart, mIsVertical ? rect.top : rect.left);
        }

        return outerStart;
    }

    public int getInnerStartEdge() {
        int innerStart = Integer.MIN_VALUE;
        for (int i = 0; i < mRects.length; i++) {
            final Rect rect = mRects[i];
            innerStart = Math.max(innerStart, mIsVertical ? rect.top : rect.left);
        }

        return innerStart;
    }

    public int getInnerEndEdge() {
        int innerEnd = Integer.MAX_VALUE;
        for (int i = 0; i < mRects.length; i++) {
            final Rect rect = mRects[i];
            innerEnd = Math.min(innerEnd, mIsVertical ? rect.bottom : rect.right);
        }

        return innerEnd;
    }

    public int getOuterEndEdge() {
        int outerEnd = Integer.MIN_VALUE;
        for (int i = 0; i < mRects.length; i++) {
            final Rect rect = mRects[i];
            outerEnd = Math.max(outerEnd, mIsVertical ? rect.bottom : rect.right);
        }

        return outerEnd;
    }

    public boolean intersects(int start, int count, Rect r) {
        for (int i = start; i < start + count; i++) {
            if (Rect.intersects(mRects[i], r)) {
                return true;
            }
        }

        return false;
    }
}
package com.lky.toucheffectsmodule.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public interface TouchEffectsViewSubject {

    View createView(View parent, String name, Context context, AttributeSet attrs);

}

package com.lky.toucheffectsmodule.types;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.StringDef;

@Retention(RetentionPolicy.SOURCE)
@StringDef({TouchEffectsViewType.ALL,
        TouchEffectsViewType.TextView,
        TouchEffectsViewType.Button,
        TouchEffectsViewType.ImageView,
        TouchEffectsViewType.ImageButton,
        TouchEffectsViewType.FrameLayout,
        TouchEffectsViewType.LinearLayout,
        TouchEffectsViewType.RelativeLayout,
        TouchEffectsViewType.ConstraintLayout})
public @interface TouchEffectsViewType {

    String ALL = "ALL";
    String TextView = "TextView";
    String Button = "Button";
    String ImageView = "ImageView";
    String ImageButton = "ImageButton";
    String FrameLayout = "FrameLayout";
    String LinearLayout = "LinearLayout";
    String RelativeLayout = "RelativeLayout";
    String ConstraintLayout = "android.support.constraint.ConstraintLayout";




//    ALL("ALL"),
//    TextView("TextView"),
//    Button("Button"),
//    ImageView("ImageView"),
//    ImageButton("ImageButton"),
//    FrameLayout("FrameLayout"),
//    LinearLayout("LinearLayout"),
//    RelativeLayout("RelativeLayout"),
//    ConstraintLayout("android.support.constraint.ConstraintLayout");
//
//
//
//    private String mViewName;
//
//    public String getViewName() {
//        return mViewName;
//    }
//
//    TouchEffectsViewType(String viewName) {
//        this.mViewName = viewName;
//    }

}

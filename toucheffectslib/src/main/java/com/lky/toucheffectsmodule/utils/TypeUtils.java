package com.lky.toucheffectsmodule.utils;

import com.lky.toucheffectsmodule.types.TouchEffectsViewType;

import java.util.ArrayList;
import java.util.List;

public class TypeUtils {

    public static List<String> getAllViewTypes(){
        ArrayList<String> viewTypes = new ArrayList<>();
        viewTypes.add(TouchEffectsViewType.TextView);
        viewTypes.add(TouchEffectsViewType.Button);
        viewTypes.add(TouchEffectsViewType.ImageView);
        viewTypes.add(TouchEffectsViewType.ImageButton);
        viewTypes.add(TouchEffectsViewType.FrameLayout);
        viewTypes.add(TouchEffectsViewType.LinearLayout);
        viewTypes.add(TouchEffectsViewType.RelativeLayout);
        viewTypes.add(TouchEffectsViewType.ConstraintLayout);
        return viewTypes;
    }

}

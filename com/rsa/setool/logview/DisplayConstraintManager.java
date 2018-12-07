package com.rsa.setool.logview;

import java.awt.*;
import java.util.HashMap;

public class DisplayConstraintManager {
    static HashMap<String, GridBagConstraints> constraints = new HashMap<String, GridBagConstraints>();
    static{
        constraints.put("textField", new GridBagConstraints(0,2,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0),
                0, 0));

        constraints.put("chart", new GridBagConstraints(0,1,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0),
                0, 0));
        constraints.put("csvComponents", new GridBagConstraints(1,0,1,1,0,0,
                GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),
                0, 0));
        constraints.put("userLookupFields", new GridBagConstraints(1,3,1,1,0,0,
                GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,20,0),
                0, 0));
        constraints.put("noticeArea", new GridBagConstraints(1,1,1,1,0,0,
                GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0),
                0, 0));


    }

    public static GridBagConstraints getConstraint(String componentName){
        return constraints.get(componentName);

    }
}

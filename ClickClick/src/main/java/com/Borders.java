package com;

import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;

public class Borders {
    
    public static BorderStroke[] strokes = new BorderStroke[]{

        // Default [0]
        new BorderStroke(null, null, null, null),

        // Red [1]
        new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(3)),

        // Red V2 [2]
        new BorderStroke(Color.web("#EB1C01"), BorderStrokeStyle.SOLID, null, new BorderWidths(2)),

        // Blue [3]
        new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(3)),

        // Green 4]
        new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, null, new BorderWidths(2)),

        // Yellow [5]
        new BorderStroke(Color.web("#cd9507"), BorderStrokeStyle.SOLID, null, new BorderWidths(2)),

    };

}

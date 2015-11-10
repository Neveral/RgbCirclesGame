package com.neveral.rgbcircles;

/**
 * Created by Neveral on 09.11.15.
 */
public interface ICanvasView {
    void drawCircle(SimpleCircle circle);

    void redraw();

    void showMessage(String text);
}

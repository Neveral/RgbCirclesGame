package com.neveral.rgbcircles;

import java.util.ArrayList;

/**
 * Created by Neveral on 09.11.15.
 */
public class GameManager {

    public static final int NUM_OF_ENEMY_CIRCLES = 10;
    private MainCircle mainCircle;
    private ArrayList<EnemyCircle> enemyCircles;

    private CanvasView canvasView;
    private static int width;
    private static int height;

    public GameManager(CanvasView canvasView, int w, int h) {
        this.canvasView = canvasView;
        width = w;
        height = h;
        initMainCircle();
        initEnemyCircles();
    }

    private void initMainCircle() {
        mainCircle = new MainCircle(width/2, height/2);
    }

    private void initEnemyCircles() {
        enemyCircles = new ArrayList<EnemyCircle>();
        SimpleCircle mainCircleArea = mainCircle.getCircleArea();
        for (int i = 0; i < NUM_OF_ENEMY_CIRCLES; i++) {
            EnemyCircle enemyCircle;
            do {
                enemyCircle = EnemyCircle.getRandomCircle();
            }while(enemyCircle.isIntersect(mainCircle));
            enemyCircles.add(enemyCircle);
        }
        calculateAndSetCirclesColor();
    }

    private void calculateAndSetCirclesColor() {
        for(EnemyCircle circle: enemyCircles) {
            circle.setEnemyOrFoodColorDependsOn(mainCircle);
        }
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public void onDrow() {
        canvasView.drawCircle(mainCircle);
        for (EnemyCircle es: enemyCircles) {
            canvasView.drawCircle(es);
        }
    }

    public void onTouchEvent(int x, int y) {
        mainCircle.moveMainCircleWhenTouchAt(x, y);
        checkCollision();
        moveCircles();
    }

    private void checkCollision() {
        SimpleCircle circleForDel = null;
        for (EnemyCircle es : enemyCircles) {
            if(mainCircle.isIntersect(es)){
                if(es.isSmallerThan(mainCircle)){
                    mainCircle.growRadius(es);
                    circleForDel = es;
                    calculateAndSetCirclesColor();
                    break;
                }else{
                    gameEnd("YOU LOSE!");
                }
            }
        }
        if(circleForDel != null) {
            enemyCircles.remove(circleForDel);
        }
        if(enemyCircles.isEmpty()){
            gameEnd("YOU WIN!");
        }
    }

    private void gameEnd(String text) {
        canvasView.showMessage(text);
        mainCircle.initRadius();
        initEnemyCircles();
        canvasView.redraw();
    }

    private void moveCircles() {
        for (EnemyCircle es: enemyCircles){
            es.moveOneStep();
        }
    }
}

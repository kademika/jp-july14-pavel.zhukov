package com.kademika.day12.f2to3ball;

public class ThreadForBall extends Thread {
    private Ball ball;
    private int fieldWidth;
    private int fieldHeight;
    private boolean flagBall = true;

    public ThreadForBall(Ball ball, int fieldWidth, int fieldHeight) {
        this.ball = ball;
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
    }

    @Override
    public void run() {
        System.out.println("Running " + Thread.currentThread().getName() + " Ball №" + ball.getNum());
        while (true) {
            if (flagBall) {
                ball.step(-1, 0);
                if (ball.getX() == 0) {
                    flagBall = false;
                }
            } else {
                ball.step(1, 0);
                if (ball.getX() == fieldWidth - ball.getWidth() - 5) {
                    flagBall = true;
                }
            }

            try {
                Thread.sleep(ball.getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

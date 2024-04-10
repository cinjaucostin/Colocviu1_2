package ro.pub.cs.systems.eim.Colocviu1_2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.time.LocalTime;

public class ProcessThread extends Thread {

    private boolean isRunning = true;
    private int sum;
    private Context context;

    public ProcessThread(Context context, int sum) {
        this.context = context;
        this.sum = sum;
    }

    @Override
    public void run() {
        Log.d("ProcessThread", "processing method has started");
        while (isRunning) {
            sleep();
            sendMessage();
        }
        Log.d("ProcessThread", "processing method ended");
    }

    private void sleep() {
        try {
            Thread.sleep(Constants.SLEEP_TIME);
        } catch(InterruptedException interruptedException) {

        }
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.SUM_ACTION);
        String broadcastMessage = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            broadcastMessage = "Time: " + LocalTime.now() + ", sum = " + sum;
        } else {
            broadcastMessage = "sum = " + sum;
        }

        Log.d("PracticalTest01Service - ProcessingThread", "Message produced: " + broadcastMessage);

        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA, broadcastMessage);
        context.sendBroadcast(intent);
    }

    public void stopThread() {
        isRunning = false;
    }
}

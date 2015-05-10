package pe.area51.threadsapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    AlertDialog myProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myProgressDialog = new ProgressDialog(MainActivity.this);
        myProgressDialog.setTitle(R.string.progress_title);
        myProgressDialog.setMessage(getString(R.string.progress_message));
        myProgressDialog.setCancelable(false);

        findViewById(R.id.activity_main_button_main_thread).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeOnMainThread();
            }
        });
        findViewById(R.id.activity_main_button_worker_thread).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeOnWorkerThread();
            }
        });
        findViewById(R.id.activity_main_button_asynctask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeWithAsyncTask();
            }
        });
    }

    private void longTask() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void executeOnMainThread() {
        myProgressDialog.show();
        longTask();
        myProgressDialog.dismiss();
    }

    private void executeOnWorkerThread() {
        myProgressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                longTask();
                myProgressDialog.dismiss();
            }
        }).start();
    }

    private void executeWithAsyncTask() {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected void onPreExecute() {
                myProgressDialog.show();
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                longTask();
                return null;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                myProgressDialog.dismiss();
            }
        }.execute();
    }

}

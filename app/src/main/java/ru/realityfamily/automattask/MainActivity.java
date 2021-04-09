package ru.realityfamily.automattask;

import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import ru.realityfamily.automattask.Models.Automat;
import ru.realityfamily.automattask.Models.Student;

public class MainActivity extends FragmentActivity {
    FrameLayout container1;
    FrameLayout container2;
    FrameLayout container3;
    FrameLayout container4;
    private static MainActivity instance;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    AutomatFragments fragment1 = AutomatFragments.newInstance();
    AutomatFragments fragment2 = AutomatFragments.newInstance();
    AutomatFragments fragment3 = AutomatFragments.newInstance();
    AutomatFragments fragment4 = AutomatFragments.newInstance();
    List<Automat> automatList = new ArrayList<>();
    List<Student> studentList = new ArrayList<>();
    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch(msg.getData().getInt("name")){
            case 1:
                if (msg.getData().getString("status").equals(Automat.AutomatStatus.Waiting.toString())) {
                    fragment1.setAutoStatus(msg.getData().getString("status"));
                    fragment1.setClientId("");
                    fragment1.setAutoCart("");
                    fragment1.setAutoQueue1("");
                    fragment1.setAutoQueue2(CalculateQueue(1));
                } else {
                    fragment1.setAutoStatus(msg.getData().getString("status"));
                    fragment1.setClientId(msg.getData().getString("client"));
                    fragment1.setAutoCart(msg.getData().getString("cart"));
                    fragment1.setAutoQueue1(msg.getData().getString("queue1"));
                    fragment1.setAutoQueue2(CalculateQueue(1));
                }
                break;
            case 2:
                if (msg.getData().getString("status").equals(Automat.AutomatStatus.Waiting.toString())) {
                    fragment2.setAutoStatus(msg.getData().getString("status"));
                    fragment2.setClientId("");
                    fragment2.setAutoCart("");
                    fragment2.setAutoQueue1("");
                    fragment2.setAutoQueue2(CalculateQueue(2));
                } else {
                    fragment2.setAutoStatus(msg.getData().getString("status"));
                    fragment2.setClientId(msg.getData().getString("client"));
                    fragment2.setAutoCart(msg.getData().getString("cart"));
                    fragment2.setAutoQueue1(msg.getData().getString("queue1"));
                    fragment2.setAutoQueue2(CalculateQueue(2));
                }
                break;
            case 3:
                if (msg.getData().getString("status").equals(Automat.AutomatStatus.Waiting.toString())) {
                    fragment3.setAutoStatus(msg.getData().getString("status"));
                    fragment3.setClientId("");
                    fragment3.setAutoCart("");
                    fragment3.setAutoQueue1("");
                    fragment3.setAutoQueue2(CalculateQueue(3));
                } else {
                    fragment3.setAutoStatus(msg.getData().getString("status"));
                    fragment3.setClientId(msg.getData().getString("client"));
                    fragment3.setAutoCart(msg.getData().getString("cart"));
                    fragment3.setAutoQueue1(msg.getData().getString("queue1"));
                    fragment3.setAutoQueue2(CalculateQueue(3));
                }
                break;
            case 4:
                if (msg.getData().getString("status").equals(Automat.AutomatStatus.Waiting.toString())) {
                    fragment4.setAutoStatus(msg.getData().getString("status"));
                    fragment4.setClientId("");
                    fragment4.setAutoCart("");
                    fragment4.setAutoQueue1("");
                    fragment4.setAutoQueue2(CalculateQueue(4));
                } else {
                    fragment4.setAutoStatus(msg.getData().getString("status"));
                    fragment4.setClientId(msg.getData().getString("client"));
                    fragment4.setAutoCart(msg.getData().getString("cart"));
                    fragment4.setAutoQueue1(msg.getData().getString("queue1"));
                    fragment4.setAutoQueue2(CalculateQueue(4));
                }
                break;
        }
            }
        };

    /*FrameLayout container1;
    TextView autoName1;
    TextView autoName2;
    TextView autoName3;
    TextView autoName4;
    Button button1;
    TextView autoStatus1;
    TextView autoStatus2;
    TextView autoStatus3;
    TextView autoStatus4;

    TextView clientId1;
    TextView clientId2;
    TextView clientId3;
    TextView clientId4;

    TextView autoCart1;
    TextView autoCart2;
    TextView autoCart3;
    TextView autoCart4;

    TextView autoQueue11;
    TextView autoQueue12;

    TextView autoQueue21;
    TextView autoQueue22;

    TextView autoQueue31;
    TextView autoQueue32;

    TextView autoQueue41;
    TextView autoQueue42;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instance = this;
        container1=findViewById(R.id.container1);
        container2=findViewById(R.id.container2);
        container3=findViewById(R.id.container3);
        container4=findViewById(R.id.container4);
        /*autoCart1 = findViewById(R.id.autoCart1);
        autoCart2 = findViewById(R.id.autoCart2);
        autoCart3 = findViewById(R.id.autoCart3);
        autoCart4 = findViewById(R.id.autoCart4);

        autoName1 = findViewById(R.id.autoName1);
        autoName2 = findViewById(R.id.autoName2);
        autoName3 = findViewById(R.id.autoName3);
        autoName4 = findViewById(R.id.autoName4);

        autoStatus1 = findViewById(R.id.autoStatus1);
        autoStatus2 = findViewById(R.id.autoStatus2);
        autoStatus3 = findViewById(R.id.autoStatus3);
        autoStatus4 = findViewById(R.id.autoStatus4);

        clientId1 = findViewById(R.id.clientId1);
        clientId2 = findViewById(R.id.clientId2);
        clientId3 = findViewById(R.id.clientId3);
        clientId4 = findViewById(R.id.clientId4);
        container1 = findViewById(R.id.container1);
        autoQueue11 = findViewById(R.id.autoQueue11);
        autoQueue12 = findViewById(R.id.autoQueue12);

        autoQueue21 = findViewById(R.id.autoQueue21);
        autoQueue22 = findViewById(R.id.autoQueue22);

        autoQueue31 = findViewById(R.id.autoQueue31);
        autoQueue32 = findViewById(R.id.autoQueue32);

        autoQueue41 = findViewById(R.id.autoQueue41);
        autoQueue42 = findViewById(R.id.autoQueue42);*/
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container1,fragment1);
        transaction.add(R.id.container2,fragment2);
        transaction.add(R.id.container3,fragment3);
        transaction.add(R.id.container4,fragment4);
        transaction.commit();
        container1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    fragmentManager.beginTransaction().remove(fragment1).commit();
                    fragmentManager.executePendingTransactions();
                    fragmentManager.beginTransaction().replace(R.id.maincontainer,fragment1).commit();


                }
                return true;
            }
        });
        container2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    fragmentManager.beginTransaction().remove(fragment2).commit();
                    fragmentManager.executePendingTransactions();
                    fragmentManager.beginTransaction().replace(R.id.maincontainer,fragment2).commit();
                }
                return true;
            }
        });
        container3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    fragmentManager.beginTransaction().remove(fragment3).commit();
                    fragmentManager.executePendingTransactions();
                    fragmentManager.beginTransaction().replace(R.id.maincontainer,fragment3).commit();

                }
                return true;
            }
        });
        container4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    fragmentManager.beginTransaction().remove(fragment4).commit();
                    fragmentManager.executePendingTransactions();
                    fragmentManager.beginTransaction().replace(R.id.maincontainer,fragment4).commit();
                }
                return true;
            }
        });
      // fragment1.setAutoName(Integer.toString(1));
        //fragment2.setAutoName(Integer.toString(2));
        //fragment3.setAutoName(Integer.toString(3));
       // fragment4.setAutoName(Integer.toString(4));


        for (int i = 1; i < 5; i++) {
            automatList.add(
                    new Automat(i)
            );
        }

        for (int i = 1; i < 21; i++) {
            studentList.add(
                    new Student(
                            i,
                            new Random().nextInt(5) + 3,
                            automatList.get(new Random().nextInt(automatList.size()))
                    )
            );
        }

        for(Student student : studentList) { //StartThread,StartTask

            student.createThread(handler);
            student.StartThread();
            //student.StartTask(); //  TODO: run треда
        }
    }
    //TODO
   /*public void UpdateData(Automat automat, Student student) {
        switch (automat.getName()){
            case 1:
                if (automat.getStatus() == Automat.AutomatStatus.Waiting) {
                    fragment1.setAutoStatus(automat.getStatus().toString());
                    fragment1.setClientId("");
                    fragment1.setAutoCart("");
                    fragment1.setAutoQueue1("");
                    fragment1.setAutoQueue2(CalculateQueue(1));
                } else {
                    fragment1.setAutoStatus(automat.getStatus().toString());
                    fragment1.setClientId(student.getName());
                    fragment1.setAutoCart(student.getCart().toString());
                    fragment1.setAutoQueue1("= " + student.CartCost() + " у.е.");
                    fragment1.setAutoQueue2(CalculateQueue(1));
                }
                break;
            case 2:
                if (automat.getStatus() == Automat.AutomatStatus.Waiting) {
                    fragment2.setAutoStatus(automat.getStatus().toString());
                    fragment2.setClientId("");
                    fragment2.setAutoCart("");
                    fragment2.setAutoQueue1("");
                    fragment2.setAutoQueue2(CalculateQueue(1));
                } else {
                    fragment2.setAutoStatus(automat.getStatus().toString());
                    fragment2.setClientId(student.getName());
                    fragment2.setAutoCart(student.getCart().toString());
                    fragment2.setAutoQueue1("= " + student.CartCost() + " у.е.");
                    fragment2.setAutoQueue2(CalculateQueue(2));
                }
                break;
            case 3:
                if (automat.getStatus() == Automat.AutomatStatus.Waiting) {
                    fragment3.setAutoStatus(automat.getStatus().toString());
                    fragment3.setClientId("");
                    fragment3.setAutoCart("");
                    fragment3.setAutoQueue1("");
                    fragment3.setAutoQueue2(CalculateQueue(3));
                } else {
                    fragment3.setAutoStatus(automat.getStatus().toString());
                    fragment3.setClientId(student.getName());
                    fragment3.setAutoCart(student.getCart().toString());
                    fragment3.setAutoQueue1("= " + student.CartCost() + " у.е.");
                    fragment3.setAutoQueue2(CalculateQueue(3));
                    break;
                }
            case 4:
                if (automat.getStatus() == Automat.AutomatStatus.Waiting) {
                    fragment4.setAutoStatus(automat.getStatus().toString());
                    fragment4.setClientId("");
                    fragment4.setAutoCart("");
                    fragment4.setAutoQueue1("");
                    fragment4.setAutoQueue2(CalculateQueue(4));
                } else {
                    fragment4.setAutoStatus(automat.getStatus().toString());
                    fragment4.setClientId(student.getName());
                    fragment4.setAutoCart(student.getCart().toString());
                    fragment4.setAutoQueue1("= " + student.CartCost() + " у.е.");
                    fragment4.setAutoQueue2(CalculateQueue(4));
                }
                break;
        }
    }*/
  //TODO
    /*public String CalculateQueue(int automatName) {
        int queue = 0;
        for (Student student : studentList) {
            if (student.getAutomatName() == automatName && student.getTaskStatus() == AsyncTask.Status.RUNNING) queue++;
        }
        return queue - 1 > 0 ? queue - 1 + " человек" : "Людей больше нет.";
    }*/

    public static MainActivity getInstance() {
        return instance;
    }
    public String CalculateQueue(int automatName) {
        int queue = 0;
        for (Student student : studentList) {
            if (student.getAutomatName() == automatName && student.getStudentThread().isAlive()) queue++;
        }
        return queue - 1 > 0 ? queue - 1 + " человек" : "Людей больше нет.";
    }








    public void onBackPressed(){
        fragmentManager.beginTransaction().remove(fragment1).commit();
        fragmentManager.beginTransaction().remove(fragment2).commit();
        fragmentManager.beginTransaction().remove(fragment3).commit();
        fragmentManager.beginTransaction().remove(fragment4).commit();
        fragmentManager.executePendingTransactions();
        fragmentManager.beginTransaction().add(R.id.container1,fragment1).commit();
        fragmentManager.beginTransaction().add(R.id.container2,fragment2).commit();
        fragmentManager.beginTransaction().add(R.id.container3,fragment3).commit();
        fragmentManager.beginTransaction().add(R.id.container4,fragment4).commit();

    }

}
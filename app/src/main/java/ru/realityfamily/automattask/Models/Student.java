package ru.realityfamily.automattask.Models;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ru.realityfamily.automattask.MainActivity;

public class Student {
    private final String name;
    private List<IProduct> cart = new ArrayList<>();
    private final int cartCount;
    private final Automat automat;
    private StudentThread thread;
    //TODO
    //private StudentTask task;

    public Student(int name, int cartCount, Automat automat) {
        this.name = "Студент №" + name;
        this.cartCount = cartCount;
        this.automat = automat;
        //TODO
        //this.task = new StudentTask(automat, this);
    }
    public void createThread(Handler handler){
        thread = new StudentThread(automat,this,handler);
    }
    public void ChooseProduct() throws InterruptedException {
        while (cart.size() < cartCount) {
            IProduct product = automat.GetProduct();
            if (product != null) {
                TimeUnit.SECONDS.sleep(1);
                cart.add(product);
            }
        }
    }

    public double CartCost() {
        double finalCost = 0;
        for (IProduct product : cart) {
            finalCost += product.getCost();
        }
        return finalCost;
    }

    public void PayForCart() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
    }
    //TODO
    /*public void StartTask() {
        if (task == null) return;
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }*/
   public void StartThread() {
        if (thread == null) return;
        thread.start();
    }
    public String getName() {
        return name;
    }

    public List<IProduct> getCart() {
        return cart;
    }

    public int getAutomatName() {
        return automat.getName();
    }
    class StudentThread extends Thread{
        final Automat automat;
        final Student student;
        private Handler handler;

        StudentThread(Automat automat, Student student,Handler handler) {
            this.automat=automat;
            this.student=student;
            this.handler=handler;

        }
        public void run(){
            synchronized (automat){
                publishProgress(automat,student);
                automat.setStatus(Automat.AutomatStatus.Client_Choosing);
                publishProgress(automat,student);
                try {
                    student.ChooseProduct();
                    publishProgress(automat,student);
                    automat.setStatus(Automat.AutomatStatus.Client_Paying);
                    publishProgress(automat,student);
                    student.PayForCart();
                    automat.setStatus(Automat.AutomatStatus.Giving_Products);
                    publishProgress(automat,student);
                    automat.GiveProducts();
                    automat.setStatus(Automat.AutomatStatus.Waiting);
                    publishProgress(automat,student);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
        public void publishProgress(Automat automat, Student student){
            Bundle bundle = new Bundle();
            bundle.putSerializable("name", automat.getName());
            bundle.putSerializable("status", automat.getStatus().toString());
            bundle.putSerializable("client", student.getName());
            bundle.putSerializable("cart", student.getCart().toString());
            bundle.putSerializable("queue1", "= " + student.CartCost() + " у.е.");


            Message msg = new Message();
            msg.setData(bundle);
            if (msg!=null){
            handler.sendMessage(msg);}
            }
        }
   public Thread getStudentThread() {return this.thread;}
   //TODO
    /*public AsyncTask.Status getTaskStatus() {
        return task.getStatus();
    }
//TODO
    class StudentTask extends AsyncTask<Void, Void, Void> {
        final Automat automat;
        final Student student;

        public StudentTask(Automat automat, Student student) {
            this.automat = automat;
            this.student = student;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            synchronized (automat) {
                publishProgress();
                automat.setStatus(Automat.AutomatStatus.Client_Choosing);
                publishProgress();
                try {
                    student.ChooseProduct();
                    publishProgress();
                    automat.setStatus(Automat.AutomatStatus.Client_Paying);
                    publishProgress();
                    student.PayForCart();
                    automat.setStatus(Automat.AutomatStatus.Giving_Products);
                    publishProgress();
                    automat.GiveProducts();
                    automat.setStatus(Automat.AutomatStatus.Waiting);
                    publishProgress();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            MainActivity.getInstance().UpdateData(automat, student);
        }
    }*/
}

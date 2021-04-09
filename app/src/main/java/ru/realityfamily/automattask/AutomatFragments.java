package ru.realityfamily.automattask;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AutomatFragments extends android.app.Fragment {
    private TextView autoName;
    private TextView autoStatus;
    private TextView clientId;
    private TextView autoCart;
    private TextView autoQueue1;
    private TextView autoQueue2;
    public AutomatFragments(){

    }
    public static AutomatFragments newInstance(){
        AutomatFragments fragment = new AutomatFragments();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment,container,false);
        autoName = view.findViewById(R.id.autoName);
        autoStatus=view.findViewById(R.id.autoStatus);
        clientId=view.findViewById(R.id.clientId);
        autoCart=view.findViewById(R.id.autoCart);
        autoQueue1=view.findViewById(R.id.autoQueue1);
        autoQueue2=view.findViewById(R.id.autoQueue2);

        return view;
    }
    public void setAutoName(String name){
        autoName.setText(name);
    }
    public void setAutoQueue1(String queue1){
        autoQueue1.setText(queue1);
    }
    public void setAutoQueue2(String queue2){
        autoQueue2.setText(queue2);
    }
    public void setAutoCart(String cart){
        autoCart.setText(cart);
    }
    public void setAutoStatus(String status){
        autoStatus.setText(status);
    }
    public void setClientId(String id){
        clientId.setText(id);
    }

}

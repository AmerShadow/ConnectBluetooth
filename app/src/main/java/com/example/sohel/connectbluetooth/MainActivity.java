package com.example.sohel.connectbluetooth;

import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Button on,off,discover,listDevices;
    String TAG=MainActivity.class.getSimpleName();
    private BluetoothAdapter ba;
    ListAdapter listAdapter;
    Set<BluetoothDevice> pairedDevices;
    Intent turnOn,discoverIntent;
    ListView showDevices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setIds();
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turnOn();
            }
        });
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turnoff();
            }
        });
        discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visible();
            }
        });
        listDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listBluetooth();
            }
        });




    }
    private void setIds(){
        on=findViewById(R.id.on);
        off=findViewById(R.id.off);
        discover=findViewById(R.id.visible);
        listDevices=findViewById(R.id.listDevice);
        showDevices=findViewById(R.id.bluetoothList);
        ba=BluetoothAdapter.getDefaultAdapter();

    }
    private void turnOn(){
        turnOn=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(turnOn,0);
        Toast.makeText(getApplicationContext(), "Turned on",Toast.LENGTH_LONG).show();
    }
    private void turnoff(){
        Log.d(TAG,"reaching in turnoff");
        ba.disable();
        Toast.makeText(getApplicationContext(),"Disconnected",Toast.LENGTH_LONG).show();


    }
    private void visible() {
        Log.d(TAG,"reaching in visible");
        discoverIntent=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(discoverIntent,0);

    }
    private void listBluetooth(){
        ba.startDiscovery();
        pairedDevices=ba.getBondedDevices();
        ArrayList list=new ArrayList();
        for(BluetoothDevice bt:pairedDevices){
            list.add(bt.getName());
        }
        ArrayAdapter adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,list);
        showDevices.setAdapter(adapter);
        /*final BroadcastReceiver broadcastReceiver=new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                String action=intent.getAction();
                if(BluetoothDevice.ACTION_FOUND.equals(action)){
                    BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                }
            }
        }; */
    }


}

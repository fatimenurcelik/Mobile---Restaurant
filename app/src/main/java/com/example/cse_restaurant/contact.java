package com.example.cse_restaurant;

import android.content.Intent;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.Task;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class contact extends Fragment {

    Typeface tf1;
    Typeface tf2;
    private GoogleMap googleMap;

    public contact(){

    }

/*
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);

if (googleMap == null) {
googleMap = ((MapFragment) getFragmentManager().findFragmentById(
R.id.map)).getMap();
if (googleMap == null) {
Toast.makeText(getApplicationContext(),
“Üzgünüm, map oluşturualamadı”, Toast.LENGTH_SHORT)
.show();
}
googleMap.setMyLocationEnabled(true);
googleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
@Override
public void onMyLocationChange(Location location) {
double latitude = location.getLatitude();
double longitude = location.getLongitude();
LatLng latLng = new LatLng(latitude, longitude);
googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
googleMap.setOnMyLocationChangeListener(null);
}
});
}}
 */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_fragment, container, false);

        tf1=Typeface.createFromAsset(view.getContext().getAssets(), "fonts/Lob.ttf");
        TextView textView1=(TextView)view.findViewById(R.id.Rezer);
        textView1.setTypeface(tf1);

        tf2=Typeface.createFromAsset(view.getContext().getAssets(), "fonts/sa.ttf");
        TextView textView2=(TextView)view.findViewById(R.id.num);
        textView2.setTypeface(tf2);

        TextView konum = (TextView)view.findViewById(R.id.konum);
//        harita(view,konum);

/*
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            if (googleMap == null) {
                Toast.makeText(contact.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                //  Toast.makeText(getApplicationContext(),"Üzgünüm, map oluşturulamadı”, Toast.LENGTH_SHORT).show();
            }
            googleMap.setMyLocationEnabled(true);
            googleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LatLng latLng = new LatLng(latitude, longitude);
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
                    googleMap.setOnMyLocationChangeListener(null);
                }
            });
        }
*/
        return view;
    }

   /* public void harita(View v,TextView tv) {

        switch(v.getId()) {
            case 1:
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("tıklandı");
                    Intent mapIntent =new Intent(contact.this.getActivity(),MapsActivity.class);
                    System.out.println("tıklandı");
                }
            });

        }
    }*/
}

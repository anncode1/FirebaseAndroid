package com.anncode.firebasecursoplatzi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.anncode.firebasecursoplatzi.model.Artist;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String ARTIST_NODE =  "Artists";
    private static final String TAG = "MainActivity" ;
    private DatabaseReference databaseReference;

    private ListView lstArtist;
    private ArrayAdapter arrayAdapter;
    private List<String> artistNames;
    private List<Artist> artists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstArtist = (ListView) findViewById(R.id.lstArtist);
        artistNames = new ArrayList<>();
        artists = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, artistNames);
        lstArtist.setAdapter(arrayAdapter);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        databaseReference = FirebaseDatabase.getInstance().getReference(); //android-examples-2a122

        databaseReference.child(ARTIST_NODE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                artistNames.clear();
                artists.clear();
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                        Artist artist = snapshot.getValue(Artist.class);
                        Log.w(TAG, "Artist Name: " + artist.getName());
                        artistNames.add(artist.getName());
                        artists.add(artist);
                    }
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        lstArtist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                String idArtist = artists.get(position).getId();
                artists.remove(position);
                artistNames.remove(position);
                databaseReference.child(ARTIST_NODE).child(idArtist).removeValue();
                return true;
            }
        });

    }

    public void createArtist(View view){
        Artist artist = new Artist(databaseReference.push().getKey(),"Garbage", "Rock");
        databaseReference.child(ARTIST_NODE).child(artist.getId()).setValue(artist);
    }


}

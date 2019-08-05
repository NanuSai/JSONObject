package com.saiproject.jsonp1;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.saiproject.jsonp1.Model.iTunesStuff;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtArtistName;
    TextView txtType;
    TextView txtKind;
    TextView txtCollectionName;
    TextView txtTrackName;
    ImageView imgArt;
    String imgURL;
    Button btnGetData;
    TextView txtDownload;
    Bitmap bm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        txtArtistName = findViewById(R.id.txtArtistName);
        txtType = findViewById(R.id.txtType);
        txtKind = findViewById(R.id.txtKind);
        txtCollectionName = findViewById(R.id.txtCollectionName);
        txtTrackName = findViewById(R.id.txtTrackName);
        imgArt = findViewById(R.id.imgArt);
        btnGetData = findViewById(R.id.btnGetData);





        btnGetData.setOnClickListener(this);




    }

    /* This downloads from internet  in the background, hence async class is used*/
    // String -> input ,  ItunesStuff ->Output , void -> ProgressData

    private class JSONItunesStuffTask extends AsyncTask<String, Void, iTunesStuff>{

        Context context;
        ProgressDialog progressDialog;

        public JSONItunesStuffTask(Context context) {
            this.context = context;
            progressDialog = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setTitle("Downloading info from Itunes....Please Wait");
            progressDialog.show();
        }

        /* The return type is iTunesStuff because AsyncTask output is iTunesStuff */

        @Override
        protected iTunesStuff doInBackground(String... strings) {

            iTunesHTTPClient itunesHTTPClient =  new iTunesHTTPClient();
            iTunesStuff iTunesStuff = new iTunesStuff();
            String data = itunesHTTPClient.getITunesStuffData();  //Todo: Can't connect to apple services, use wifi and come back

            try{

                iTunesStuff = JsonItunesParser.getItunesStuff(data);        //From the string format, converted to iTunesStuffObject
                imgURL = iTunesStuff.getArtistViewURL();
                bm = itunesHTTPClient.getBitmapFromURL(imgURL);

                }

            catch (Throwable t) {   //Incase internet doesn't connects
                t.printStackTrace();
            }

            return iTunesStuff;

            }

    //Just after execution, the argument is the output of iTunesStuff
        @Override
        protected void onPostExecute(iTunesStuff iTunesStuff) {
            super.onPostExecute(iTunesStuff);



            txtArtistName.setText(iTunesStuff.getArtistName());
            txtType.setText(iTunesStuff.getType());
            txtKind.setText(iTunesStuff.getKind());
            txtCollectionName.setText(iTunesStuff.getCollectionName());
            txtTrackName.setText(iTunesStuff.getTrackName());
            imgArt.setImageBitmap(bm);

            if(progressDialog.isShowing())
                progressDialog.dismiss();

            if(txtKind.getText().length() <= 0){
                Log.i("log","TEXT KIND IS NULL");
            }


            if(txtTrackName.getText().length() <= 0){
                Log.i("LOG","txtTrackName is null");
            }






        }
    }


    @Override
    public void onClick(View v) {

        JSONItunesStuffTask jsonItunesStuffTask = new JSONItunesStuffTask(this);
        jsonItunesStuffTask.execute();
    }
}

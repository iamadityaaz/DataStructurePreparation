package kvadityaaz.forhitesh.com.datastructurepreparation;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kvadityaaz.forhitesh.com.datastructurepreparation.adapter.CardItemString;
import kvadityaaz.forhitesh.com.datastructurepreparation.adapter.CardPagerAdapterS;
import kvadityaaz.forhitesh.com.datastructurepreparation.utils.ShadowTransformer;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    private CardPagerAdapterS mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_data);

        String myURL = "http://api.openweathermap.org/data/2.5/weather?q=" + "patna" + "&appid=35649579c1d1403a49d25504c76b92c7";


        Log.i("Tap", "Tapped" + myURL);

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("JSON", "json" + response);


                        try {
                            String weather = response.getString("weather");

                            JSONArray ar = new JSONArray(weather);

                            for (int i = 0; i < ar.length(); i++) {
                                JSONObject jr = ar.getJSONObject(i);

                                String st = jr.getString("main");

                                Log.i("main", "st" + st);

                                String yo = "yoyoyoyoyo";

                                mCardAdapter.addCardItemS(new CardItemString( st,yo ));


//                                result.setText(st);
                            }


//                                    result.setText(weather);
//
//                                    Log.i("coor", "coordinates" + weather);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Error", "error" + error);
                    }
                }

        );

        MySingleton.getInstance(MainActivity.this).addToRequestQueue(jsonObjectRequest);

        context = this;

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);


        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mCardAdapter = new CardPagerAdapterS();


    }
}
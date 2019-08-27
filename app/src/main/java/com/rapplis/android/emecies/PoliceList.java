package com.rapplis.android.emecies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PoliceList extends AppCompatActivity {
    private static String title;
    private static int icon;
    private static int cover;
    private static String phone;
    private static double lat;
    private static double lon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        final ArrayList<List> lists = new ArrayList<>();
        lists.add(new List("আদাবর থানা",R.drawable.police_logo, R.drawable.adabor_cover,
                "02-9133265", 23.770912, 90.359243));
        lists.add(new List("উত্তরা পূর্ব থানা",R.drawable.police_logo, R.drawable.uttara_e_cover,
                "01199883740", 23.866911, 90.400657));
        lists.add(new List("উত্তরখান থানা",R.drawable.police_logo, R.drawable.uttarkhan_cover,
                "01713373164", 23.870906, 90.432525));
        lists.add(new List("কাফরুল থানা",R.drawable.police_logo, R.drawable.kafrul_cover,
                "02-9871771",23.801258, 90.381491));
        lists.add(new List("কলাবাগান থানা",R.drawable.police_logo, R.drawable.kalabagan_cover,
                "01713398339", 23.743464, 90.388251));
        lists.add(new List("খিলগাঁও থানা",R.drawable.police_logo, R.drawable.khilgaon_cover,
                "02-47219090", 23.750941, 90.425220));
        lists.add(new List("খিলক্ষেত থানা",R.drawable.police_logo, R.drawable.khilkhet_cover,
                "01769691792", 23.828149, 90.419649));
        lists.add(new List("গেন্ডারিয়া থানা",R.drawable.police_logo, R.drawable.ganderia_cover,
                "01713398331",23.699556, 90.421028));
        lists.add(new List("বনানী থানা",R.drawable.police_logo, R.drawable.banani_cover,
                "01769058053", 23.789959, 90.401878));
        lists.add(new List("তেজগাঁও থানা",R.drawable.police_logo, R.drawable.tejgaon_cover,
                "01713373181", 23.761450, 90.389757));
        lists.add(new List("তুরাগ থানা",R.drawable.police_logo, R.drawable.turag_cover,
                "02-8982111", 23.889197, 90.370964));
        lists.add(new List("ধানমন্ডি থানা",R.drawable.police_logo, R.drawable.dhanmondi_cover,
                "02-8631941", 23.743273, 90.381507));
        lists.add(new List("মোহাম্মদপুর থানা",R.drawable.police_logo, R.drawable.mohammadpur_cover,
                "01713373182", 23.755429, 90.363856));
        lists.add(new List("নিউমার্কেট থানা",R.drawable.police_logo, R.drawable.newmarket_cover,
                "02-8631942", 23.733075, 90.386900));
        lists.add(new List("পল্লবী থানা",R.drawable.police_logo, R.drawable.pollobi_cover,
                "01713373190", 23.826190, 90.366446));
        lists.add(new List("পল্টন মডেল থানা",R.drawable.police_logo, R.drawable.paltan_cover,
                "02-9360802", 23.736093, 90.416192));
        lists.add(new List("বিমানবন্দর থানা",R.drawable.police_logo, R.drawable.airport_cover,
                "01713373162", 23.850219, 90.409105));
        lists.add(new List("বাড্ডা থানা",R.drawable.police_logo, R.drawable.badda_cover,
                "01713373173", 23.771390, 90.427435));
        lists.add(new List("বংশাল থানা",R.drawable.police_logo, R.drawable.bongshal_cover,
                "02-9565700", 23.714120, 90.410516));
        lists.add(new List("ভাষানটেক পুলিশ ফাঁড়ি",R.drawable.police_logo, R.drawable.vasantek_cover,
                "02-8713023", 23.813466, 90.391202));
        lists.add(new List("ভাটারা থানা",R.drawable.police_logo, R.drawable.vatara_cover,
                "01769058055", 23.797428, 90.423969));
        lists.add(new List("মিরপুর মডেল থানা",R.drawable.police_logo, R.drawable.mirpur_cover,
                "02-9001001", 23.804394, 90.363028));
        lists.add(new List("মতিঝিল থানা",R.drawable.police_logo, R.drawable.motijhil_cover,
                "02-9571000", 23.729964, 90.417051));
        lists.add(new List("যাত্রাবাড়ী থানা",R.drawable.police_logo, R.drawable.jatrabari_cover,
                "01713373146", 23.710275, 90.435540));
        lists.add(new List("রামপুরা থানা",R.drawable.police_logo, R.drawable.rampura_cover,
                "01817602050", 23.761364, 90.443619));
        lists.add(new List("রমনা মডেল থানা",R.drawable.police_logo, R.drawable.ramna_cover,
                "01769691652", 23.745672, 90.404596));
        lists.add(new List("রূপনগর থানা",R.drawable.police_logo, R.drawable.rupnagar_cover,
                "01713373190", 23.817373, 90.360089));
        lists.add(new List("শ্যামপুর থানা",R.drawable.police_logo, R.drawable.shampur_cover,
                "02-47440691", 23.691820, 90.431275));
        lists.add(new List("শেরেবাংলা নগর থানা",R.drawable.police_logo, R.drawable.sherebangla_cover,
                "02-9124154", 23.776062, 90.376872));
        lists.add(new List("শাহবাগ থানা",R.drawable.police_logo, R.drawable.shahbag_cover,
                "02-9676699", 23.737206, 90.396175));
        lists.add(new List("শাহ আলী থানা",R.drawable.police_logo, R.drawable.shahali_cover,
                "01191005500", 23.805428, 90.348874));
        lists.add(new List("শাহজাহানপুর থানা",R.drawable.police_logo, R.drawable.shahjahanpur_cover,
                "02-9360535", 23.739315, 90.425656));
        lists.add(new List("সূত্রাপুর থানা",R.drawable.police_logo, R.drawable.sutrapur_cover,
                "02-7116233", 23.702389, 90.418242));
        lists.add(new List("সবুজবাগ থানা",R.drawable.police_logo, R.drawable.sabujbag_cover,
                "01713373153", 23.740584, 90.428355));
        lists.add(new List("হাজারীবাগ থানা",R.drawable.police_logo, R.drawable.hajaribagh_cover,
                "02-9669900", 23.735073, 90.369061));
        /**lists.add(new List("",R.drawable.police_logo, R.drawable.,
                "", ));**/


        LinearLayout header = (LinearLayout) findViewById(R.id.header);
        header.setVisibility(View.VISIBLE);

        ImageView headIcon = (ImageView) findViewById(R.id.header_icon);
        headIcon.setImageResource(R.drawable.police_logo);

        TextView headerTitle = (TextView) findViewById(R.id.header_title);
        headerTitle.setText("Police Stations inside Dhaka City");

        ListAdapter adapter = new ListAdapter(this, lists);

        GridView gridView = (GridView) findViewById(R.id.list);

        gridView.setNumColumns(1);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final List list = lists.get(position);
                title = list.getText();
                icon = list.getIcon();
                cover = list.getCover();
                phone = list.getPhone();
                lat = list.getLat();
                lon = list.getLon();

                Intent option = new Intent(view.getContext(), PoliceOption.class);
                startActivity(option);
            }
        });
    }
    public String title(){
        return title;
    }
    public int icon(){
        return icon;
    }
    public int setCover(){
        return cover;
    }
    public String setPhone(){
        return phone;
    }
    public double setLat(){
        return lat;
    }
    public double setLon(){
        return lon;
    }
}
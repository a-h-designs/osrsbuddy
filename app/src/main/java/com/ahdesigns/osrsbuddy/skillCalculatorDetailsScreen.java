package com.ahdesigns.osrsbuddy;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Objects;

public class skillCalculatorDetailsScreen extends AppCompatActivity {

    ImageView skillImage;

    TextView currentLevel, nextLevel, nextXP;

    String username, skill, num, nextLvl, b,
            title, imageSrc, jsonURL,
            url, currentLvl, overallRankValue,
            attackLvlValue, attackXpValue,
            defenceLvlValue, defenceXpValue,
            strengthLvlValue, strengthXpValue,
            hitpointsLvlValue,hitpointsXpValue,
            rangedLvlValue, rangedXpValue,
            prayerLvlValue, prayerXpValue,
            magicLvlValue, magicXpValue,
            cookingLvlValue, cookingXpValue,
            woodcuttingLvlValue, woodcuttingXpValue,
            fletchingLvlValue, fletchingXpValue,
            fishingLvlValue, fishingXpValue,
            firemakingLvlValue, firemakingXpValue,
            craftingLvlValue, craftingXpValue,
            smithingLvlValue, smithingXpValue,
            miningLvlValue, miningXpValue,
            herbloreLvlValue, herbloreXpValue,
            agilityLvlValue, agilityXpValue,
            thievingLvlValue, thievingXpValue,
            slayerLvlValue, slayerXpValue,
            farmingLvlValue, farmingXpValue,
            runecraftingLvlValue, runecraftingXpValue,
            hunterLvlValue, hunterXpValue,
            constructionLvlValue, constructionXpValue;

    int resImage, sum, xpLeft;

    private ProgressDialog pDialog;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skillcalculatordetailsscreen);

        skillImage = findViewById(R.id.chatHead);

        currentLevel = findViewById(R.id.currentLevel);
        nextLevel = findViewById(R.id.nextLevel);
        nextXP = findViewById(R.id.nextXP);

        Bundle extras = getIntent().getExtras();
        assert extras != null;
        username = extras.getString("username");
        skill = extras.getString("skill");
        title = skill.substring(0, 1).toUpperCase() + skill.substring(1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);

        try {
            assert url != null;
            jsonURL = "http://ahdesigns.coolpage.biz/old_hiscores.php?user=" + URLEncoder.encode(username, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        imageSrc = ("calculator_icon_" + skill);
        resImage = getResources().getIdentifier(imageSrc , "drawable", getPackageName());
        skillImage.setImageResource(resImage);
        //Call the GetData class
        new GetData().execute();

    }

    //Get data class to get json data
    @SuppressLint("StaticFieldLeak")
    private class GetData extends AsyncTask<Void, Void, Void> {

        //Before data will be looked up
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Showing progress dialog
            pDialog = new ProgressDialog(skillCalculatorDetailsScreen.this);
            pDialog.setMessage("Searching Hiscores...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        //During the background operation
        @Override
        protected Void doInBackground(Void... arg0) {

            // Creating service handler class
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(jsonURL, ServiceHandler.GET);

            //Check to see if JSON string is not empty
            if (jsonStr != null) {
                try {

                    //Create a new JSON object
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting the 'overall' object from the JSON object
                    JSONObject overall = jsonObj.getJSONObject("overall");

                    //From the 'overall' object, we want the below strings
                    //Assign all string and int to variables
                    overallRankValue = overall.getString("rank");


                    // Getting the 'attack' object from the JSON object
                    JSONObject attack = jsonObj.getJSONObject("attack");

                    //From the 'attack' object, we want the below strings
                    //Assign all string and int to variables
                    attackLvlValue = attack.getString("level");
                    attackXpValue = attack.getString("xp");

                    // Getting the 'defence' object from the JSON object
                    JSONObject defence = jsonObj.getJSONObject("defence");

                    //From the 'defence' object, we want the below strings
                    //Assign all string and int to variables
                    defenceLvlValue = defence.getString("level");
                    defenceXpValue = defence.getString("xp");

                    // Getting the 'strength' object from the JSON object
                    JSONObject strength = jsonObj.getJSONObject("strength");

                    //From the 'strength' object, we want the below strings
                    //Assign all string and int to variables
                    strengthLvlValue = strength.getString("level");
                    strengthXpValue = strength.getString("xp");

                    // Getting the 'constitution' object from the JSON object
                    JSONObject hitpoints = jsonObj.getJSONObject("hitpoints");

                    //From the 'constitution' object, we want the below strings
                    //Assign all string and int to variables
                    hitpointsLvlValue = hitpoints.getString("level");
                    hitpointsXpValue = hitpoints.getString("xp");

                    // Getting the 'ranged' object from the JSON object
                    JSONObject ranged = jsonObj.getJSONObject("ranged");

                    //From the 'ranged' object, we want the below strings
                    //Assign all string and int to variables
                    rangedLvlValue = ranged.getString("level");
                    rangedXpValue = ranged.getString("xp");

                    // Getting the 'prayer' object from the JSON object
                    JSONObject prayer = jsonObj.getJSONObject("prayer");

                    //From the 'prayer' object, we want the below strings
                    //Assign all string and int to variables
                    prayerLvlValue = prayer.getString("level");
                    prayerXpValue = prayer.getString("xp");

                    // Getting the 'magic' object from the JSON object
                    JSONObject magic = jsonObj.getJSONObject("magic");

                    //From the 'magic' object, we want the below strings
                    //Assign all string and int to variables
                    magicLvlValue = magic.getString("level");
                    magicXpValue = magic.getString("xp");

                    // Getting the 'cooking' object from the JSON object
                    JSONObject cooking = jsonObj.getJSONObject("cooking");

                    //From the 'cooking' object, we want the below strings
                    //Assign all string and int to variables
                    cookingLvlValue = cooking.getString("level");
                    cookingXpValue = cooking.getString("xp");

                    // Getting the 'woodcutting' object from the JSON object
                    JSONObject woodcutting = jsonObj.getJSONObject("woodcutting");

                    //From the 'woodcutting' object, we want the below strings
                    //Assign all string and int to variables
                    woodcuttingLvlValue = woodcutting.getString("level");
                    woodcuttingXpValue = woodcutting.getString("xp");

                    // Getting the 'fletching' object from the JSON object
                    JSONObject fletching = jsonObj.getJSONObject("fletching");

                    //From the 'fletching' object, we want the below strings
                    //Assign all string and int to variables
                    fletchingLvlValue = fletching.getString("level");
                    fletchingXpValue = fletching.getString("xp");

                    // Getting the 'fishing' object from the JSON object
                    JSONObject fishing = jsonObj.getJSONObject("fishing");

                    //From the 'fishing' object, we want the below strings
                    //Assign all string and int to variables
                    fishingLvlValue = fishing.getString("level");
                    fishingXpValue = fishing.getString("xp");

                    // Getting the 'firemaking' object from the JSON object
                    JSONObject firemaking = jsonObj.getJSONObject("firemaking");

                    //From the 'firemaking' object, we want the below strings
                    //Assign all string and int to variables
                    firemakingLvlValue = firemaking.getString("level");
                    firemakingXpValue = firemaking.getString("xp");

                    // Getting the 'crafting' object from the JSON object
                    JSONObject crafting = jsonObj.getJSONObject("crafting");

                    //From the 'crafting' object, we want the below strings
                    //Assign all string and int to variables
                    craftingLvlValue = crafting.getString("level");
                    craftingXpValue = crafting.getString("xp");

                    // Getting the 'smithing' object from the JSON object
                    JSONObject smithing = jsonObj.getJSONObject("smithing");

                    //From the 'smithing' object, we want the below strings
                    //Assign all string and int to variables
                    smithingLvlValue = smithing.getString("level");
                    smithingXpValue = smithing.getString("xp");

                    // Getting the 'mining' object from the JSON object
                    JSONObject mining = jsonObj.getJSONObject("mining");

                    //From the 'mining' object, we want the below strings
                    //Assign all string and int to variables
                    miningLvlValue = mining.getString("level");
                    miningXpValue = mining.getString("xp");

                    // Getting the 'herblore' object from the JSON object
                    JSONObject herblore = jsonObj.getJSONObject("herblore");

                    //From the 'herblore' object, we want the below strings
                    //Assign all string and int to variables
                    herbloreLvlValue = herblore.getString("level");
                    herbloreXpValue = herblore.getString("xp");

                    // Getting the 'agility' object from the JSON object
                    JSONObject agility = jsonObj.getJSONObject("agility");

                    //From the 'agility' object, we want the below strings
                    //Assign all string and int to variables
                    agilityLvlValue = agility.getString("level");
                    agilityXpValue = agility.getString("xp");

                    // Getting the 'thieving' object from the JSON object
                    JSONObject thieving = jsonObj.getJSONObject("thieving");

                    //From the 'thieving' object, we want the below strings
                    //Assign all string and int to variables
                    thievingLvlValue = thieving.getString("level");
                    thievingXpValue = thieving.getString("xp");

                    // Getting the 'slayer' object from the JSON object
                    JSONObject slayer = jsonObj.getJSONObject("slayer");

                    //From the 'slayer' object, we want the below strings
                    //Assign all string and int to variables
                    slayerLvlValue = slayer.getString("level");
                    slayerXpValue = slayer.getString("xp");

                    // Getting the 'farming' object from the JSON object
                    JSONObject farming = jsonObj.getJSONObject("farming");

                    //From the 'farming' object, we want the below strings
                    //Assign all string and int to variables
                    farmingLvlValue = farming.getString("level");
                    farmingXpValue = farming.getString("xp");

                    // Getting the 'runecrafting' object from the JSON object
                    JSONObject runecrafting = jsonObj.getJSONObject("runecrafting");

                    //From the 'runecrafting' object, we want the below strings
                    //Assign all string and int to variables
                    runecraftingLvlValue = runecrafting.getString("level");
                    runecraftingXpValue = runecrafting.getString("xp");

                    // Getting the 'hunter' object from the JSON object
                    JSONObject hunter = jsonObj.getJSONObject("hunter");

                    //From the 'hunter' object, we want the below strings
                    //Assign all string and int to variables
                    hunterLvlValue = hunter.getString("level");
                    hunterXpValue = hunter.getString("xp");

                    // Getting the 'construction' object from the JSON object
                    JSONObject construction = jsonObj.getJSONObject("construction");

                    //From the 'construction' object, we want the below strings
                    //Assign all string and int to variables
                    constructionLvlValue = construction.getString("level");
                    constructionXpValue = construction.getString("xp");

                    //Catch any errors
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if(overallRankValue != null) {

                //After we have obtained the JSON data
                // Dismiss the progress dialog
                if (pDialog.isShowing())
                    pDialog.dismiss();

                if(skill.equals("mining")) {
                    num = miningXpValue;
                    currentLevel.setText(miningLvlValue);
                    calculateXP(num);
                }
                if(skill.equals("thieving")) {
                    num = thievingXpValue;
                    currentLevel.setText(thievingLvlValue);
                    calculateXP(num);
                }
            } else {
                if (pDialog.isShowing())
                    pDialog.dismiss();
                Toast.makeText(getApplicationContext(), username + " was not found in the Overall table", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void calculateXP(String num){
        DecimalFormat df = new DecimalFormat();
        df.setGroupingUsed(true);
        df.setGroupingSize(3);

        currentLvl = currentLevel.getText().toString();
        sum = Integer.parseInt(currentLvl) + Integer.parseInt("1");
        nextLvl = String.valueOf(sum);

        if(currentLevel.getText() != null) {
            switch (currentLvl) {
                case "50":
                    nextLevel.setText(nextLvl);
                    nextXP.setText(df.format(Long.valueOf("111945")));
                    break;
                case "51":
                    nextLevel.setText(nextLvl);
                    nextXP.setText(df.format(Long.valueOf("123660")));
                    break;
                case "52":
                    nextLevel.setText(nextLvl);
                    nextXP.setText(df.format(Long.valueOf("136594")));
                    break;
                case "53":
                    nextLevel.setText(nextLvl);
                    nextXP.setText(df.format(Long.valueOf("150872")));
                    break;
                case "54":
                    nextLevel.setText(nextLvl);
                    nextXP.setText(df.format(Long.valueOf("166636")));
                    break;
                case "55":
                    nextLevel.setText(nextLvl);
                    nextXP.setText(df.format(Long.valueOf("184040")));
                    break;
                case "72":
                    xpLeft = Integer.parseInt("992895") - Integer.parseInt(num);
                    nextLevel.setText(nextLvl);
                    nextXP.setText(df.format(Long.valueOf(String.valueOf(xpLeft))));
                    break;
                case "74":
                    nextLevel.setText(nextLvl);
                    nextXP.setText(df.format(Long.valueOf("1210421")));
                    break;
                case "75":
                    xpLeft = Integer.parseInt("1336443") - Integer.parseInt(num);
                    nextLevel.setText(b);
                    nextXP.setText(df.format(Long.valueOf(String.valueOf(xpLeft))));
                    break;
                case "97":
                    xpLeft = Integer.parseInt("11805606") - Integer.parseInt(num);
                    nextLevel.setText(nextLvl);
                    nextXP.setText(df.format(Long.valueOf(String.valueOf(xpLeft))));
                    break;
                default:
                    nextLevel.setText("2");
                    nextXP.setText(df.format(Long.valueOf("83")));
                    break;
            }
        }
    }
}
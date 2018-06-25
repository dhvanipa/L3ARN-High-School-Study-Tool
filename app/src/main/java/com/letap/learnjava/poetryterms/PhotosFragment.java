package com.letap.learnjava.poetryterms;

/**
 * Created by Dhvani on 31/10/2015.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class PhotosFragment extends Fragment implements
        SearchView.OnQueryTextListener, SearchView.OnCloseListener{

    public PhotosFragment() {
    }

    private SearchView search;
    private ExpandableListAdapter listAdapter;
    private ExpandableListView myList;
    static private ArrayList<Continent> continentList = new ArrayList<Continent>();
    static private ArrayList<Country> countryList;
    static ArrayList<String> biology = new ArrayList<String>();
    static ArrayList<String> chemistry = new ArrayList<String>();
    static ArrayList<String> physics = new ArrayList<String>();
    static ArrayList<String> computerScience = new ArrayList<String>();
    static ArrayList<String> geology = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_photos, container, false);

        final int[] biopics = new int[]{
                R.drawable.bio1,
                R.drawable.bio2,
                R.drawable.bio3,
                R.drawable.bio4,
                R.drawable.bio5,
                R.drawable.bio6,
                R.drawable.bio7,
                R.drawable.bio8,
                R.drawable.bio9,
                R.drawable.bio10,
                R.drawable.bio11,
                R.drawable.bio12,
                R.drawable.bio13,
                R.drawable.bio14,
                R.drawable.bio15,
                R.drawable.bio16,
                R.drawable.bio17,
                R.drawable.bio18,
                R.drawable.bio19,
                R.drawable.bio20,
                R.drawable.bio21,
                R.drawable.bio22,
                R.drawable.bio23,
                R.drawable.bio24,
                R.drawable.bio25,
                R.drawable.bio26,
                R.drawable.bio27,
                R.drawable.bio28,
                R.drawable.bio29,
                R.drawable.bio30,
                R.drawable.bio31,
                R.drawable.bio32,
                R.drawable.bio33,
                R.drawable.bio34,
                R.drawable.bio35,
                R.drawable.bio36,
                R.drawable.bio37,
                R.drawable.bio38,
                R.drawable.bio39,
                R.drawable.bio40,
                R.drawable.bio41,
                R.drawable.bio42,
                R.drawable.bio43,
                R.drawable.bio44,
                R.drawable.bio45,
                R.drawable.bio46,
                R.drawable.bio47,
                R.drawable.bio48,
                R.drawable.bio49,
                R.drawable.bio50,
                R.drawable.bio51,
                R.drawable.bio52,
                R.drawable.bio53,
                R.drawable.bio54,
                R.drawable.bio55,
                R.drawable.bio56,
                R.drawable.bio57,
                R.drawable.bio58,
                R.drawable.bio59,
                R.drawable.bio60,
                R.drawable.bio61,
                R.drawable.bio62,
                R.drawable.bio63,
                R.drawable.bio64,
                R.drawable.bio65,
                R.drawable.bio66,
                R.drawable.bio67,
                R.drawable.bio68,
                R.drawable.bio69,
                R.drawable.bio70,
                R.drawable.bio71,
                R.drawable.bio72,
                R.drawable.bio73,
                R.drawable.bio74,
                R.drawable.bio75,
                R.drawable.bio76,
                R.drawable.bio77,
                R.drawable.bio78,
                R.drawable.bio79,
                R.drawable.bio80,
                R.drawable.bio81,
                R.drawable.bio82,
                R.drawable.bio83,
                R.drawable.bio84,
                R.drawable.bio85,
                R.drawable.bio86,
                R.drawable.bio87,
                R.drawable.bio88,
                R.drawable.bio89,
                R.drawable.bio90,
                R.drawable.bio91,
                R.drawable.bio92,
                R.drawable.bio93,
                R.drawable.bio94,
                R.drawable.bio95,
                R.drawable.bio96,
                R.drawable.bio97,
                R.drawable.bio98,
                R.drawable.bio99,
                R.drawable.bio100,
                R.drawable.bio101,
                R.drawable.bio102,
                R.drawable.bio103,
                R.drawable.bio104,
                R.drawable.bio105,
                R.drawable.bio106,
                R.drawable.bio107,
                R.drawable.bio108,
                R.drawable.bio109,
                R.drawable.bio110,
                R.drawable.bio111,
                R.drawable.bio112,
                R.drawable.bio113,
                R.drawable.bio114,
                R.drawable.bio115,
                R.drawable.bio116,
                R.drawable.bio117,
                R.drawable.bio118,
        };

        final int[] chempics = new int[]{
                R.drawable.chem1,
                R.drawable.chem2,
                R.drawable.chem3,
                R.drawable.chem4,
                R.drawable.chem5,
                R.drawable.chem6,
                R.drawable.chem7,
                R.drawable.chem8,
                R.drawable.chem9,
                R.drawable.chem10,
                R.drawable.chem11,
                R.drawable.chem12,
                R.drawable.chem13,
                R.drawable.chem14,
                R.drawable.chem15,
                R.drawable.chem16,
                R.drawable.chem17,
                R.drawable.chem18,
                R.drawable.chem19,
                R.drawable.chem20,
                R.drawable.chem21,
                R.drawable.chem22,
                R.drawable.chem23,
                R.drawable.chem24,
                R.drawable.chem25,
                R.drawable.chem26,
                R.drawable.chem27,
                R.drawable.chem28,
                R.drawable.chem29,
                R.drawable.chem30,
                R.drawable.chem31,
                R.drawable.chem32,
                R.drawable.chem33,
                R.drawable.chem34,
                R.drawable.chem35,
                R.drawable.chem36,
                R.drawable.chem37,
                R.drawable.chem38,
                R.drawable.chem39,
                R.drawable.chem40,
                R.drawable.chem41,
                R.drawable.chem42,
                R.drawable.chem43,
                R.drawable.chem44,
                R.drawable.chem45,
                R.drawable.chem46,
                R.drawable.chem47,
                R.drawable.chem48,
                R.drawable.chem49,
                R.drawable.chem50,
                R.drawable.chem51,
                R.drawable.chem52,
                R.drawable.chem53,
                R.drawable.chem54,
                R.drawable.chem55,
                R.drawable.chem56,
                R.drawable.chem57,
                R.drawable.chem58,
                R.drawable.chem59,
                R.drawable.chem60,
                R.drawable.chem61,
                R.drawable.chem62,
                R.drawable.chem63,
                R.drawable.chem64,
                R.drawable.chem65,
                R.drawable.chem66,
                R.drawable.chem67,
                R.drawable.chem68,
                R.drawable.chem69,
                R.drawable.chem70,
                R.drawable.chem71,
                R.drawable.chem72,
                R.drawable.chem73,
                R.drawable.chem74,
                R.drawable.chem75,
                R.drawable.chem76,
                R.drawable.chem77,
                R.drawable.chem78,
                R.drawable.chem79,
                R.drawable.chem80,
                R.drawable.chem81,
                R.drawable.chem82,
                R.drawable.chem83,
                R.drawable.chem84,
                R.drawable.chem85,
                R.drawable.chem86,
                R.drawable.chem87,
                R.drawable.chem88,
                R.drawable.chem89,
                R.drawable.chem90,
                R.drawable.chem91,
                R.drawable.chem92,
                R.drawable.chem93,
                R.drawable.chem94,
                R.drawable.chem95,
                R.drawable.chem96,
                R.drawable.chem97,
        };

        final int[] phypics = new int[]{
                R.drawable.phy1,
                R.drawable.phy2,
                R.drawable.phy3,
                R.drawable.phy4,
                R.drawable.phy5,
                R.drawable.phy6,
                R.drawable.phy7,
                R.drawable.phy8,
                R.drawable.phy9,
                R.drawable.phy10,
                R.drawable.phy11,
                R.drawable.phy12,
                R.drawable.phy13,
                R.drawable.phy14,
                R.drawable.phy15,
                R.drawable.phy16,
                R.drawable.phy17,
                R.drawable.phy18,
                R.drawable.phy19,
                R.drawable.phy20,
                R.drawable.phy21,
                R.drawable.phy22,
                R.drawable.phy23,
                R.drawable.phy24,
                R.drawable.phy25,
                R.drawable.phy26,
                R.drawable.phy27,
                R.drawable.phy28,
                R.drawable.phy29,
                R.drawable.phy30,
                R.drawable.phy31,
                R.drawable.phy32,
                R.drawable.phy33,
                R.drawable.phy34,
                R.drawable.phy35,
                R.drawable.phy36,
                R.drawable.phy37,
                R.drawable.phy38,
                R.drawable.phy39,
                R.drawable.phy40,
                R.drawable.phy41,
                R.drawable.phy42,
                R.drawable.phy43,
        };

        final int[] compscipics = new int[]{
             R.drawable.compsci1,
             R.drawable.compsci2,
             R.drawable.compsci3,
             R.drawable.compsci4,
             R.drawable.compsci5,
             R.drawable.compsci6,
             R.drawable.compsci7,
        };

        final int[] geologypics = new int[]{
                R.drawable.geo1,
                R.drawable.geo2,
                R.drawable.geo3,
                R.drawable.geo4,
                R.drawable.geo5,
                R.drawable.geo6,
                R.drawable.geo7,
                R.drawable.geo8,
                R.drawable.geo9,
                R.drawable.geo10,
                R.drawable.geo11,
                R.drawable.geo12,
                R.drawable.geo13,
                R.drawable.geo14,
                R.drawable.geo15,
                R.drawable.geo16,
                R.drawable.geo17,
                R.drawable.geo18,
                R.drawable.geo19,
                R.drawable.geo20,
                R.drawable.geo21,
                R.drawable.geo22,
                R.drawable.geo23,
                R.drawable.geo24,
                R.drawable.geo25,
                R.drawable.geo26,
                R.drawable.geo27,
                R.drawable.geo28,
                R.drawable.geo29,
                R.drawable.geo30,
                R.drawable.geo31,
                R.drawable.geo32,
                R.drawable.geo33,
                R.drawable.geo34,
                R.drawable.geo35,
                R.drawable.geo36,
                R.drawable.geo37,
                R.drawable.geo38,
                R.drawable.geo39,
                R.drawable.geo40,
                R.drawable.geo41,
                R.drawable.geo42,
                R.drawable.geo43,
                R.drawable.geo44,
                R.drawable.geo45,
                R.drawable.geo46,
                R.drawable.geo47,
                R.drawable.geo48,
                R.drawable.geo49,
                R.drawable.geo50,
                R.drawable.geo51,
                R.drawable.geo52,
                R.drawable.geo53,
                R.drawable.geo54,
                R.drawable.geo55,
                R.drawable.geo56,
                R.drawable.geo57,
                R.drawable.geo58,
                R.drawable.geo59,
                R.drawable.geo60,
                R.drawable.geo61,
                R.drawable.geo62,
                R.drawable.geo63,
                R.drawable.geo64,
                R.drawable.geo65,
        };



        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        search = (SearchView) rootView.findViewById(R.id.search);
        search.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);
        myList = (ExpandableListView) rootView.findViewById(R.id.lvProvinceNames);

        //add header to listview
        //LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.activity_listheader, myList, false);
        ImageView header2 = (ImageView)header.findViewById(R.id.imageHeader);
        header2.setImageResource(R.drawable.headersc);
        myList.addHeaderView(header, null, false);

        // display the list
        displayList();
        // expand all Groups
        // expandAll();


        myList.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {



                TextView text = (TextView) v.findViewById(R.id.name);
                String selected = text.getText().toString();

                String province = "";
                String provincedesclabel = "";
                int prvImg = 0;
                Bitmap prvBImg = null;

               // System.out.println(text.getText().toString());
                if(biology.contains(selected) == true){

                    //we get the title
                    int fIndex = biology.indexOf(selected);
                    province = biology.get(fIndex);

                    //we retrieve the description of the juices from an array defined in arrays.xml
                    String[] provincedescription = getResources().getStringArray(R.array.bio);
                    provincedesclabel = provincedescription[fIndex];

                    //we get the image
                    prvImg = biopics[fIndex];

                }
                else if(chemistry.contains(selected) == true){

                    //we get the title
                    int fIndex = chemistry.indexOf(selected);
                    province = chemistry.get(fIndex);

                    //we retrieve the description of the juices from an array defined in arrays.xml
                    String[] provincedescription = getResources().getStringArray(R.array.chem);
                    provincedesclabel = provincedescription[fIndex];

                    //we get the image
                    prvImg = chempics[fIndex];

                }
                else if(physics.contains(selected) == true){

                    //we get the title
                    int fIndex = physics.indexOf(selected);
                    province = physics.get(fIndex);

                    //we retrieve the description of the juices from an array defined in arrays.xml
                    String[] provincedescription = getResources().getStringArray(R.array.phy);
                    provincedesclabel = provincedescription[fIndex];

                    //we get the image
                    prvImg = phypics[fIndex];

                }
                else if(computerScience.contains(selected) == true){
                    //we get the title
                    int fIndex = computerScience.indexOf(selected);
                    province = computerScience.get(fIndex);

                    //we retrieve the description of the juices from an array defined in arrays.xml
                    String[] provincedescription = getResources().getStringArray(R.array.comp);
                    provincedesclabel = provincedescription[fIndex];

                    //we get the image
                    prvImg = compscipics[fIndex];

                }
                else if(geology.contains(selected) == true){

                    //we get the title
                    int fIndex = geology.indexOf(selected);
                    province = geology.get(fIndex);

                    //we retrieve the description of the juices from an array defined in arrays.xml
                    String[] provincedescription = getResources().getStringArray(R.array.geology);
                    provincedesclabel = provincedescription[fIndex];

                    //we get the image
                    prvImg = geologypics[fIndex];


                }

                //retrieve content for the dialog
                String[] dialogmessage = getResources().getStringArray(R.array.dialomessage);
                final String dialogmsg = dialogmessage[0];

                Intent intent = new Intent(getActivity().getApplicationContext(), detailActivity.class);
                intent.putExtra("province", province);
                intent.putExtra("provincedesclabel", provincedesclabel);
                intent.putExtra("prvImg", prvImg);
                intent.putExtra("dialogmsg", dialogmsg);

                startActivity(intent);

                return false;
            }
        });


        return rootView;
    }

    // method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            myList.expandGroup(i);
        }
    }

    // method to expand all groups
    private void displayList() {

        // display the list
        loadSomeData();

        // get reference to the ExpandableListView

        // create the adapter by passing your ArrayList data
        listAdapter = new ExpandableListAdapter(getActivity(), continentList);
        // attach the adapter to the list
        myList.setAdapter(listAdapter);

    }



    public static void loadSomeData() {
        countryList = new ArrayList<Country>();
        //Country country = new Country("Bermuda");
        Country country = new Country("Active Transport");
        countryList.add(country);
        country = new Country("Adhesion");
        countryList.add(country);
        country = new Country("Area Of Elongation");
        countryList.add(country);
        country = new Country("Auxin");
        countryList.add(country);
        country = new Country("Capillary Action");
        countryList.add(country);
        country = new Country("Carbohydrates");
        countryList.add(country);
        country = new Country("Carrier Proteins");
        countryList.add(country);
        country = new Country("Cell Communication");
        countryList.add(country);
        country = new Country("Cell Membrane");
        countryList.add(country);
        country = new Country("Cell Theory");
        countryList.add(country);
        country = new Country("Cell Wall");
        countryList.add(country);
        country = new Country("Cellular Respiration");
        countryList.add(country);
        country = new Country("Channel Proteins");
        countryList.add(country);
        country = new Country("Chlorophyll");
        countryList.add(country);
        country = new Country("Chloroplast");
        countryList.add(country);
        country = new Country("Cohesion");
        countryList.add(country);
        country = new Country("Companion Cells");
        countryList.add(country);
        country = new Country("Concentration Gradient");
        countryList.add(country);
        country = new Country("Confocal Technology");
        countryList.add(country);
        country = new Country("Contrast");
        countryList.add(country);
        country = new Country("Control Systems");
        countryList.add(country);
        country = new Country("Controlled Variables");
        countryList.add(country);
        country = new Country("Cuticle");
        countryList.add(country);
        country = new Country("Cytoplasm");
        countryList.add(country);
        country = new Country("Cytoplasmic Streaming");
        countryList.add(country);
        country = new Country("Dialysis");
        countryList.add(country);
        country = new Country("Diffusion");
        countryList.add(country);
        country = new Country("Electron Microscopy (EM)");
        countryList.add(country);
        country = new Country("Endocytosis");
        countryList.add(country);
        country = new Country("Endoplasmic Reticulum (ER)");
        countryList.add(country);
        country = new Country("Epidermis");
        countryList.add(country);
        country = new Country("Equilibrium");
        countryList.add(country);
        country = new Country("Exocytosis");
        countryList.add(country);
        country = new Country("Facilitated Diffusion");
        countryList.add(country);
        country = new Country("Field Of View");
        countryList.add(country);
        country = new Country("Fluid-mosaic Model");
        countryList.add(country);
        country = new Country("Fluorescence Microscopy");
        countryList.add(country);
        country = new Country("Gene Mapping");
        countryList.add(country);
        country = new Country("Geotropism");
        countryList.add(country);
        country = new Country("GFP Technology");
        countryList.add(country);
        country = new Country("Golgi Apparatus");
        countryList.add(country);
        country = new Country("Gravitropism");
        countryList.add(country);
        country = new Country("Ground Tissue");
        countryList.add(country);
        country = new Country("Guard Cells");
        countryList.add(country);
        country = new Country("Herbaceous");
        countryList.add(country);
        country = new Country("Hormone");
        countryList.add(country);
        country = new Country("Hypertonic");
        countryList.add(country);
        country = new Country("Hypotonic");
        countryList.add(country);
        country = new Country("Isotonic");
        countryList.add(country);
        country = new Country("Lenticels");
        countryList.add(country);
        country = new Country("Light Microscope");
        countryList.add(country);
        country = new Country("Lipids");
        countryList.add(country);
        country = new Country("Liposomes");
        countryList.add(country);
        country = new Country("Lysosome");
        countryList.add(country);
        country = new Country("Magnification");
        countryList.add(country);
        country = new Country("Manipulated Variable");
        countryList.add(country);
        country = new Country("Membrane");
        countryList.add(country);
        country = new Country("Membrane Technologies");
        countryList.add(country);
        country = new Country("Meristems");
        countryList.add(country);
        country = new Country("Mesophyll");
        countryList.add(country);
        country = new Country("Microscope");
        countryList.add(country);
        country = new Country("Mitochondrion");
        countryList.add(country);
        country = new Country("Negative Gravitropism");
        countryList.add(country);
        country = new Country("Negative Phototropism");
        countryList.add(country);
        country = new Country("Nucleic Acids");
        countryList.add(country);
        country = new Country("Nucleus");
        countryList.add(country);
        country = new Country("Osmosis");
        countryList.add(country);
        country = new Country("Palisade Tissue");
        countryList.add(country);
        country = new Country("Particle Model");
        countryList.add(country);
        country = new Country("Passive Transport");
        countryList.add(country);
        country = new Country("Peritoneal");
        countryList.add(country);
        country = new Country("Phloem Tissue");
        countryList.add(country);
        country = new Country("Phospholipid Bilayer");
        countryList.add(country);
        country = new Country("Photosynthesis");
        countryList.add(country);
        country = new Country("Phototropism");
        countryList.add(country);
        country = new Country("Plasmolysis");
        countryList.add(country);
        country = new Country("Positive Gravitropism");
        countryList.add(country);
        country = new Country("Positive Phototropism");
        countryList.add(country);
        country = new Country("Power");
        countryList.add(country);
        country = new Country("Pressure Differences");
        countryList.add(country);
        country = new Country("Pressure-Flow Theory");
        countryList.add(country);
        country = new Country("Protein");
        countryList.add(country);
        country = new Country("Rate Of Diffusion");
        countryList.add(country);
        country = new Country("Receptor Proteins");
        countryList.add(country);
        country = new Country("Recognition Proteins");
        countryList.add(country);
        country = new Country("Resolution Or Resolving");
        countryList.add(country);
        country = new Country("Reverse Osmosis (RO)");
        countryList.add(country);
        country = new Country("Ribosome");
        countryList.add(country);
        country = new Country("Root Hairs");
        countryList.add(country);
        country = new Country("Root Pressure");
        countryList.add(country);
        country = new Country("Root System");
        countryList.add(country);
        country = new Country("Selectively Permeable");
        countryList.add(country);
        country = new Country("Semi-permeable");
        countryList.add(country);
        country = new Country("Shoot System");
        countryList.add(country);
        country = new Country("Sieve Tube Cells");
        countryList.add(country);
        country = new Country("Sink Source");
        countryList.add(country);
        country = new Country("Spongy Mesophyll");
        countryList.add(country);
        country = new Country("Spontaneous Generation");
        countryList.add(country);
        country = new Country("Staining Technique");
        countryList.add(country);
        country = new Country("Stimuli");
        countryList.add(country);
        country = new Country("Stomata");
        countryList.add(country);
        country = new Country("Surface Area");
        countryList.add(country);
        country = new Country("System");
        countryList.add(country);
        country = new Country("Tension");
        countryList.add(country);
        country = new Country("Tissue");
        countryList.add(country);
        country = new Country("Tonicity");
        countryList.add(country);
        country = new Country("Trace Elements");
        countryList.add(country);
        country = new Country("Transpiration");
        countryList.add(country);
        country = new Country("Transpiration Pull");
        countryList.add(country);
        country = new Country("Tuber");
        countryList.add(country);
        country = new Country("Turgid");
        countryList.add(country);
        country = new Country("Turgor Pressure");
        countryList.add(country);
        country = new Country("Vacuole");
        countryList.add(country);
        country = new Country("Vascular Tissue");
        countryList.add(country);
        country = new Country("Vesicles");
        countryList.add(country);
        country = new Country("Volume");
        countryList.add(country);
        country = new Country("X-ray Crystallography");
        countryList.add(country);
        country = new Country("Xylem Tissue");
        countryList.add(country);

        Continent continent = new Continent("Biology", countryList);
        for(int i = 0; i < countryList.size(); i++){
            biology.add(countryList.get(i).getName());
        }
        continentList.add(continent);

        countryList = new ArrayList<Country>();
        country = new Country("Acid");
        countryList.add(country);
        country = new Country("Alkali Metal");
        countryList.add(country);
        country = new Country("Alkaline-earth Metal");
        countryList.add(country);
        country = new Country("Alloy");
        countryList.add(country);
        country = new Country("Anion");
        countryList.add(country);
        country = new Country("Atom");
        countryList.add(country);
        country = new Country("Atomic Molar Mass");
        countryList.add(country);
        country = new Country("Atomic Number");
        countryList.add(country);
        country = new Country("Avogadro’s Number");
        countryList.add(country);
        country = new Country("Base");
        countryList.add(country);
        country = new Country("Buffer");
        countryList.add(country);
        country = new Country("Cation");
        countryList.add(country);
        country = new Country("Chemical Change");
        countryList.add(country);
        country = new Country("Chemical Equation");
        countryList.add(country);
        country = new Country("Chemical Properties");
        countryList.add(country);
        country = new Country("Chemical Reaction");
        countryList.add(country);
        country = new Country("Colloid Compound");
        countryList.add(country);
        country = new Country("Combustion Reaction");
        countryList.add(country);
        country = new Country("Compound");
        countryList.add(country);
        country = new Country("Covalent Bond");
        countryList.add(country);
        country = new Country("Crystal Lattice");
        countryList.add(country);
        country = new Country("Decomposition Reaction");
        countryList.add(country);
        country = new Country("Diatomic");
        countryList.add(country);
        country = new Country("Double Replacement Reaction");
        countryList.add(country);
        country = new Country("Ductile");
        countryList.add(country);
        country = new Country("Electrolyte");
        countryList.add(country);
        country = new Country("Electron");
        countryList.add(country);
        country = new Country("Electron Energy Levels");
        countryList.add(country);
        country = new Country("Element");
        countryList.add(country);
        country = new Country("Endothermic Reaction");
        countryList.add(country);
        country = new Country("Energy Level");
        countryList.add(country);
        country = new Country("Exothermic Reaction");
        countryList.add(country);
        country = new Country("Family");
        countryList.add(country);
        country = new Country("Fermentation");
        countryList.add(country);
        country = new Country("Formation Reaction");
        countryList.add(country);
        country = new Country("Formula Equation");
        countryList.add(country);
        country = new Country("Group");
        countryList.add(country);
        country = new Country("Halogen");
        countryList.add(country);
        country = new Country("Heterogeneous Mixture");
        countryList.add(country);
        country = new Country("Homogeneous Mixture");
        countryList.add(country);
        country = new Country("Hydrocarbon Combustion");
        countryList.add(country);
        country = new Country("Hypothesis");
        countryList.add(country);
        country = new Country("Inert");
        countryList.add(country);
        country = new Country("Ion");
        countryList.add(country);
        country = new Country("Ionic Bonding");
        countryList.add(country);
        country = new Country("Ionic Compound");
        countryList.add(country);
        country = new Country("Ionization");
        countryList.add(country);
        country = new Country("Isotope");
        countryList.add(country);
        country = new Country("IUPAC");
        countryList.add(country);
        country = new Country("Law Of Conservation Of Mass");
        countryList.add(country);
        country = new Country("Litmus");
        countryList.add(country);
        country = new Country("Malleable");
        countryList.add(country);
        country = new Country("Manipulated Variable");
        countryList.add(country);
        country = new Country("Mass Number");
        countryList.add(country);
        country = new Country("Material Safety Data Sheet (MSDS)");
        countryList.add(country);
        country = new Country("Mechanical Mixture");
        countryList.add(country);
        country = new Country("Metalloid");
        countryList.add(country);
        country = new Country("Metallurgy");
        countryList.add(country);
        country = new Country("Metals");
        countryList.add(country);
        country = new Country("Molar Mass");
        countryList.add(country);
        country = new Country("Mole");
        countryList.add(country);
        country = new Country("Molecular Compound");
        countryList.add(country);
        country = new Country("Molecular Element");
        countryList.add(country);
        country = new Country("Molecule");
        countryList.add(country);
        country = new Country("Multivalent");
        countryList.add(country);
        country = new Country("Neutral");
        countryList.add(country);
        country = new Country("Neutralization");
        countryList.add(country);
        country = new Country("Neutron");
        countryList.add(country);
        country = new Country("Noble Gas");
        countryList.add(country);
        country = new Country("Non-metal");
        countryList.add(country);
        country = new Country("Nucleus");
        countryList.add(country);
        country = new Country("Octet Rule");
        countryList.add(country);
        country = new Country("Period");
        countryList.add(country);
        country = new Country("PH");
        countryList.add(country);
        country = new Country("PH Scale");
        countryList.add(country);
        country = new Country("Physical Change");
        countryList.add(country);
        country = new Country("Physical Properties");
        countryList.add(country);
        country = new Country("Polar");
        countryList.add(country);
        country = new Country("Polyatomic Ion");
        countryList.add(country);
        country = new Country("Precipitate");
        countryList.add(country);
        country = new Country("Precipitation");
        countryList.add(country);
        country = new Country("Product");
        countryList.add(country);
        country = new Country("Proton");
        countryList.add(country);
        country = new Country("Pure Substance");
        countryList.add(country);
        country = new Country("Reactant");
        countryList.add(country);
        country = new Country("Responding Variable");
        countryList.add(country);
        country = new Country("Salt");
        countryList.add(country);
        country = new Country("Single Replacement Reaction");
        countryList.add(country);
        country = new Country("Skeleton Equation");
        countryList.add(country);
        country = new Country("Solubility");
        countryList.add(country);
        country = new Country("Solution");
        countryList.add(country);
        country = new Country("Suspension");
        countryList.add(country);
        country = new Country("Universal Indicator");
        countryList.add(country);
        country = new Country("Valence");
        countryList.add(country);
        country = new Country("Valence Electron");
        countryList.add(country);
        country = new Country("Valence Number");
        countryList.add(country);
        country = new Country("Workplace Hazardous Materials Information System (WHMIS)");
        countryList.add(country);


        continent = new Continent("Chemistry", countryList);
        for(int i = 0; i < countryList.size(); i++){
            chemistry.add(countryList.get(i).getName());
        }
        continentList.add(continent);

        countryList = new ArrayList<Country>();
        country = new Country("Absorb");
        countryList.add(country);
        country = new Country("Adaptation");
        countryList.add(country);
        country = new Country("Albedo");
        countryList.add(country);
        country = new Country("Altitude");
        countryList.add(country);
        country = new Country("Anecdotal Evidence");
        countryList.add(country);
        country = new Country("Angle of Incidence");
        countryList.add(country);
        country = new Country("Angle of Inclination");
        countryList.add(country);
        country = new Country("Atmosphere");
        countryList.add(country);
        country = new Country("Atmospheric Dust");
        countryList.add(country);
        country = new Country("Atmospheric Pressure");
        countryList.add(country);
        country = new Country("Attitude");
        countryList.add(country);
        country = new Country("Biome");
        countryList.add(country);
        country = new Country("Biosphere");
        countryList.add(country);
        country = new Country("Calorimeter");
        countryList.add(country);
        country = new Country("Carbon Dioxide Sequesterin");
        countryList.add(country);
        country = new Country("Carbon Sink");
        countryList.add(country);
        country = new Country("Carbon Source");
        countryList.add(country);
        country = new Country("Climate");
        countryList.add(country);
        country = new Country("Climate Change");
        countryList.add(country);
        country = new Country("Climatograph");
        countryList.add(country);
        country = new Country("Closed System");
        countryList.add(country);
        country = new Country("Conduction");
        countryList.add(country);
        country = new Country("Convectio");
        countryList.add(country);
        country = new Country("Coriolis Effect");
        countryList.add(country);
        country = new Country("Current");
        countryList.add(country);
        country = new Country("Density");
        countryList.add(country);
        country = new Country("Enhanced Greenhouse Effect");
        countryList.add(country);
        country = new Country("Equinox");
        countryList.add(country);
        country = new Country("Fluid Jet Stream");
        countryList.add(country);
        country = new Country("Fossil Fuels");
        countryList.add(country);
        country = new Country("General Circulation Model (GCM)");
        countryList.add(country);
        country = new Country("Global Warming");
        countryList.add(country);
        country = new Country("Greenhouse Gases");
        countryList.add(country);
        country = new Country("Halocarbons");
        countryList.add(country);
        country = new Country("Heat of Condensation");
        countryList.add(country);
        country = new Country("Heat of Fusion");
        countryList.add(country);
        country = new Country("Heat of Solidification");
        countryList.add(country);
        country = new Country("Heat of Vaporization");
        countryList.add(country);
        country = new Country("Hydrochlorofluorocarbons (HCFCs)");
        countryList.add(country);
        country = new Country("Hydrologic (water) Cycle");
        countryList.add(country);
        country = new Country("Hydrosphere");
        countryList.add(country);
        country = new Country("Insolation");
        countryList.add(country);
        country = new Country("Inversion");
        countryList.add(country);
        country = new Country("Lithosphere");
        countryList.add(country);
        country = new Country("Mesosphere");
        countryList.add(country);
        country = new Country("Natural Greenhouse Effect");
        countryList.add(country);
        country = new Country("Net Radiation Budget");
        countryList.add(country);
        country = new Country("Open System");
        countryList.add(country);
        country = new Country("Ozone");
        countryList.add(country);
        country = new Country("Ozone Layer");
        countryList.add(country);
        country = new Country("Radiant Energy");
        countryList.add(country);
        country = new Country("Radiation");
        countryList.add(country);
        country = new Country("Reflect");
        countryList.add(country);
        country = new Country("Scientific Evidence");
        countryList.add(country);
        country = new Country("Solar Energy");
        countryList.add(country);
        country = new Country("Solstice");
        countryList.add(country);
        country = new Country("Specific Heat Capacity (c)");
        countryList.add(country);
        country = new Country("Stratosphere");
        countryList.add(country);
        country = new Country("Sustainable Development");
        countryList.add(country);
        country = new Country("Thermal Energy");
        countryList.add(country);
        country = new Country("Thermal Energy Transfer");
        countryList.add(country);
        country = new Country("Thermosphere");
        countryList.add(country);
        country = new Country("Troposphere");
        countryList.add(country);
        country = new Country("Weather");
        countryList.add(country);
        country = new Country("Wind");
        countryList.add(country);

        continent = new Continent("Geology", countryList);
        for(int i = 0; i < countryList.size(); i++){
            geology.add(countryList.get(i).getName());
        }
        continentList.add(continent);


        countryList = new ArrayList<Country>();
        country = new Country("Acceleration");
        countryList.add(country);
        country = new Country("Average Speed");
        countryList.add(country);
        country = new Country("Chemical Potential Energy");
        countryList.add(country);
        country = new Country("Chemical Energy");
        countryList.add(country);
        country = new Country("Displacement");
        countryList.add(country);
        country = new Country("Distance Traveled");
        countryList.add(country);
        country = new Country("Efficiency");
        countryList.add(country);
        country = new Country("Elastic Potential Energy");
        countryList.add(country);
        country = new Country("Electrical Energy");
        countryList.add(country);
        country = new Country("Energy");
        countryList.add(country);
        country = new Country("Energy Conversion");
        countryList.add(country);
        country = new Country("First Law Of Thermodynamics");
        countryList.add(country);
        country = new Country("Force");
        countryList.add(country);
        country = new Country("Fuel Cell");
        countryList.add(country);
        country = new Country("Gravitational Potential Energy");
        countryList.add(country);
        country = new Country("Heat");
        countryList.add(country);
        country = new Country("Heat Engine");
        countryList.add(country);
        country = new Country("Heat Input");
        countryList.add(country);
        country = new Country("Heat Output");
        countryList.add(country);
        country = new Country("Internal Combustion Engine");
        countryList.add(country);
        country = new Country("Joule");
        countryList.add(country);
        country = new Country("Kinetic Energy");
        countryList.add(country);
        country = new Country("Law Of Conservation Of Energy");
        countryList.add(country);
        country = new Country("Matter");
        countryList.add(country);
        country = new Country("Mechanical Energy");
        countryList.add(country);
        country = new Country("Motion");
        countryList.add(country);
        country = new Country("Nuclear Energy");
        countryList.add(country);
        country = new Country("Percent Efficiency");
        countryList.add(country);
        country = new Country("Potential Energy");
        countryList.add(country);
        country = new Country("Scalar Quantity");
        countryList.add(country);
        country = new Country("Second Law Of Thermodynamics");
        countryList.add(country);
        country = new Country("Solar Energy");
        countryList.add(country);
        country = new Country("Surroundings");
        countryList.add(country);
        country = new Country("System");
        countryList.add(country);
        country = new Country("Thermal Power Station");
        countryList.add(country);
        country = new Country("Thermodynamics");
        countryList.add(country);
        country = new Country("Uniform Motion");
        countryList.add(country);
        country = new Country("Useful Energy");
        countryList.add(country);
        country = new Country("Vector Quantity");
        countryList.add(country);
        country = new Country("Velocity");
        countryList.add(country);
        country = new Country("Wasted Energy");
        countryList.add(country);
        country = new Country("Work");
        countryList.add(country);
        country = new Country("Work Input");
        countryList.add(country);

        continent = new Continent("Physics", countryList);
        for(int i = 0; i < countryList.size(); i++){
            physics.add(countryList.get(i).getName());
        }
        continentList.add(continent);

        countryList = new ArrayList<Country>();
        country = new Country("C++");
        countryList.add(country);
        country = new Country("HTML");
        countryList.add(country);
        country = new Country("Java");
        countryList.add(country);
        country = new Country("JavaScript");
        countryList.add(country);
        country = new Country("Python");
        countryList.add(country);
        country = new Country("SQL");
        countryList.add(country);
        country = new Country("Swift");
        countryList.add(country);

        continent = new Continent("Computer Science", countryList);
        for(int i = 0; i < countryList.size(); i++){
            computerScience.add(countryList.get(i).getName());
        }
        continentList.add(continent);

    }

    @Override
    public boolean onClose() {
        // TODO Auto-generated method stub
        listAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
       countryList = new ArrayList<Country>();
       continentList = new ArrayList<Continent>();
       displayList();

    }


    @Override
    public boolean onQueryTextChange(String newText) {
        // TODO Auto-generated method stub
        listAdapter.filterData(newText);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // TODO Auto-generated method stub
        listAdapter.filterData(query);
        expandAll();
        return false;
    }


}




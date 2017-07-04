package rodrigo.com.poc1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;

import android.support.test.espresso.core.deps.guava.collect.Lists;

import github.chenupt.springindicator.SpringIndicator;
import github.chenupt.springindicator.TabClickListener;
import rodrigo.com.poc1.Fragment.QuestionFragment;
import rodrigo.com.poc1.Viewpager.NonSwipeableViewPager;
import rodrigo.com.poc1.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends FragmentActivity {
    NonSwipeableViewPager pager;

    final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;
    private DatabaseReference myrefTest;

    private List<Fragment> fList;
    private List<String> title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myrefTest = FirebaseDatabase.getInstance().getReference().child("Questionários").child("1");
        getFragments();
        settings = getSharedPreferences(PREFS_NAME, 0);


        pager = (NonSwipeableViewPager) findViewById(R.id.view_pager);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        PagerModelManager manager = new PagerModelManager();
        manager.addCommonFragment(fList, title);


        ModelPagerAdapter adapter = new ModelPagerAdapter(getSupportFragmentManager(), manager);
        pager.setAdapter(adapter);


    }

    @Override
    public void onBackPressed() {

    }

    private List<Fragment> getFragments() {
        fList = new ArrayList<Fragment>();
        title = new ArrayList<String>();
        fList.add(QuestionFragment.newInstance("2", "2", "2", "1", "3", "3"));
        title.add("");
        getAllQuestions();
        return fList;
    }

    private List<String> getTitles() {
        return Lists.newArrayList("1", "2", "3");
    }


    public void swipeFragment() {

        pager.setCurrentItem(pager.getCurrentItem() + 1);

    }

    public int getFragment() {
        return pager.getCurrentItem();

    }


    public void getAllQuestions() {

        myrefTest.addListenerForSingleValueEvent(new ValueEventListener() {
            int cont = 0;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Code
                DataSnapshot question = dataSnapshot;

                String questionFirebase = "";
                List<String> answersFirebase = new ArrayList<String>();


                fList.clear();
                title.clear();

                for (DataSnapshot election : question.getChildren()) {
                    questionFirebase = election.getKey();
                    cont++;
                    for (DataSnapshot answers : election.getChildren()) {
                        answersFirebase.add(answers.getKey());
                    }
                    fList.add(QuestionFragment.newInstance(questionFirebase, answersFirebase.get(0), answersFirebase.get(1), answersFirebase.get(2), answersFirebase.get(3), answersFirebase.get(4)));
                    answersFirebase.clear();
                }



                for(int i=1; i<=cont; i++){
                    title.add(Integer.toString(i));
                }


                pager = (NonSwipeableViewPager) findViewById(R.id.view_pager);

                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);

                PagerModelManager manager = new PagerModelManager();
                manager.addCommonFragment(fList, title);


                ModelPagerAdapter adapter = new ModelPagerAdapter(getSupportFragmentManager(), manager);
                pager.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Code
            }


        });
    }





}


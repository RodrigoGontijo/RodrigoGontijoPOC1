package rodrigo.com.poc1.Fragment;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import rodrigo.com.poc1.QuestionActivity;
import rodrigo.com.poc1.R;

/**
 * Created by Rodrigo on 28/06/2017.
 */

public class QuestionFragment extends Fragment {
    private Button openButton;
    private Button confirmationCloseButton;
    private Button yesRenew;
    private Button noRenew;
    private BroadcastReceiver udpMessages;

    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

    public static final String EXTRA_MESSAGE1 = "EXTRA_MESSAGE_1";
    public static final String EXTRA_MESSAGE2 = "EXTRA_MESSAGE_2";
    public static final String EXTRA_MESSAGE3 = "EXTRA_MESSAGE_3";
    public static final String EXTRA_MESSAGE4 = "EXTRA_MESSAGE_4";
    public static final String EXTRA_MESSAGE5 = "EXTRA_MESSAGE_5";

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private TextView text;

    String message;

    String message1;
    String message2;
    String message3;
    String message4;
    String message5;

    private String android_id;

    private DatabaseReference myRef;


    public static final QuestionFragment newInstance(String message, String message1, String message2, String message3, String message4, String message5) {
        QuestionFragment f = new QuestionFragment();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_MESSAGE, message);

        bdl.putString(EXTRA_MESSAGE1, message1);
        bdl.putString(EXTRA_MESSAGE2, message2);
        bdl.putString(EXTRA_MESSAGE3, message3);
        bdl.putString(EXTRA_MESSAGE4, message4);
        bdl.putString(EXTRA_MESSAGE5, message5);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        android_id = Settings.Secure.getString(getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        message = getArguments().getString(EXTRA_MESSAGE);
        myRef = FirebaseDatabase.getInstance().getReference();

        message1 = getArguments().getString(EXTRA_MESSAGE1);
        message2 = getArguments().getString(EXTRA_MESSAGE2);
        message3 = getArguments().getString(EXTRA_MESSAGE3);
        message4 = getArguments().getString(EXTRA_MESSAGE4);
        message5 = getArguments().getString(EXTRA_MESSAGE5);

        View v = inflater.inflate(R.layout.fragment_question, container, false);

        text = (TextView) v.findViewById(R.id.question_fragment);
        button1 = (Button) v.findViewById(R.id.button_1);
        button2 = (Button) v.findViewById(R.id.button_2);
        button3 = (Button) v.findViewById(R.id.button_3);
        button4 = (Button) v.findViewById(R.id.button_4);
        button5 = (Button) v.findViewById(R.id.button_5);


        text.setText(message);

        button1.setText(message1);
        button2.setText(message2);
        button3.setText(message3);
        button4.setText(message4);
        button5.setText(message5);

        setListeners();
        return v;
    }

    private void setListeners() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(getSharedPrefs("sesssion_pin")).child(getSharedPrefs("questionsId")).child(message).child(android_id.substring(0,5)).setValue(message1);
                ((QuestionActivity) getActivity()).swipeFragment();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(getSharedPrefs("sesssion_pin")).child(getSharedPrefs("questionsId")).child(message).child(android_id.substring(0,5)).setValue(message1);
                ((QuestionActivity) getActivity()).swipeFragment();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(getSharedPrefs("sesssion_pin")).child(getSharedPrefs("questionsId")).child(message).child(android_id.substring(0,5)).setValue(message2);
                ((QuestionActivity) getActivity()).swipeFragment();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(getSharedPrefs("sesssion_pin")).child(getSharedPrefs("questionsId")).child(message).child(android_id.substring(0,5)).setValue(message3);
                ((QuestionActivity) getActivity()).swipeFragment();
            }
        });


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(getSharedPrefs("sesssion_pin")).child(getSharedPrefs("questionsId")).child(message).child(android_id.substring(0,5)).setValue(message4);
                ((QuestionActivity) getActivity()).swipeFragment();
            }
        });


        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(getSharedPrefs("sesssion_pin")).child(getSharedPrefs("questionsId")).child(message).child(android_id.substring(0,5)).setValue(message5);
                ((QuestionActivity) getActivity()).swipeFragment();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    public String getSharedPrefs(String key) {
        SharedPreferences prefs = getContext().getSharedPreferences("PIN_PREFS", getContext().MODE_PRIVATE);
        String restoredText = prefs.getString(key, null);
        return restoredText;
    }

}



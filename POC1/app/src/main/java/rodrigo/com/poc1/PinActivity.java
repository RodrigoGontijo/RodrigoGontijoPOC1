package rodrigo.com.poc1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Rodrigo on 24/06/2017.
 */

public class PinActivity extends AppCompatActivity {
    private Button startButton;
    private EditText code;
    private String codeUserText;
    private String[] textCode;
    private DatabaseReference myrefTest;
    private DatabaseReference myrefRetrieve;
    private DatabaseReference myRef;
    private Boolean retorno = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        retorno = false;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference();


        myrefRetrieve = FirebaseDatabase.getInstance().getReference().child("Questionários");
        myrefTest = FirebaseDatabase.getInstance().getReference().child("Questionários").child("1");

        startButton = (Button) findViewById(R.id.start_button);
        code = (EditText) findViewById(R.id.edit_enter_code);


        myRef.child("Questionários").child("1").child("Qual a cor do cavalo branco de napoleão? - 2").child("Branco").setValue("true");
        myRef.child("Questionários").child("1").child("Qual a cor do cavalo branco de napoleão? - 2").child("Azul").setValue("false");
        myRef.child("Questionários").child("1").child("Qual a cor do cavalo branco de napoleão? - 2").child("Preto").setValue("false");
        myRef.child("Questionários").child("1").child("Qual a cor do cavalo branco de napoleão? - 2").child("Roxo").setValue("false");
        myRef.child("Questionários").child("1").child("Qual a cor do cavalo branco de napoleão? - 2").child("Amarelo").setValue("false");

        myRef.child("Sessão1").child("1").child("Qual a cor do cavalo branco de napoleão?").child("ID do celular1").setValue("Branco");
        myRef.child("Sessão1").child("1").child("Qual a cor do cavalo branco de napoleão?").child("ID do celular2").setValue("Branco");
        myRef.child("Sessão1").child("1").child("Qual a cor do cavalo branco de napoleão?").child("ID do celular3").setValue("Preto");


        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkQuestionExists();
                retorno = false;
                textCode = code.getText().toString().split("-");
                if (retorno) {
                    myRef.child(textCode[0]).child(textCode[1]);

                }
            }
        });
    }

    public boolean checkQuestionExists() {

        myrefRetrieve.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Code
                DataSnapshot question = dataSnapshot;

                for (DataSnapshot election : question.getChildren()) {
                    if (election.getKey().compareTo(textCode[1]) == 0) {
                        Toast.makeText(getApplicationContext(), "gol", Toast.LENGTH_LONG).show();
                        savePrefs("sesssion_pin", textCode[0]);
                        savePrefs("questionsId", textCode[1]);
                        Intent i = new Intent(getBaseContext(), QuestionActivity.class);
                        startActivity(i);
                        retorno = true;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Code
            }


        });


        return retorno;
    }


    public void savePrefs(String key, String value){
        SharedPreferences.Editor editor = getSharedPreferences("PIN_PREFS", MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }
}

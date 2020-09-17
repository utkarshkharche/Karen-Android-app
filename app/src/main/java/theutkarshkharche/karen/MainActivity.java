package theutkarshkharche.karen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textViewIP;
    Button buttonIP,buttonSend;
    EditText editTextIP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText)findViewById(R.id.editText);
        editTextIP=(EditText)findViewById(R.id.editTextIP);
        textViewIP=(TextView)findViewById(R.id.textViewIP);
        buttonIP=(Button)findViewById(R.id.buttonIP);
        buttonSend=(Button)findViewById(R.id.button);
        buttonIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg=editTextIP.getText().toString();
                SharedPreferences shrd=getSharedPreferences("Karen",MODE_PRIVATE);
                SharedPreferences.Editor editor =shrd.edit();

                editor.putString("str",msg);
                editor.apply();
                textViewIP.setText(msg);
            }
        });

        SharedPreferences getShared = getSharedPreferences("Karen",MODE_PRIVATE);
        final String value =getShared.getString("str","Saved IP will show up here");
        textViewIP.setText(value);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageSender MessageSender=new messageSender();
                MessageSender.execute(editText.getText().toString(),value);
            }
        });
    }

    public void send(View v)
    {
        messageSender MessageSender=new messageSender();
        MessageSender.execute(editText.getText().toString());
    }
}
package pl.jlukas.myowndiamond;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static android.widget.Toast.LENGTH_LONG;

public class IntroductionActivity extends AppCompatActivity {

    private LinearLayout one;
    private LinearLayout two;
    private LinearLayout three;

    private EditText editText;

    private CheckBox black;
    private CheckBox red;
    private CheckBox green;

    private Button start;
    private Button next;
    private Button back;

    private Button next1;
    private Button back1;

    private Button next2;
    private Button back2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);

        editText = findViewById(R.id.nameDiamond);

        black = findViewById(R.id.blackCheck);
        green = findViewById(R.id.greenCheck);
        red = findViewById(R.id.redCheck);

        start = findViewById(R.id.startButton);
        back = findViewById(R.id.back);
        next = findViewById(R.id.next);

        back1 = findViewById(R.id.twoback);
        next1 = findViewById(R.id.twonext);

        back2 = findViewById(R.id.threeback);
        next2 = findViewById(R.id.threenext);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear("next");
            }
        });

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear("next");
            }
        });

        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear("next");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear("back");
            }
        });

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear("back");
            }
        });

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear("back");
            }
        });

        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(0);
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(1);
            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(2);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder accept = new AlertDialog.Builder(IntroductionActivity.this);

                accept.setTitle("Are you sure?");
                accept.setMessage("All settings, if so, click ok and if you want to make changes click cancel");

                accept.setPositiveButton("Okey", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        checker();
                    }
                });

                accept.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = accept.create();
                alertDialog.show();
            }
        });
    }

    private void intent() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    private  void checker() {
        if((editText.length() > 2) && (black.isChecked() || red.isChecked() || green.isChecked())) {
            intent();
        } else if(editText.length() < 3) {
            Toast.makeText(IntroductionActivity.this, "The minimum diamond name length is 3", LENGTH_LONG).show();
        } else {
            Toast.makeText(IntroductionActivity.this, "Complete the missing space", LENGTH_LONG).show();
        }
    }

    private void linear(String string) {
        if(string == "next") {
            if(one.getVisibility() == View.VISIBLE) {
                one.setVisibility(View.INVISIBLE);
                two.setVisibility(View.VISIBLE);
            } else if(two.getVisibility() == View.VISIBLE) {
                two.setVisibility(View.INVISIBLE);
                three.setVisibility(View.VISIBLE);
            }
        } else if(string == "back") {
            if(two.getVisibility() == View.VISIBLE) {
                two.setVisibility(View.INVISIBLE);
                one.setVisibility(View.VISIBLE);
            } else if(three.getVisibility() == View.VISIBLE) {
                three.setVisibility(View.INVISIBLE);
                two.setVisibility(View.VISIBLE);
            }
        }
    }

    private void check(int i) {
        switch(i) {
            case 0:
                red.setChecked(false);
                green.setChecked(false);
                break;
            case 1:
                black.setChecked(false);
                red.setChecked(false);
                break;
            case 2:
                black.setChecked(false);
                green.setChecked(false);
                break;
        }
    }
}

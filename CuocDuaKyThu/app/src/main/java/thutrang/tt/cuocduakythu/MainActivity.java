package thutrang.tt.cuocduakythu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtDiem;
    ImageButton ibtnpLay;
    CheckBox cbOne, cbTwo, cbThree;
    SeekBar sbOne, sbTwo, sbThree;
    int soDiem=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        sbOne.setEnabled(false);
        sbTwo.setEnabled(false);
        sbThree.setEnabled(false);

        txtDiem.setText(soDiem+ "");
        CountDownTimer countDownTimer = new CountDownTimer(60000,300) {
            @Override
            public void onTick(long l) {
                int number = 10;
                Random random = new Random();
                int one = random.nextInt(number);
                int two = random.nextInt(number);
                int three = random.nextInt(number);

                //kiem tra win one
                if (sbOne.getProgress() >= sbOne.getMax()) {
                    this.cancel();
                    Toast.makeText(MainActivity.this, "ONE WIN", Toast.LENGTH_SHORT).show();
                    ibtnpLay.setVisibility(View.VISIBLE);
                    //Kiem tra dat cuoc
                    if (cbOne.isChecked()) {
                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "Bạn đoán chính xác", Toast.LENGTH_SHORT).show();
                    } else {
                        soDiem -= 5;
                        Toast.makeText(MainActivity.this, "Bạn đoán sai rồi", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem + "");
                    EnabledCheckBox();
                }

                //kiem tra win two
                if (sbTwo.getProgress() >= sbTwo.getMax()) {
                    this.cancel();
                    Toast.makeText(MainActivity.this, "TWO WIN", Toast.LENGTH_SHORT).show();
                    ibtnpLay.setVisibility(View.VISIBLE);
                    //Kiem tra dat cuoc
                    if (cbTwo.isChecked()) {
                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "Bạn đoán chính xác", Toast.LENGTH_SHORT).show();
                    } else {
                        soDiem -= 5;
                        Toast.makeText(MainActivity.this, "Bạn đoán sai rồi", Toast.LENGTH_SHORT).show();

                    }
                    txtDiem.setText(soDiem + "");
                    EnabledCheckBox();
                }

                //kiem tra win three
                if(sbThree.getProgress() >=sbThree.getMax()){
                    this.cancel();
                    Toast.makeText(MainActivity.this," THREE WIN",Toast.LENGTH_SHORT).show();
                    ibtnpLay.setVisibility(View.VISIBLE);
                    //Kiem tra dat cuoc
                    if(cbThree.isChecked()){
                        soDiem +=10;
                        Toast.makeText(MainActivity.this,"Bạn đoán chính xác",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        soDiem -= 5;
                        Toast.makeText(MainActivity.this, "Bạn đoán sai rồi", Toast.LENGTH_SHORT).show();

                    }
                    txtDiem.setText(soDiem+ "");
                    EnabledCheckBox();
                }

                sbOne.setProgress(sbOne.getProgress() +one);
                sbTwo.setProgress(sbTwo.getProgress() +two);
                sbThree.setProgress(sbThree.getProgress() +three);
            }
            @Override
            public void onFinish() {

            }
        };
        ibtnpLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbOne.isChecked() || cbTwo.isChecked() || cbThree.isChecked()){
                    sbOne.setProgress(0);
                    sbTwo.setProgress(0);
                    sbThree.setProgress(0);
                    ibtnpLay.setVisibility(View.INVISIBLE);
                    CountDownTimer start = countDownTimer.start();
                    DisabledCheckBox();
                }
                else{
                   Toast.makeText(MainActivity.this,"Vui lòng đặt cược trước khi chơi!",Toast.LENGTH_SHORT).show();
                }
            }
        });
       cbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if(b)
               {
                   //bo check 2,3
                   cbTwo.setChecked(false);
                   cbThree.setChecked(false);
               }
           }
       });
       cbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               //bo 1,3
               cbOne.setChecked(false);
               cbThree.setChecked(false);
           }
       });
       cbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               //BO 1,2
                cbOne.setChecked(false);
                cbTwo.setChecked(false);
           }
       });
    }
    private void EnabledCheckBox(){
        cbOne.setEnabled(true);
        cbTwo.setEnabled(true);
        cbThree.setEnabled(true);
    }
    private void DisabledCheckBox(){
        cbOne.setEnabled(false);
        cbTwo.setEnabled(false);
        cbThree.setEnabled(false);

    }
    private void AnhXa()
    {
        txtDiem = (TextView) findViewById(R.id.textviewDiemSo);
        ibtnpLay = (ImageButton) findViewById(R.id.buttonPlay);
        cbOne = (CheckBox) findViewById(R.id.checkbox1);
        cbTwo = (CheckBox) findViewById(R.id.checkbox2);
        cbThree = (CheckBox) findViewById(R.id.checkbox3);
        sbOne = (SeekBar) findViewById(R.id.seakbar1);
        sbTwo = (SeekBar) findViewById(R.id.seakbar2);
        sbThree = (SeekBar) findViewById(R.id.seakbar3);
    }
}


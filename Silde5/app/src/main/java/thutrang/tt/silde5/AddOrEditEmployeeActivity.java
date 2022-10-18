package thutrang.tt.silde5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import thutrang.tt.silde5.model.Employee;
import thutrang.tt.silde5.sqlite.EmplyoyeeDao;

public class AddOrEditEmployeeActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etEmployeeID,etName,etSalary;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit_employee2);
        etEmployeeID =findViewById(R.id.etEmployeeid);
        etName = findViewById(R.id.etName);
        etSalary = findViewById(R.id.etSalary);
        btnSave= findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        findViewById(R.id.btnListEmployees).setOnClickListener(this);
        readEmployee();
    }
    private void readEmployee(){
        Intent intent = getIntent();
        Bundle bundle =intent.getBundleExtra("data");
        if(bundle == null){
            return;
        }
        String id =bundle.getString("id");
        EmplyoyeeDao dao = new EmplyoyeeDao(this);
        Employee emp = dao.getById(id);
        etEmployeeID.setText(emp.getId());
        etName.setText(emp.getName());
        etSalary.setText(""+emp.getSalary());
         btnSave.setText("Update");
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave:
                EmplyoyeeDao dao = new EmplyoyeeDao(this);
                Employee emp = new Employee();
                emp.setId(etEmployeeID.getText().toString());
                emp.setName(etName.getText().toString());
                emp.setSalary(Float.parseFloat(etSalary.getText().toString()));
                if(btnSave.getText().equals("Save")){
                    dao.insert(emp);
                }
                else
                {
                    dao.update(emp);
                }
                Toast.makeText(this,"New employee has been saved!",
                    Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
package ro.pub.cs.systems.eim.Colocviu1_2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Colocviu1_2MainActivity extends AppCompatActivity {

    private Button addButton;
    private Button computeButton;
    private TextView allTerms;
    private EditText addTerm;

    private ActivityResultLauncher<Intent> activityResultLauncher;

    class GeneralOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if(id == R.id.add_button) {
                Log.d("Add button", addTerm.getText().toString());

                if(addTerm.getText().toString().isEmpty()) {
                    return;
                }

                String newTerm = addTerm.getText().toString();
                int newTermNumber;

                try {
                    newTermNumber = Integer.parseInt(newTerm);
                } catch (NumberFormatException nfe) {
                    return;
                }

                if(allTerms.getText().toString().isEmpty()) {
                    allTerms.setText(addTerm.getText().toString());
                } else {
                    allTerms.setText(allTerms.getText().toString() + "+"  + addTerm.getText().toString());
                }

                addTerm.setText("");

            } else if(id == R.id.compute_button) {
                Intent goToSecondaryActivtyIntent = new Intent(Colocviu1_2MainActivity.this, Colocviu1_2SecondaryActivity.class);
                Log.d("MainActivity", allTerms.getText().toString());
                goToSecondaryActivtyIntent.putExtra(Constants.ALL_TERMS_DATA, allTerms.getText().toString());
                activityResultLauncher.launch(goToSecondaryActivtyIntent);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_2_main);

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                if(result.getData() != null) {
                    int sum = result.getData().getIntExtra(Constants.TERMS_SUM, 0);
                    Toast.makeText(this, "The activity returned with OK, sum = " + sum, Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "The activity returned with CANCEL", Toast.LENGTH_LONG).show();
            }
        });

        addButton = findViewById(R.id.add_button);
        computeButton = findViewById(R.id.compute_button);
        allTerms = findViewById(R.id.all_terms);
        addTerm = findViewById(R.id.next_term);

        addTerm.setText("");
        allTerms.setText("");

        addButton.setOnClickListener(new GeneralOnClickListener());
        computeButton.setOnClickListener(new GeneralOnClickListener());

    }
}
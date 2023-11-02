package screens;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.as1.R;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;


public class ChatScreen extends AppCompatActivity {

    private EditText messageEditText;
    private Button sendButton;
    private ScrollView chatScrollView;
    private TextView chatTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);
        chatScrollView = findViewById(R.id.chatScrollView);
        chatTextView = findViewById(R.id.chatTextView);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString();

                chatTextView.append("You: " + message + "\n");

                messageEditText.setText("");

                chatScrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }


}

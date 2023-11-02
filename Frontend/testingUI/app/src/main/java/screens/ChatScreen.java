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

        // Initialize UI elements
        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);
        chatScrollView = findViewById(R.id.chatScrollView);
        chatTextView = findViewById(R.id.chatTextView);

        // Set a click listener for the Send button
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the EditText
                String message = messageEditText.getText().toString();

                // Append the message to the chatTextView
                chatTextView.append("You: " + message + "\n");

                // Clear the EditText
                messageEditText.setText("");

                // Scroll the ScrollView to the bottom to show the new message
                chatScrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }


}

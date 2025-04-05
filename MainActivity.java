
package com.devdroid.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Declare button variables before any logic
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    String currentPlayer = "X";  // X always starts

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view
        setContentView(R.layout.activity_main);

        // Handling edge-to-edge and system bar insets
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize button variables
        init();
    }

    private void init() {
        // Initialize all buttons
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
    }

    public void check(View view) {
        Button clickedButton = (Button) view;
        if (clickedButton.getText().toString().equals("")) {
            clickedButton.setText(currentPlayer);
            if (checkForWinner()) {
                Toast.makeText(this, currentPlayer + " Wins!", Toast.LENGTH_SHORT).show();
                resetGame();
            } else {
                currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";  // Switch player
            }
        }
    }

    private boolean checkForWinner() {
        String[][] board = {
                {btn1.getText().toString(), btn2.getText().toString(), btn3.getText().toString()},
                {btn4.getText().toString(), btn5.getText().toString(), btn6.getText().toString()},
                {btn7.getText().toString(), btn8.getText().toString(), btn9.getText().toString()}
        };

        // Check rows, columns, and diagonals for a winner
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].equals("")) {
                return true;
            }
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].equals("")) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals("")) {
            return true;
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void resetGame() {
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        currentPlayer = "X";  // Reset to player X starting
    }
}

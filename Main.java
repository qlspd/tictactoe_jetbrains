package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //setting the values
        char[][] arr = new char[3][3];
        String word = "_________";

        //drawing the table
        draw(arr, word);

        //interactive if no winners
        while (calculate(arr) == ' ') {
            word = interactive(arr, word);
        }
        System.out.println(calculate(arr) == 'd' ? "Draw" : calculate(arr) + " wins");

    }

    static void draw(char[][] arr, String word) {
        int wordLetter = 0;
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = word.charAt(wordLetter);
                if (word.length() != 9
                        || arr[i][j] != 'X'
                        && arr[i][j] != 'O'
                        && arr[i][j] != '_') {
                    System.out.println("error");
                    break;
                }
                if (wordLetter % 3 == 0) {
                    System.out.print("| ");
                }
                System.out.print(arr[i][j] == '_' ? "  " : arr[i][j] + " ");
                if ((wordLetter + 1) % 3 == 0) {
                    System.out.println("|");
                }
                wordLetter++;
            }
        }
        System.out.println("---------");
    }

    static char calculate(char[][] arr) {
        char winner = ' ';
        boolean stop = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == '_') {
                    winner = ' ';
                    stop = true;
                    break;
                } else {
                    winner = 'd';
                }
            }
            if (stop) {
                break;
            }
        }
        for (int i = 0; i < 2; i++) {
            char set = i == 0 ? 'X' : 'O';
            if (arr[0][0] == set && arr[0][1] == set && arr[0][2] == set
                    || arr[1][0] == set && arr[1][1] == set && arr[1][2] == set
                    || arr[2][0] == set && arr[2][1] == set && arr[2][2] == set
                    || arr[0][0] == set && arr[1][1] == set && arr[2][2] == set
                    || arr[0][2] == set && arr[1][1] == set && arr[2][0] == set
                    || arr[0][0] == set && arr[1][0] == set && arr[2][0] == set
                    || arr[0][1] == set && arr[1][1] == set && arr[2][1] == set
                    || arr[0][2] == set && arr[1][2] == set && arr[2][2] == set) {
                winner = set;
                break;
            }
        }
        return winner;
    }

    static String interactive(char[][] arr, String word) {
        Scanner scanner = new Scanner(System.in);
        int h = 0;
        int w = 0;
        int sum = 0;
        boolean error = false;

        for (int i = 0; i < 9; i++) {
            sum += word.charAt(i) == '_' ? 0 : 1;
        }
        char set = sum % 2 == 0 ? 'X' : 'O';
        System.out.println("Enter the coordinates: ");
        try {
            h = scanner.nextInt();
            w = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            error = true;
        }
        if (!error) {
            if (h < 1 || h > 3 || w < 1 || w > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (arr[h - 1][w - 1] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                int formula = h * 3 - (3 - w);
                word = word.substring(0, formula - 1) + set + word.substring(formula);
                draw(arr, word);
            }
        }
        return word;
    }
}
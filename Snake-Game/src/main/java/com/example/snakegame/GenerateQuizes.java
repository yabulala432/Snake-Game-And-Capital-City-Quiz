package com.example.snakegame;

import java.util.Random;

public class GenerateQuizes {
    private Random random = new Random();
    public String[] choices = {"", "", "", "", "", "", ""};
    public String question, answer;
    private int[] indexArray;
    private int choiceIndex, answerIndex;

    public GenerateQuizes(Files files) {
        answerIndex = random.nextInt(0, files.allInfo.size());
        question = files.allInfo.get(answerIndex).substring(0, files.allInfo.get(answerIndex).indexOf(":"));
        answer = files.allInfo.get(answerIndex).substring(files.allInfo.get(answerIndex).indexOf(":") + 1);

        indexArray = new int[]{-1, -1, -1, -1, -1};
        for (int i = 0; i < 5; i++) {
            choiceIndex = random.nextInt(0, files.allInfo.size());
            for (int ind : indexArray) {
                while (ind == choiceIndex || ind == answerIndex) {
                    choiceIndex = random.nextInt(0, files.allInfo.size());
                }
            }
            indexArray[i] = choiceIndex;
            choices[i] = files.allInfo.get(choiceIndex).substring(files.allInfo.get(choiceIndex).indexOf(":") + 1);
        }
        int randPush = random.nextInt(0, 6);
        pushOnRandomPlace(choices, answer, randPush);
    }

    private void pushOnRandomPlace(String[] arr, String value, int place) {
        // I'm going to generate only 5 choices and add the 1 with the exact answer Later;
        //I could do it by generating 6 choices and replace the one with the correct val but...;
        for (int i = arr.length - 2; i >= place; i--) {
            arr[i + 1] = arr[i];
        }
        arr[place] = value;
    }
}

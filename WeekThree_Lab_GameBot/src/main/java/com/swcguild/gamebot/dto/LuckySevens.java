package com.swcguild.gamebot.dto;

import java.util.Random;

/**
 *
 * @author apprentice
 */
public class LuckySevens implements Game {

    @Override
    public String getGameName() {
        return "Lucky 7's";
    }

    @Override
    public void run() {
        Random r = new Random();

        int money = 0;
        int rollCount = 0;
        int maxMoney;
        int rollsAtMax = 0;
        int di1;
        int di2;

        // Prompt user for bet amount
        io.writeString("Let's play \"Lucky 7's\"!!!");
        money = io.readInt("How many dollars do you have to bet? ");
        maxMoney = money;

        while (money > 0) {
            // Roll dice
            di1 = 1 + r.nextInt(7);
            di2 = 1 + r.nextInt(7);
            rollCount++;

//            System.out.println("Di 1 is: " + di1);
//            System.out.println("Di 2 is: " + di2);
//            System.out.println("Dice total: " + (di1 + di2));
//            System.out.println("Roll count: " + rollCount);
            // Check dice total and adjust remaining money amount
            if (di1 + di2 == 7) {
                money += 4;
            } else {
                money -= 1;
            }

            // Record max amount of money
            if (money > maxMoney) {
                maxMoney = money;

                // Record roll count when money total is at max
                rollsAtMax = rollCount;
            }
        }
        // System.out.println("Remaining money: " + money);
        io.writeString("Max Money: " + maxMoney);
        io.writeString("Rolls at Max Money: " + rollsAtMax);

        io.writeString("");
    }

}

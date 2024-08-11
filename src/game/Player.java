package game;


import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;
public class Player {
    public static Logger logger = Main.logger;
    private int totalSum, betTotal;
    private final int[] money = new int[10];
    private final int[] bet = new int[10];

    public Player() {}

    void betReset(){
        Arrays.fill(bet, 0);
        setBetTotal();
    }

    void setTotalSum(int num){
        this.totalSum = num;
        Arrays.fill(this.money, 0);
        setMoney(num);
    }

    void setBetTotal(){
        this.betTotal = 0;
    }

    void addbetTotal(int num){
        this.betTotal += num;
    }


    int getTotalSum(){
        return this.totalSum;
    }

    int getBetTotal(){
        return betTotal;
    }

    int getMoney(int chip){
        switch (chip) {
            case 1 -> {
                return this.money[0];
            }
            case 5 -> {
                return this.money[1];
            }
            case 10 -> {
                return this.money[2];
            }
            case 25 -> {
                return this.money[3];
            }
            case 100 -> {
                return this.money[4];
            }
            case 500 -> {
                return this.money[5];
            }
            case 1000 -> {
                return this.money[6];
            }
            case 5000 -> {
                return this.money[7];
            }
            case 25000 -> {
                return this.money[8];
            }
            case 100000 -> {
                return this.money[9];
            }
            default -> logger.info("Fetching non-existing chip");
        }
        return -1;
    }

    int getBet(int chip){
        switch (chip) {
            case 1 -> {
                return this.bet[0];
            }
            case 5 -> {
                return this.bet[1];
            }
            case 10 -> {
                return this.bet[2];
            }
            case 25 -> {
                return this.bet[3];
            }
            case 100 -> {
                return this.bet[4];
            }
            case 500 -> {
                return this.bet[5];
            }
            case 1000 -> {
                return this.bet[6];
            }
            case 5000 -> {
                return this.bet[7];
            }
            case 25000 -> {
                return this.bet[8];
            }
            case 100000 -> {
                return this.bet[9];
            }
            default -> logger.info("Fetching non-existing chip");
        }
        return -1;
    }

    int getMoneySum(){
        int sum = 0;
        for(int i = 0; i < money.length; i++){
            switch (i) {
                case 0 -> sum += money[0];
                case 1 -> sum += (5 * money[1]);
                case 2 -> sum += (10 * money[2]);
                case 3 -> sum += (25 *  money[3]);
                case 4 -> sum += (100 *  money[4]);
                case 5 -> sum += (500 *  money[5]);
                case 6 -> sum += (1000 *  money[6]);
                case 7 -> sum += (5000 *  money[7]);
                case 8 -> sum += (25000 *  money[8]);
                case 9 -> sum += (100000 *  money[9]);
                default -> logger.info("Summing non-existing chip");
            }
        }
        return sum;
    }

    void setMoney(int num){
        while(num > 0) {
            if (num / 100000 > 0) {
                this.money[9] += 1;
                num -= 100000;
            } else if (num / 25000 > 0) {
                this.money[8] += 1;
                num -= 25000;
            } else if (num / 5000 > 0) {
                this.money[7] += 1;
                num -= 5000;
            } else if (num / 1000 > 0) {
                this.money[6] += 1;
                num -= 1000;
            } else if (num / 500 > 0) {
                this.money[5] += 1;
                num -= 500;
            } else if (num / 100 > 0) {
                this.money[4] += 1;
                num -= 100;
            } else if (num / 25 > 0) {
                this.money[3] += 1;
                num -= 25;
            } else if (num / 10 > 0) {
                this.money[2] += 1;
                num -= 10;
            } else if (num / 5 > 0) {
                this.money[1] += 1;
                num -= 5;
            } else {
                this.money[0] += 1;
                num -= 1;
            }
        }
        System.out.println(Arrays.toString(this.money));
        Main.gui.updateChips();
        logger.info("Chips updated");
    }

    public void setBet(){
        for(Map.Entry<Integer, Integer> set: Main.betChips.entrySet()){
            switch (set.getKey()) {
                case 1 -> bet[0] = set.getValue();
                case 5 -> bet[1] = set.getValue();
                case 10 -> bet[2] = set.getValue();
                case 25 -> bet[3] = set.getValue();
                case 100 -> bet[4] = set.getValue();
                case 500 -> bet[5] = set.getValue();
                case 1000 -> bet[6] = set.getValue();
                case 5000 -> bet[7] = set.getValue();
                case 25000 -> bet[8] = set.getValue();
                case 100000 -> bet[9] = set.getValue();
            }
        }
    }

    void setIndividual(int value){
        switch (value) {
            case 1 -> this.money[0] -= 1;
            case 5 -> this.money[1] -= 1;
            case 10 -> this.money[2] -= 1;
            case 25 -> this.money[3] -= 1;
            case 100 -> this.money[4] -= 1;
            case 500 -> this.money[5] -= 1;
            case 1000 -> this.money[6] -= 1;
            case 5000 -> this.money[7] -= 1;
            case 25000 -> this.money[8] -= 1;
            case 100000 -> this.money[9] -= 1;
            default -> logger.info("setIndividual error");
        }
        Main.gui.updateChips();
        logger.info("Updated money");
    }
}

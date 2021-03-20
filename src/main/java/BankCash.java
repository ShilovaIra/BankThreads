import java.util.logging.Logger;

/**
 * Class for bank cash
 */
public class BankCash {

    private int amountMoney;
    private Logger logger = Logger.getLogger(String.valueOf(BankCash.class));

    public BankCash() {
    }

    public synchronized void depositMoney (int money) {
        this.amountMoney += money;
        logger.info("Deposit successful");
    }

    public synchronized void withdrawMoney (int money) {
        if (this.amountMoney - money < 0) {
            logger.warning("Withdraw failed. Not enough money in bank!");
        } else if (this.amountMoney - money >= 0) {
            this.amountMoney -= money;
            logger.info("Withdraw successful.");
        }

    }

    public BankCash(int amountMoney) {
        this.amountMoney = amountMoney;
    }

    public int getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(int amountMoney) {
        this.amountMoney = amountMoney;
    }
}

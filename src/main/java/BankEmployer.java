import java.util.LinkedList;
import java.util.logging.Logger;

public class BankEmployer extends Thread {

    private LinkedList<BankClient> queue;
    private BankCash bankCash;
    private final Logger logger = Logger.getLogger(String.valueOf(BankEmployer.class));

    public BankEmployer(LinkedList<BankClient> queue) {
        this.queue = queue;
        this.bankCash = new BankCash();
    }

    public synchronized void depositMoney (int money) {
        this.bankCash.depositMoney(money);
    }

    public synchronized void withdrawMoney (int money) {
        this.bankCash.withdrawMoney(money);

    }

    @Override
    public void run() {
        while (true) {
            try {
                performClientOperation();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sleep () throws InterruptedException {
        if (queue.isEmpty())
            wait();
    }


    private synchronized void performClientOperation () throws InterruptedException {
        sleep();
        BankClient bankClient = queue.getFirst();
        try {
            wait(bankClient.getService_time());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (bankClient.getClientOperation().equals(ClientOperation.withdraw)) {
            this.withdrawMoney(bankClient.getTransactionAmount());
            logger.info("Client perform withdraw operation. Amount:" + bankClient.getTransactionAmount());
        } else if (bankClient.getClientOperation().equals(ClientOperation.deposit)) {
            this.depositMoney(bankClient.getTransactionAmount());
            logger.info("Client perform deposit operation. Amount:" + bankClient.getTransactionAmount());
        }
        queue.removeFirst();
    }

    public LinkedList<BankClient> getQueue() {
        return queue;
    }

    public void setQueue(LinkedList<BankClient> queue) {
        this.queue = queue;
    }
}

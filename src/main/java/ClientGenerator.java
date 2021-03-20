import java.util.Random;

public class ClientGenerator extends Thread {

    private final int MAX_TRANSACTION_AMOUNT = 500;
    private final int MIN_TRANSACTION_AMOUNT = 100;

    private final int MIN_SERVICE_TIME = 1000;
    private final int MAX_SERVICE_TIME = 5000;

    private final Bank bank;

    public ClientGenerator(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        while(true) {
            BankClient bankClient = null;
            try {
                bankClient = generate();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bank.addClient(bankClient);
        }
    }

    public synchronized BankClient generate() throws InterruptedException {
        wait(2500);
        BankClient bankClient = new BankClient();
        Random random = new Random();

        int transactionAmount = random.nextInt(MAX_TRANSACTION_AMOUNT - MIN_TRANSACTION_AMOUNT) + MIN_TRANSACTION_AMOUNT;
        bankClient.setTransactionAmount(transactionAmount);

        int serviceTime = random.nextInt(MAX_SERVICE_TIME - MIN_SERVICE_TIME) + MIN_SERVICE_TIME;
        bankClient.setService_time(serviceTime);

        ClientOperation[] clientOperations = ClientOperation.values();
        ClientOperation clientOperation = clientOperations[random.nextInt(clientOperations.length)];
        bankClient.setClientOperation(clientOperation);
        return bankClient;
    }



}

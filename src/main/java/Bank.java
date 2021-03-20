import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Bank {

    private final int EMPLOYER_COUNT = 4;

    private final List<LinkedList<BankClient>> clientQueues = new ArrayList<>(EMPLOYER_COUNT);
    private final List<BankEmployer> bankEmployers = new ArrayList<>(EMPLOYER_COUNT);


    public Bank() {
        for (int i = 0; i < this.EMPLOYER_COUNT; i++) {
            clientQueues.add(new LinkedList<>());
        }

        for (LinkedList<BankClient> bankClients : clientQueues) {
            BankEmployer bankEmployer = new BankEmployer(bankClients);
            bankEmployers.add(bankEmployer);
        }

        for (BankEmployer bankEmployer: bankEmployers) {
            Thread thread = new Thread(bankEmployer);
            thread.start();
        }

        ClientGenerator clientGenerator = new ClientGenerator(this);
        Thread thread = new Thread(clientGenerator);
        thread.start();
    }

    public void addClient(BankClient bankClient) {
        int tinyQueueSize = 101;
        LinkedList<BankClient> tinyQueue = new LinkedList<>();
        int j = 0;
        for (int i = 0; i < clientQueues.size(); i++) {
            if (clientQueues.get(i).size() < tinyQueueSize) {
                tinyQueue = clientQueues.get(i);
                tinyQueueSize = tinyQueue.size();
                j = i;
            }
        }

        if (tinyQueue.isEmpty()){
            synchronized (bankEmployers.get(j)) {
                tinyQueue.add(bankClient);
                bankEmployers.get(j).notify();
            }
        }
        else
            tinyQueue.add(bankClient);
    }
}

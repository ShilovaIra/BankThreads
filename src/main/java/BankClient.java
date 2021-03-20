public class BankClient {
    private ClientOperation clientOperation;
    private int transactionAmount;
    private int service_time;

    public BankClient() {
    }

    public BankClient(ClientOperation clientOperation, int transactionAmount, int service_time) {
        this.clientOperation = clientOperation;
        this.transactionAmount = transactionAmount;
        this.service_time = service_time;
    }

    public ClientOperation getClientOperation() {
        return clientOperation;
    }

    public void setClientOperation(ClientOperation clientOperation) {
        this.clientOperation = clientOperation;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public int getService_time() {
        return service_time;
    }

    public void setService_time(int service_time) {
        this.service_time = service_time;
    }
}

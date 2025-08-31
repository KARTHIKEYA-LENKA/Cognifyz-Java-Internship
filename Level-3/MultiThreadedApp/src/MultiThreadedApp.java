public class MultiThreadedApp {
    static class BankAccount {
        private double balance;
        public BankAccount(double initial) { balance = initial; }
        public synchronized void deposit(double amt) {
            balance += amt;
            System.out.println(Thread.currentThread().getName() + " deposited " + amt + " -> " + balance);
        }
        public synchronized void withdraw(double amt) {
            if (amt <= balance) {
                balance -= amt;
                System.out.println(Thread.currentThread().getName() + " withdrew " + amt + " -> " + balance);
            } else {
                System.out.println(Thread.currentThread().getName() + " insufficient funds for " + amt);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BankAccount acc = new BankAccount(1000);
        Runnable r1 = () -> {
            for (int i = 0; i < 5; i++) {
                acc.deposit(200);
                try { Thread.sleep(50); } catch (InterruptedException e) {}
            }
        };
        Runnable r2 = () -> {
            for (int i = 0; i < 5; i++) {
                acc.withdraw(150);
                try { Thread.sleep(70); } catch (InterruptedException e) {}
            }
        };
        Thread t1 = new Thread(r1, "T-Deposit");
        Thread t2 = new Thread(r2, "T-Withdraw");
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println("Final balance shown above.");
    }
}

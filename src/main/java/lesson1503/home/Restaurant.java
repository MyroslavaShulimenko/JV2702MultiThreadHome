package lesson1503.home;

    import java.util.concurrent.Semaphore;

    public class Restaurant {
        private Semaphore tables;
        private Semaphore soupCook;
        private Semaphore saladCook;
        private Semaphore mainDishCook;

        public Restaurant() {
            this.tables = new Semaphore(5);
            this.soupCook = new Semaphore(1);
            this.saladCook = new Semaphore(1);
            this.mainDishCook = new Semaphore(1);
        }
        // прийти, занять столик, перейти к заказу
        public void enterRestaurant(Customer customer) throws InterruptedException {
            System.out.println(customer.getName() + " entered the restaurant.");
            tables.acquire();
            System.out.println(customer.getName() + " took a seat.");
            orderMeal(customer);
        }
//сделать заказ - занять всех поваров и перейти к приготовлению и подаче заказа
        private void orderMeal(Customer customer) throws InterruptedException {
            System.out.println(customer.getName() + " is ordering.");
            soupCook.acquire();
            saladCook.acquire();
            mainDishCook.acquire();
            System.out.println(customer.getName() + " ordered a meal.");
            serveMeal(customer);
        }
// осободить все поваров, поесть и выйти
        private void serveMeal(Customer customer) throws InterruptedException {
            System.out.println(customer.getName() + "'s meal is being prepared.");
            Thread.sleep(2000);
            System.out.println(customer.getName() + "'s meal is ready.");
            soupCook.release();
            saladCook.release();
            mainDishCook.release();
            System.out.println(customer.getName() + "'s meal is being served.");
            Thread.sleep(1000);
            System.out.println(customer.getName() + "'s meal is served.");
            leaveRestaurant(customer);
        }
// выйти и освободить столик
        private void leaveRestaurant(Customer customer) throws InterruptedException {
            Thread.sleep(2000);
            System.out.println(customer.getName() + " left the restaurant.");
            tables.release();

        }

        public static void main(String[] args) throws InterruptedException {
            Restaurant restaurant = new Restaurant();
            Thread[] customers = new Thread[7];
            for (int i = 0; i < customers.length; i++) {
                customers[i] = new Thread(new Customer("Customer " + (i + 1), restaurant));
                customers[i].start();
                Thread.sleep(500);
            }
            for (Thread customer : customers) {
                customer.join(); // ждать, пока все посетители выйдут из ресторана
            }
            System.out.println("All customers have left the restaurant.");
        }
    }

    class Customer implements Runnable {
        private String name;
        private Restaurant restaurant;

        public Customer(String name, Restaurant restaurant) {
            this.name = name;
            this.restaurant = restaurant;
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {
            try {
                restaurant.enterRestaurant(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



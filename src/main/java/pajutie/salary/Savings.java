package pajutie.salary;

public class Savings{
    public static void main(String[] args) {
        Income income = new Income();
        System.out.println("moneyInMyPocket = " + String.format("%,.2f", income.moneyInMyPocket(Months.AUGUST)));
    }
}

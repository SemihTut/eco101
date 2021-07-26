package pajutie.salary;

public class Income {
    final private static int salary = 3500;

    public double allExpenses(){
        return ConfigReader.getAllExpensesAsMap().values()
                .stream()
                .filter(entry->!entry.contains("tax_rate"))
                .mapToDouble(Double::valueOf)
                .sum();

    }

    public double moneyInMyPocket(){
        return salary-allExpenses()-taxes();
    }

    public double taxes(){
        return salary*Double.parseDouble(ConfigReader.get("tax_rate"));
    }
}

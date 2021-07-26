package pajutie.salary;

public class Income {
    final private static int salary = 3500;
    private String path = System.getProperty("user.dir");


    public double allExpenses() {
        return ConfigReader.getAllExpensesAsMap().values()
                .stream()
                .filter(entry -> !entry.contains("tax_rate"))
                .mapToDouble(Double::valueOf)
                .sum();

    }

    public double moneyInMyPocket(Months months) {
        return salary - allExpenses() - taxes() - excelExpenses(months.toString());
    }

    public double taxes() {
        return salary * Double.parseDouble(ConfigReader.get("tax_rate"));
    }

    public double excelExpenses(String month) {
        ExcelUtil excelUtil = new ExcelUtil(path + "/extraExpenses.xlsx", month);
        return excelUtil.getFirstDataRow().stream()
                .mapToDouble(Double::parseDouble)
                .sum();
    }
}

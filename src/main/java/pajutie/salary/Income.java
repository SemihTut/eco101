package pajutie.salary;

public class Income {
    private final static int salary = 3500;
    private final String path = System.getProperty("user.dir");

    protected double fixedExpenses() {
        return ConfigReader.getAllExpensesAsMap().values()
                .stream()
                .filter(entry -> !entry.contains("tax_rate"))
                .mapToDouble(Double::valueOf)
                .sum();

    }

    protected double moneyInMyPocket(Months months) {
        return salary - fixedExpenses() - taxes() - excelExpenses(months.toString());
    }

    protected double taxes() {
        return salary * Double.parseDouble(ConfigReader.get("tax_rate"));
    }

    protected double excelExpenses(String month) {
        ExcelUtil excelUtil = new ExcelUtil(path + "/extraExpenses.xlsx", month);
        return excelUtil.getFirstDataRow()
                .stream()
                .mapToDouble(Double::parseDouble)
                .sum();
    }
}

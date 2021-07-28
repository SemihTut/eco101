package pajutie.salary;

public class Income {
    private final static int salary = 3500;
    private final String path = System.getProperty("user.dir");

    /**
     *
     * @return this method calculate the fixed expenses in the 'Savings.properties' files
     */
    protected double fixedExpenses() {
        return ConfigReader.getAllExpensesAsMap().values()
                .stream()
                .filter(entry -> !entry.contains("tax_rate"))
                .mapToDouble(Double::valueOf)
                .sum();

    }

    /**
     *
     * @param months I should be used the Month.enum
     * @return
     */
    protected double moneyInMyPocket(Months months) {
        return salary - fixedExpenses() - taxes() - excelExpenses(months.toString());
    }

    /**
     *
     * @return total tax expense
     */
    protected double taxes() {
        return salary * Double.parseDouble(ConfigReader.get("tax_rate"));
    }

    /**
     *
     * @param month each month should be in different sheet
     * @return
     */
    protected double excelExpenses(String month) {
        ExcelUtil excelUtil = new ExcelUtil(path + "/extraExpenses.xlsx", month);
        return excelUtil.getFirstDataRow()
                .stream()
                .mapToDouble(Double::parseDouble)
                .sum();
    }
}

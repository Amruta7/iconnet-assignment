public class StockData {
    public String dailyRange, totalRange;

    public StockData() {
    }

    public String getDailyRange() {
        return dailyRange;
    }

    public void setDailyRange(String dailyRange) {
        this.dailyRange = dailyRange;
    }

    public String getTotalRange() {
        return totalRange;
    }

    public void setTotalRange(String totalRange) {
        this.totalRange = totalRange;
    }

    @Override
    public String toString() {
        return "StockData{" +
                "dailyRange='" + dailyRange + '\'' +
                ", totalRange='" + totalRange + '\'' +
                '}';
    }
}

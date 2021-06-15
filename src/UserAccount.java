import java.util.Date;

public class UserAccount {
    private String userName;
    private String password;
    private Series []series;
    private int sumSeries;
    private int sumChapter;
    private int finishSeries;
    private Date accountCreationDate;
    private Date endSubscription;

    public UserAccount(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.series = new Series[0];
        this.sumSeries = this.series.length;
        this.sumChapter = 0;
        this.finishSeries=0;
        this.accountCreationDate = new Date();
        this.endSubscription = new Date();
        endSubscription.setYear(endSubscription.getYear()+1);
    }
    public void addSeries(Series series){
        Series[]newSeries=new Series[this.series.length+1];
        if(this.series.length==0){
            newSeries[0]=series;
        }
        else {
            for (int i = 0; i < this.series.length; i++) {
                newSeries[i] = this.series[i];
            }
            newSeries[this.series.length] = series;
        }
        this.series=newSeries;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public Series[] getSeries() {
        return series;
    }

    public void setSeries(Series[] series) {
        this.series = series;
    }

    public int getSumSeries() {
        return sumSeries;
    }

    public void setSumSeries(int sumSeries) {
        this.sumSeries = sumSeries;
    }

    public int getSumChapter() {
        return sumChapter;
    }

    public void setSumChapter(int sumChapter) {
        this.sumChapter = sumChapter;
    }

    public Date getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(Date accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public Date getEndSubscription() {
        return endSubscription;
    }

    public void setEndSubscription(Date endSubscription) {
        this.endSubscription = endSubscription;
    }

    public int getFinishSeries() {
        return finishSeries;
    }

    public void setFinishSeries(int finishSeries) {
        this.finishSeries = finishSeries;
    }

    public void print(){
        System.out.println("user name: "+this.userName);
        System.out.println("Series you have finished: "+this.finishSeries);
        System.out.println("Total series you saw: "+this.sumSeries);
        System.out.println("Total episodes you have seen: "+this.sumChapter);
        System.out.println("Create account: "+this.accountCreationDate);
        System.out.println("Subscription termination:"+this.endSubscription);
    }
}

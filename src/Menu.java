import java.util.Scanner;

public class Menu {
    static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args)
    {
        UserAccount[] users=new UserAccount[0];
        Series []allSeries=new Series[3];
        allSeries[0]=wonderfulCountry();
        allSeries[1]=savriMaranan();
        allSeries[2]=policeMan();
            while (true) {
                printMainMenu();

                int choice = 0;
                try {
                    choice = scanner.nextInt();
                } catch (Exception e) {
                    scanner.nextLine();
                    System.out.println("Wrong choice");
                }

                switch (choice) {
                    case Def.OPENING_ACCOUNT:
                        users = addUser(users);
                        UserAccount user = users[users.length - 1];
                        menuUser(user,allSeries);
                        break;
                    case Def.LOG_IN:
                        System.out.println("enter user name");
                        String name = null;
                        try {
                            name = scanner.next();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println("enter your password");
                        String password = null;
                        try {
                            password = scanner.next();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (userSearch(users, name, password)) {
                             user=users[indexUser(users,name,password)];
                             menuUser(user,allSeries);
                             break;
                        } else System.out.println("user name or password incorrect");
                        break;

                }
            }


    }
    public static void printMainMenu(){
        System.out.println("(1) Opening an account." );
        System.out.println(" (2) Logging in to an existing account.");

    }
    public static void printMain(){
        System.out.println("(1) View the list of all series.");
        System.out.println("(2) View the list of series you started watching.");
        System.out.println("(3) View subscription details.");
        System.out.println("(4) Select the series to watch.");
        System.out.println("(5) Log out.");
    }

    public static UserAccount[]addUser(UserAccount[]users){
        UserAccount[] newUsers=new UserAccount[users.length+1];
        for (int i=0;i< users.length;i++){
            newUsers[i]=users[i];
        }
        newUsers[users.length]= new UserAccount(null,null);
        System.out.println("Enter user name");
        newUsers[users.length].setUserName(chekUserName(scanner.next(), users));
        System.out.println("Enter password");
            newUsers[users.length].setPassword(checkPassword(scanner.next()));
        return newUsers;
    }
    public static boolean userSearch(UserAccount[]users,String userName,String password){
        boolean found=false;
        for(int i=0;i<users.length;i++){
            if(users[i].getUserName().equals(userName)&&users[i].getPassword().equals(password)){
                found=true;
            }
        }
        return found;
    }
    public static int indexUser(UserAccount[]users,String userName,String password){
        int index=0;
        for(int i=0;i<users.length;i++){
            if(users[i].getUserName().equals(userName)&&users[i].getPassword().equals(password)){
                index=i;
            }
        }
        return index;
    }
    public static void printAllSeries(Series[] series){
        for (int i=0;i<series.length;i++){
            series[i].printNameSeries();
        }
        System.out.println("--------");
    }
    public static String chekUserName(String userName,UserAccount[]users){
        for(int i=0;i<users.length;i++){
            if (userName.equals(users[i].getUserName())){
                System.out.println("Username used ");
                userName= scanner.next();
                chekUserName(userName,users);
            }
        }
        return userName;

    }
    public static boolean searchSeries(String nameSeries,Series[]series){
        boolean found=false;
        for(int i=0;i<series.length;i++){
            if(series[i].getSeriesName().equals(nameSeries)){
                found=true;
            }
        }
        return found;
    }
    public static int indexSeries(Series[]allSeries,String nameSeries){
        int index=0;
        for(int i=0;i<allSeries.length;i++){
            if(allSeries[i].getSeriesName().equals(nameSeries)){
                index=i;
            }
        }
        return index;
    }
    public  static void menuUser(UserAccount user,Series[]allSeries){
        while (true) {
            printMain();
            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Wrong choice");
            }
            switch (choice) {
                case Def.VIEW_LIST_ALL_SERIES:
                    printAllSeries(allSeries);
                    break;
                case Def.VIEW_LIST_ALL_SERIES_YOU_WATCHED:
                    if(user.getSeries().length==0){
                        System.out.println("no series");
                    }
                    for(int i=0;i<user.getSeries().length;i++){
                        user.getSeries()[i].printNameSeries();
                        Series series=user.getSeries()[i];
                        series.getChapters()[series.getNamber()].print();
                    }
                    break;
                case Def.VIEW_SUBSCIPTION_DETAILS:
                    user.print();
                    break;
                case Def.SELECT_SERIES_TO_WATCH:
                    System.out.println("enter name series");
                    String nameSeries = null;
                        nameSeries = scanner.next();
                    if (searchSeries(nameSeries, allSeries)) {
                        if (searchSeries(nameSeries, user.getSeries())) {
                            Series series = user.getSeries()[indexSeries(user.getSeries(), nameSeries)];
                            series.getChapters()[series.getNamber()].print();
                            user.setSumChapter(user.getSumChapter()+1);
                            series.setNamber(series.getNamber() + 1);
                            if(series.getNamber()>=series.getChapters().length) {
                                series.setNamber(0);
                            }
                        } else {
                            Series series = allSeries[indexSeries(allSeries, nameSeries)];
                            series.print();
                            System.out.println("which chapter you want yo see?");
                            int chapter= 1;
                            try {
                                chapter = scanner.nextInt();
                                series.getChapters()[chapter-1].print();
                            } catch (ArrayIndexOutOfBoundsException e) {
                                scanner.nextLine();
                                System.out.println("There is no such chapter");
                            }
                            user.addSeries(series);
                            user.getSeries()[user.getSeries().length-1].setNamber(chapter);
                            user.setSumChapter(user.getSumChapter()+1);
                        }

                    }
                    else System.out.println("not faond");
                    break;

                case Def.LOG_OUT:
                    break;

            }
            if(choice==Def.LOG_OUT){
                break;
            }
        }

    }
    public static String checkPassword(String password){
        if (password.length()>=6){
            for(int i=0;i<password.length()-1;i++){
                if(password.charAt(i)>='A' &&password.charAt(i)<='z'&&password.charAt(i+1)>='1'&&password.charAt(i+1)<='9'){
                    return password;
                }
                else if(password.charAt(i+1)>='A' &&password.charAt(i+1)<='z'&&password.charAt(i)>='1'&&password.charAt(i)<='9'){
                    return password;
                }
            }
        }
        System.out.println("password not good");
        return checkPassword(scanner.next());
    }
    public static Series wonderfulCountry(){
        Chapter[]chapters=new Chapter[3];
        chapters[0]=new Chapter("A great country elects a president",
                "This happens once every seven years: the Knesset of Israel elects citizen number 1! A great country kicks off the presidential race with Bozi Herzog, Miriam Peretz and the genius who knew how to retire in time - who will win the senior position in the country?",
                "26/05/21");
        chapters[1]=new Chapter("A great country for a pharmacist",
                "Anything can happen: Will Israel get both a president and a government in one day?",
                "02/06/21");
        chapters[2]=new Chapter("Swearing in the government",
                "After 12 years in power and countless election campaigns - the change is coming. After the inauguration ceremony in the Knesset, comes the real inauguration ceremony in a wonderful country, including (perhaps) a farewell to the royal couple",
                "14/06/21");
        Series wonderfulCountry=new Series(chapters,"WonderfulCountry");
        return wonderfulCountry;

    }
    public static Series savriMaranan(){
        Chapter[]chapters=new Chapter[3];
        chapters[0]=new Chapter("The return of the comeback",
                "Will Manny and Aline go back to each other? And where will they live?",
                "28/08/20");
        chapters[1]=new Chapter("redemption",
                "Meirav and Aharon left the parents' house and everyone is invited to the farewell party, including the counts. The problem is that Sylvan refuses to believe it's really happening!?",
                "16/10/20");
        chapters[2]=new Chapter("Procedure",
                "Ricky invites Rachel to her for a quiet evening and recommendations on plastic surgeons and everyone meets Shai and Shani's beautiful opera.",
                "23/10/20");
        Series savriMaranan=new Series(chapters,"SavriMaranan");
        return savriMaranan;

    }
    public static Series policeMan(){
        Chapter[]chapters=new Chapter[3];
        chapters[0]=new Chapter("Stable cleaning",
                "Maor Ezra, who believes that Bauman betrayed him, goes out to clean violent stables in the organization. The city becomes a battlefield and Alon and the crew are determined to capture Ezra at all costs",
                "22/02/21");
        chapters[1]=new Chapter("Harvest Holiday",
                " A month after Ezra's arrest, the police are celebrating Shavuot and it seems that peace has returned to the city. But soon Alon and the crew discover that they have a new enemy, of a completely different kind",
                " 01/03/21");
        chapters[2]=new Chapter("The truth",
                "Season Ending Episode: DIP investigators are trying to break Alon and the crew. The arrested police officers must obtain outside cooperation in order to conceal incriminating evidence. How will the story end?.",
                "08/03/21");
        Series policeMan=new Series(chapters,"Police");
        return policeMan;

    }
}

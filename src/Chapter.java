public class Chapter {
    private String nameChapter;
    private String chapterSummary;
    private String broadcastDate;

    public Chapter(String nameChapter, String chapterSummary, String broadcastDate) {
        this.nameChapter = nameChapter;
        this.chapterSummary = chapterSummary;
        this.broadcastDate = broadcastDate;
    }

    public String getNameChapter() {
        return nameChapter;
    }

    public void setNameChapter(String nameChapter) {
        this.nameChapter = nameChapter;
    }

    public String getChapterSummary() {
        return chapterSummary;
    }

    public void setChapterSummary(String chapterSummary) {
        this.chapterSummary = chapterSummary;
    }

    public String getBroadcastDate() {
        return broadcastDate;
    }

    public void setBroadcastDate(String broadcastDate) {
        this.broadcastDate = broadcastDate;
    }
    public void print(){
        System.out.println("Name Chapter: "+this.nameChapter);
        System.out.println("Chapter Summary: "+this.chapterSummary);
        System.out.println("Broadcast date: "+this.broadcastDate);
        System.out.println("-------------------");
    }
}

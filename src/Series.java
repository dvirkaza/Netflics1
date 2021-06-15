public class Series {
    private Chapter[] chapters;
    private String seriesName;
    private int namber;

    public Series(Chapter[] series, String seriesName) {
        this.chapters = series;
        this.seriesName = seriesName;
        this.namber=0;
    }

    public Chapter[] getChapters() {
        return chapters;
    }

    public void setChapters(Chapter[] chapters) {
        this.chapters = chapters;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public int getNamber() {
        return namber;
    }

    public void setNamber(int namber) {
        this.namber = namber;
    }

    public void printNameSeries(){
        System.out.println("series name is:"+this.seriesName);

    }
    public void print(){
        System.out.println("series name is:"+this.seriesName);
        for (int i=0;i<this.chapters.length;i++){
            System.out.println(i+1+":");
            chapters[i].print();
        }
    }

}

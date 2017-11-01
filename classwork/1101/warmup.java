import java.util.PriorityQueue;

class Video implements Comparable<Video> {
    private String url, author;
    int views;
    public Video(String url, String author, int views) {
        this.url = url;
        this.author = author;
        this.views = views;
    }
    public String toString() {
        return url+" "+author+" "+views;
    }
    public int compareTo(Video v) {
        if(v.views > views) {
            return 1;
        }
        else if(v.views < views) {
            return -1;
        }
        return 0;
    }
}
public class warmup {
    public static void main(String[] args) {
        PriorityQueue<Video> videos = new PriorityQueue<Video>();
        videos.add(new Video("url1", "name1", 111));
        videos.add(new Video("url1", "name2", 33));
        videos.add(new Video("url1", "name3", 542));
        videos.add(new Video("url1", "name4", 75));
        videos.add(new Video("url1", "name5", 123));
        videos.add(new Video("url1", "name6", 123));
        while(!videos.isEmpty()) {
            System.out.println(videos.poll());
        }
    }
}
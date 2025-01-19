public class Book {
    private String title, author, publisher, summary, coverImage;
    private int year;
    private double price;

    public Book(String title, String author, int year, String publisher, double price, String summary, String coverImage) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.publisher = publisher;
        this.price = price;
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public String getPublisher() {
        return publisher;
    }

    public double getPrice() {
        return price;
    }

    public String getSummary() {
        return summary;
    }
}

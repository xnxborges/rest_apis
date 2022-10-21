package br.com.rest_apis.dto.v1;

import br.com.rest_apis.model.Book;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"id","author","date", "title","price"})
public class BookDto extends RepresentationModel<BookDto> {

    private Long id;
    private String author;
    private Date launchDate;
    private String title;
    private Double price;

    public BookDto(Long id, String author, Date launchDate, Double price, String title) {
        this.id = id;
        this.author = author;
        this.launchDate = launchDate;
        this.price = price;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return launchDate;
    }

    public void setDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(getId(), book.getId()) && Objects.equals(getAuthor(), book.getAuthor())
                && Objects.equals(getDate(), book.getDate()) && Objects.equals(getPrice(), book.getPrice())
                && Objects.equals(getTitle(), book.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthor(), getDate(), getPrice(), getTitle());
    }
}

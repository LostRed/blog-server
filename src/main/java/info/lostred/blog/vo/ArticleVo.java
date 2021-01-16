package info.lostred.blog.vo;

import info.lostred.blog.entity.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleVo extends Article {
    private String catalogue;
    private String author;
    private String status;

    @Override
    public String toString() {
        return super.toString() + "+ ArticleVo(" +
                "catalogue='" + catalogue + '\'' +
                ", author='" + author + '\'' +
                ", status='" + status + '\'' +
                ')';
    }
}

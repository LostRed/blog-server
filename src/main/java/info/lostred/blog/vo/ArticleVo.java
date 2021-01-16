package info.lostred.blog.vo;

import info.lostred.blog.entity.Article;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ArticleVo对象", description = "文章")
public class ArticleVo extends Article {
    @ApiModelProperty(value = "文章类型")
    private String catalogue;
    @ApiModelProperty(value = "作者")
    private String author;
    @ApiModelProperty(value = "状态名")
    private String status;

    @Override
    public String toString() {
        return super.toString() + "+ArticleVo(" +
                "catalogue='" + catalogue + '\'' +
                ", author='" + author + '\'' +
                ", status='" + status + '\'' +
                ')';
    }
}

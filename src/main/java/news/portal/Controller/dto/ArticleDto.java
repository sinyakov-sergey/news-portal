package news.portal.Controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import news.portal.entity.ArticleEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    private String category;
    private String head;
    private String text;

    public ArticleDto(ArticleEntity articleEntity) {
        this.id = articleEntity.getId();
        this.category = articleEntity.getCategory();
        this.head = articleEntity.getHead();
        this.text = articleEntity.getText();
    }
}



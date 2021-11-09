package news.portal.entity;

import lombok.*;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import javax.persistence.Id;


@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("ARTICLES")
public class ArticleEntity {

    @Id
    @Column(value = "ID")
    private Long id;

    @Column(value = "CATEGORY")
    private String category;

    @Column(value = "HEAD")
    private String head;

    @Column(value = "TEXT")
    private String text;

}

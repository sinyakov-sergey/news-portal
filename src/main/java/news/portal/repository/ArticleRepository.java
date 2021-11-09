package news.portal.repository;


import news.portal.entity.ArticleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticleRepository extends PagingAndSortingRepository<ArticleEntity, Long> {

}

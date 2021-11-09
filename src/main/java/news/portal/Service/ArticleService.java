package news.portal.Service;


import news.portal.Controller.dto.ArticleDto;
import news.portal.entity.ArticleEntity;
import news.portal.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<ArticleDto> getAllArticle(Pageable pageable){
        Page<ArticleEntity> articleEntityPage = articleRepository.findAll(pageable);
        return articleEntityPage.stream().map(ArticleDto::new).collect(Collectors.toList());
    }

    public boolean uploadFile(MultipartFile file, String category) {
        //if (file.isEmpty())return false;
        //if (file.getSize() >)
        //ArticleEntity articleEntity = getArticleFromZip(file, category);
        //articleRepository.save(articleEntity);
        System.out.println("goood");
        return true;
    }

    private ArticleEntity getArticleFromZip(MultipartFile file, String category) {

        return new ArticleEntity();
    }
}

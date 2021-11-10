package news.portal.Controller;

import lombok.RequiredArgsConstructor;
import news.portal.Service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    //@CrossOrigin(origins = "http://localhost:3000")
    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/news/{pageNumber}")
    public ResponseEntity<?> fetchAllArticles(@PathVariable int pageNumber) {
        int pageIndex = pageNumber - 1;
        int numberArticleOnPage = 2;//число статей на одной странице
        return  ResponseEntity.ok(articleService.
                getAllArticle(PageRequest.
                        of(pageIndex, numberArticleOnPage, Sort.by("id").descending())));
    }


    //@CrossOrigin(origins = "http://localhost:3000")
    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/upload")
    public ResponseEntity<?> uploadArticle(@RequestParam("file") MultipartFile file,
                                           @RequestParam("category") String category) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.uploadFile(file, category));
    }

}

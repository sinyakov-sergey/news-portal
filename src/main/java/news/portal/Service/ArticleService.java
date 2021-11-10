package news.portal.Service;


import news.portal.Controller.dto.ArticleDto;
import news.portal.Exceptions.FileFormatException;
import news.portal.Exceptions.NumberOfFilesException;
import news.portal.Exceptions.TextFileException;
import news.portal.entity.ArticleEntity;
import news.portal.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<ArticleDto> getAllArticle(Pageable pageable){
        Page<ArticleEntity> articleEntityPage = articleRepository.findAll(pageable);
        return articleEntityPage.stream().map(ArticleDto::new).collect(Collectors.toList());
    }

    public boolean uploadFile(MultipartFile file, String category) throws IOException {
        if (file.isEmpty()) {
            throw new NumberOfFilesException("Zip-файл пуст!");
        }
        ArticleEntity articleEntity = getArticleFromZip(file, category);
        articleRepository.save(articleEntity);
        return true;
    }

    private ArticleEntity getArticleFromZip(MultipartFile file, String category) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(file.getBytes());
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        ZipEntry zipEntry;
        int numberOfFiles = 0;
        String head = null, text = null;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            numberOfFiles++;
            if (numberOfFiles > 1) {
                throw new NumberOfFilesException("В Zip-файле много файлов!");
            }
            if (!zipEntry.getName().equals("article/article.txt")) {
                throw new FileFormatException("Расширение файла статье не поддерживается!");
            }
            Scanner scanner = new Scanner(zipInputStream);
            head = getHead(scanner);
            text = getText(scanner);
        }
        return new ArticleEntity(null, category, head, text);
    }

    private String getHead(Scanner scanner) {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            throw new TextFileException("Отсутсвует заголовок статьи!");
        }
    }

    private String getText(Scanner scanner) {
        StringBuilder text = new StringBuilder();
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine()).append(" ");
        }
        if (text.length() < 1) {
            throw new TextFileException("Отсутсвует текст статьи!");
        } else {
            return text.toString();
        }
    }
}

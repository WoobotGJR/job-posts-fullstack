package com.woobot.jobProject.repository;

import com.mongodb.client.*;
import model.JobPost;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class SearchRepositoryImplementation implements SearchRepository {

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Override
    public List<JobPost> findByText(String text) {
        // Создание списка для хранения результатов
        final List<JobPost> posts = new ArrayList<>();

        // Получение доступа к базе данных и коллекции
        MongoDatabase db = client.getDatabase("javajobdb");
        MongoCollection<Document> collection = db.getCollection("jobposts");

        // Создание регулярного выражения для поиска с учетом регистра
        Pattern regex = Pattern.compile(text, Pattern.CASE_INSENSITIVE);

        // Создание фильтра для поиска по трем полям
        Document filter = new Document("$or", Arrays.asList(
                new Document("desc", regex),
                new Document("profile", regex),
                new Document("techs", regex)
        ));

        // Создание Aggregation Pipeline
//        Document matchStage = new Document("$match", filter);

        // Создание Aggregation Pipeline с лимитом
        List<Document> matchStage = new ArrayList<>();
        matchStage.add(new Document("$match", filter));
        matchStage.add(new Document("$limit", 10));

        // Выполнение агрегации
        try (MongoCursor<Document> cursor = collection.aggregate(matchStage).iterator()) {

            // Итерация по результатам агрегации
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                JobPost model = new JobPost();

                // Заполнение модели данными из документа
                model.setDesc(doc.getString("desc"));
                model.setExp(doc.getInteger("exp"));
                model.setProfile(doc.getString("profile"));
                List<String> techs = doc.getList("techs", String.class);
                model.setTechs(techs);

                // Добавление модели в список результатов
                posts.add(model);
            }
        } catch (Exception err) {
            // Обработка исключений при выполнении агрегации
            System.out.println(Arrays.toString(err.getStackTrace()));
        }

        // Возврат списка результатов
        return posts;
    }
}
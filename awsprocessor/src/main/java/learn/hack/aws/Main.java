package learn.hack.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.comprehend.AmazonComprehend;

import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
import com.amazonaws.services.comprehend.model.*;
import com.amazonaws.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;



public class Main {
    public static int stringSizeLimit =4500;
    public static void main(String[] args) {

        AWSCredentials awsCredentials = new AWSCredentials() {
            public String getAWSAccessKeyId() {
                return Security.ACCESS_KEY;
            }

            public String getAWSSecretKey() {

                return Security.SECREET_KEY;
            }
        };
        //ProfileCredentialsProvider profileCredentialsProvider = new ProfileCredentialsProvider("ME");
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
        AmazonComprehendClientBuilder amazonComprehendClientBuilder = AmazonComprehendClientBuilder.standard()
                .withRegion(Regions.DEFAULT_REGION)
                .withCredentials(awsCredentialsProvider);

        AmazonComprehend amazonComprehend = amazonComprehendClientBuilder.build();

        Set<DetectEntitiesResult> results = new HashSet<>();
        getSmallStrings(SampleData.profile_Full).forEach(y-> {
                DetectEntitiesRequest detectEntitiesRequest = new DetectEntitiesRequest()
                    .withText(y)
                    .withLanguageCode(LanguageCode.En);
            DetectEntitiesResult detectEntitiesResult = amazonComprehend.detectEntities(detectEntitiesRequest);
            results.add(detectEntitiesResult);
            });


//        DetectSentimentResult detectSentimentResult = amazonComprehend.detectSentiment(new DetectSentimentRequest()
//                .withLanguageCode(LanguageCode.En.toString())
//                .withText(SampleData.movie_review_from_wesite));
//        System.out.println("Sentiment : " + detectSentimentResult);
        Set<Entity> entityList= segregateEntitiesResult(results);
//        analyseEntities(detectEntitiesResult);
        analyseEntities(entityList);
//        for (Entity entity : detectEntitiesResult.getEntities()) {
//            System.out.println(entity);
//        }

        amazonComprehend.shutdown();
    }

    private static Set<Entity> segregateEntitiesResult(Set<DetectEntitiesResult> results) {
        Set<Entity> entities = new HashSet<>();
        for (DetectEntitiesResult detectEntitiesResult :results) {
            entities.addAll(detectEntitiesResult.getEntities());
        }
        return  entities;
    }

    static void analyseEntities(DetectEntitiesResult detectEntitiesResult ) {
        List<Entity> dataEntities = detectEntitiesResult.getEntities();
        Set<String> names = new HashSet<>();
        dataEntities.stream().filter(new Predicate<Entity>() {
            @Override
            public boolean test(Entity entity) {
                return entity.getType().equals(EntityType.PERSON.name());
            }
        }).forEach(y-> {
//            System.out.println("Person  : " +y.getText());
            names.add(y.getText());});
        System.out.println("Number of persons : " + names.size());

    }
    static void analyseEntities(Set<Entity> dataEntities ) {
        Set<String> names = new HashSet<>();
        dataEntities
//                .stream().filter(new Predicate<Entity>() {
//            @Override
//            public boolean test(Entity entity) {
//                return entity.getType().equals(EntityType.PERSON.name());
//            }
//        })
                .forEach(y-> {
            System.out.println(">>>>>>>>>>>>>>>>>>>>\t"+y);
            names.add(y.getText());
        });
        System.out.println("Number of persons : " + names.size());


    }    static void analyseEntities_Person(Set<Entity> dataEntities ) {
        Set<String> names = new HashSet<>();
        dataEntities.stream().filter(new Predicate<Entity>() {
            @Override
            public boolean test(Entity entity) {
                return entity.getType().equals(EntityType.PERSON.name());
            }
        }).forEach(y-> {
            System.out.println("Person  : " +y.getText());
            names.add(y.getText());
        });
        System.out.println("Number of persons : " + names.size());


    }

    static Set<String> getSmallStrings(String bigString){
        Set<String> strings = new HashSet<>();
        int lenghtOfString = bigString.length();
        if (lenghtOfString<stringSizeLimit) {
            strings.add(bigString);
            return  strings;
        } else {
            for(int index = 0; index<lenghtOfString;index+=stringSizeLimit){
                if(index+stringSizeLimit > lenghtOfString){
                    strings.add(bigString.substring(index));
                }else {
                    strings.add(bigString.substring(index,index+stringSizeLimit));
                }
            }
        }
        return  strings;
    }
}

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.comprehend.AmazonComprehend;

import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
import com.amazonaws.services.comprehend.model.DetectEntitiesRequest;
import com.amazonaws.services.comprehend.model.DetectEntitiesResult;
import com.amazonaws.services.comprehend.model.Entity;
import com.amazonaws.services.comprehend.model.LanguageCode;

public class Main {
    public static void main(String[] args) {

        AWSCredentials awsCredentials = new AWSCredentials() {
            public String getAWSAccessKeyId() {
                return "null";
            }

            public String getAWSSecretKey() {

                return "null";
            }
        };
        ProfileCredentialsProvider  profileCredentialsProvider = new ProfileCredentialsProvider("ME");
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
        AmazonComprehendClientBuilder amazonComprehendClientBuilder = AmazonComprehendClientBuilder.standard()
                .withRegion(Regions.DEFAULT_REGION)
                .withCredentials(awsCredentialsProvider);

        AmazonComprehend amazonComprehend = amazonComprehendClientBuilder.build();

        DetectEntitiesRequest detectEntitiesRequest = new DetectEntitiesRequest().withText("Iam So happy to hear from you , thanks David");
        detectEntitiesRequest.setLanguageCode(LanguageCode.En.toString());

        DetectEntitiesResult detectEntitiesResult = amazonComprehend.detectEntities(detectEntitiesRequest);
        for (Entity entity: detectEntitiesResult.getEntities()){
            System.out.println(entity);
        }

        amazonComprehend.shutdown();
    }
    static void showResult(){

    }
}

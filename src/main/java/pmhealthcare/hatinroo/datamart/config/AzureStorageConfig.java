package pmhealthcare.hatinroo.datamart.config;


import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

@Configuration
public class AzureStorageConfig {

    @Value("${azure.storage.connection-string}")
    public String azureStorageConnectionString;


    @Bean
    public CloudBlobClient cloudBlobClient() throws URISyntaxException, InvalidKeyException {
        CloudStorageAccount storageAccount = CloudStorageAccount.parse(azureStorageConnectionString);
        return storageAccount.createCloudBlobClient();
    }

}

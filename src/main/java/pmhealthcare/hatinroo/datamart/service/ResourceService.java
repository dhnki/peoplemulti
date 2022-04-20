package pmhealthcare.hatinroo.datamart.service;


import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.core.Base64;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pmhealthcare.hatinroo.datamart.repository.app.MemberRepository;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;


@Slf4j
@RequiredArgsConstructor
@Service
public class ResourceService {

    private final CloudBlobClient cloudBlobClient;

    private final MemberRepository memberRepository;



//    public String add(long memberNo, int type, MultipartFile resource)  {
//
//        String uri = "";
//
//        Optional<Member> findMember = memberRepository.findById(memberNo);
//        findMember.orElseThrow( () -> new ApiException(E_Error.NO_MEMBER) );
//
//        if (  !resource.getContentType().contains("image") ){
//            throw new ApiException(E_Error.FILE_UPLOAD);
//        }
//
//        // moniter
//        if( 1 == type ){
//
////            String originFileName = StringUtils.cleanPath(resource.getOriginalFilename());
//
////            String ext = originFileName.substring(originFileName.lastIndexOf(".") + 1);
//
//            log.info("resource name : " + resource.getOriginalFilename());
//
//            String fileName = "/" + findMember.get().getNo() + "/" + "hatinroo_" + findMember.get().getNo() + ".jpg";
//
//            uri = resourceUploader(fileName, "moniter", resource);
//
//        }
//        // user
////        else if( 2 == type){
////
////        }
//
//        return uri;
//    }




    public  String    resourceUploader(String bucket , String strFilename, String strFolder, File file) throws RuntimeException
    {

        String uri = null;

        try {

            CloudBlobContainer cloudBlobContainer = cloudBlobClient.getContainerReference(bucket);

            //서비스시 제거 필요
//            cloudBlobContainer.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());

            CloudBlockBlob blob = cloudBlobContainer.getBlockBlobReference(strFolder+"/"+strFilename);

//            log.info("contenttype : " + pkSourceFile.getContentType());

            FileInputStream fi = new FileInputStream(file);

            blob.upload( fi, file.length());

            uri = blob.getUri().toString();


            fi.close();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uri;
    }


}

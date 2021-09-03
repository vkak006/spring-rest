package ls.electric.demo.component.file.service;

import lombok.RequiredArgsConstructor;
import ls.electric.demo.component.file.domain.Image;
import ls.electric.demo.component.file.repository.FileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Service
public class FileService {
    @Value("${resource.location}")
    private String basePath;

    private ApplicationContext resourceLoader;
    private final FileRepository fileRepository;

    public Mono<Void> uploadFile(Mono<FilePart> filePartMono){
        return filePartMono
                .flatMap(file -> {
                    Image image = Image.newInstance(file.filename(),Paths.get(basePath).toString());
                    Mono<Image> saveFileData = fileRepository.save(image);
                    Mono<Void> copyFileObj = Mono.just(Paths.get(Paths.get(basePath).toString(), image.getTempFileName())
                            .toFile())
                            .flatMap(file::transferTo);
                    return Mono.when(saveFileData, copyFileObj);
                })
                .then();
    }
    public Mono<Void> uploadFile(Flux<FilePart> filePartFlux){
        return filePartFlux
                .flatMap(file -> {
                    Mono<Image> saveFileData = fileRepository.save(Image.newInstance(file.filename(),Paths.get(basePath).toString()));
                    Mono<Void> copyFileObj = Mono.just(Paths.get(Paths.get(basePath).toString(), file.filename())
                            .toFile())
                            .flatMap(file::transferTo);
                    return Mono.when(saveFileData, copyFileObj);
                })
                .then();
    }
    public Mono<Void> deleteFile(String fileName){
        Mono<Void> deleteImageData = fileRepository.deleteByTempFileName(fileName);
        Mono<Void> deleteFile = Mono.fromRunnable(() -> {
           try{
               Files.deleteIfExists(Paths.get(Paths.get(basePath).toString(),fileName));
           }catch (IOException e){
               throw new RuntimeException(e);
           }
        });

        return Mono.when(deleteImageData,deleteFile).then();
    }
    public Mono<String> selectFilePath(String fileName){
        return fileRepository.findByTempFileName(fileName)
                .flatMap(file -> Mono.just(Paths.get(file.getFilePath() + "/" + file.getTempFileName()).toString()));
    }
}

package ls.electric.demo.common.file.service;

import lombok.RequiredArgsConstructor;
import ls.electric.demo.common.file.domain.Image;
import ls.electric.demo.common.file.repository.FileRepository;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Service
public class FileService {
    private final Path basePath = Paths.get("./src/main/resources/upload/");
    private final FileRepository fileRepository;

    public Mono<Void> uploadFile(Mono<FilePart> filePartMono){
        return filePartMono
                .flatMap(file -> {
                    Mono<Image> saveImageData = fileRepository.save(Image.newInstance(file.filename(),basePath.toString()));
                    Mono<Void> copyImageData = Mono.just(Paths.get(basePath.toString(), file.filename())
                            .toFile())
                            .map(destFile -> {
                                try{
                                    destFile.createNewFile();
                                    return destFile;
                                }catch (IOException e){
                                    throw new RuntimeException(e);
                                }
                            })
                            .flatMap(file::transferTo);
                    return Mono.when(saveImageData, copyImageData);
                })
                .then();
    }

    public Mono<Void> uploadFiles(Flux<FilePart> filePartFlux){
        return filePartFlux
                .flatMap(file -> {
                    Mono<Image> saveImageData = fileRepository.save(Image.newInstance(file.filename(),basePath.toString()));
                    Mono<Void> copyImageData = Mono.just(Paths.get(basePath.toString(), file.filename())
                            .toFile())
                            .map(destFile -> {
                                try{
                                    destFile.createNewFile();
                                    return destFile;
                                }catch (IOException e){
                                    throw new RuntimeException(e);
                                }
                            })
                            .flatMap(file::transferTo);
                    return Mono.when(saveImageData, copyImageData);
                })
                .then();
    }

    public Mono<Void> deleteFile(String fileName){
        Mono<Void> deleteImageData = fileRepository.deleteByOrigFileName(fileName);
        Mono<Void> deleteFile = Mono.fromRunnable(() -> {
           try{
               Files.deleteIfExists(Paths.get(basePath.toString(),fileName));
           }catch (IOException e){
               throw new RuntimeException(e);
           }
        });

        return Mono.when(deleteImageData,deleteFile).then();
    }
}

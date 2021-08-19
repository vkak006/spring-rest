package ls.electric.demo.common.file.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import ls.electric.demo.common.file.service.FileService;
import ls.electric.demo.common.users.service.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequestMapping("/api/file")
@RestController
public class FileController {

    private final Path basePath = Paths.get("./src/main/resources/upload/");

    @Autowired
    private FileService fileService;

    @PostMapping("/single")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> upload(@RequestPart("file") Mono<FilePart> filePartMono){
        return fileService.uploadFile(filePartMono);
//        return filePartMono
//            .doOnNext(file -> System.out.println("Received File : " + file.filename()))
//            .flatMap(file -> file.transferTo(basePath.resolve(file.filename())))
//            .then();
    }

    @DeleteMapping("/single")
    public Mono<Void> delete(String filename){
        return fileService.deleteFile(filename);
    }

    @PostMapping("/multi")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> upload(@RequestPart("file") Flux<FilePart> filePartFlux){
        return filePartFlux
                .flatMap(file-> file.transferTo(basePath.resolve(file.filename())))
                .then();
    }
}

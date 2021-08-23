package ls.electric.demo.common.file.controller;

import ls.electric.demo.common.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    }

    @DeleteMapping("/single")
    public Mono<Void> delete(String filename){
        return fileService.deleteFile(filename);
    }

    @PostMapping("/multi")
    public Mono<Void> upload(@RequestPart("files") Flux<FilePart> filePartFlux){
        return fileService.uploadFile(filePartFlux);
//        return  filePartFlux
//                .doOnNext(fp -> System.out.println(fp.filename()))
//                .flatMap(fp -> fp.transferTo(basePath.resolve(fp.filename())))
//                .then();
    }

    @GetMapping("/{fileName}")
    public Mono<String> readLocalFile(@PathVariable("fileName") String fileName){
        return fileService.readLocalFile(fileName);
    }
}

package ru.practicum.ewn.service.compilations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewn.service.compilations.dto.CompilationDto;
import ru.practicum.ewn.service.compilations.service.CompilationService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping("compilations")
@RequiredArgsConstructor
@Slf4j
public class CompilationController {
    private final CompilationService compilationService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompilationDto findById(@PathVariable Long id) {
        log.info("Получена подборка событий с id {}", id);
        return compilationService.getCompilationById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompilationDto> getCompilations(@RequestParam(required = false, defaultValue = "false") Boolean pinned,
                                                @PositiveOrZero @RequestParam(name = "from", defaultValue = "0", required = false) Integer from,
                                                @Positive @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        return compilationService.findAllCompilations(pinned, from, size);
    }


}

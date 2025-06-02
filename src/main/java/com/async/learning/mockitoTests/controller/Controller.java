package com.async.learning.mockitoTests.controller;

import com.async.learning.mockitoTests.dto.RequestDto;
import com.async.learning.mockitoTests.dto.ResponseDto;
import com.async.learning.mockitoTests.service.ItemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/one")
public class Controller {

    private ItemService itemService;

    public Controller(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/addItem")
    public ResponseEntity<ResponseDto> addItem(@RequestBody @Valid RequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.addItem(requestDto));
    }

    @GetMapping("/getItemById")
    public ResponseEntity<ResponseDto> getItemById(@RequestParam("id") @NotNull(message = "Id cannot be empty") Long id) {
        return ResponseEntity.ok().body(itemService.getItemById(id));
    }

    //@RequestParam(required = false, defaultValue = "0") Если параметр необязательный, можно делать так
    //Можно использовать в особо запущенных случаях для валидации

    /*
            @GetMapping("/data")
            public String getData(  @RequestParam
                                    @NotNull(message = "Параметр 'id' обязателен")
                                    @Positive(message = "ID должен быть положительным числом")
                                    Long id) {
            return "Data for ID: " + id;
}
     */

    @GetMapping("/getItemByTitle")
    public ResponseEntity<ResponseDto> getItemByTitle(@RequestParam("title") @NotBlank(message = "Title cannot be empty") String title) {
        return ResponseEntity.ok().body(itemService.getItemByTitle(title));
    }


    @GetMapping("/getAllItems")
    public ResponseEntity<List<ResponseDto>> getAllItems() {
        return ResponseEntity.ok().body(itemService.getAllItem());
    }


    @PutMapping("/updateItemById")
    public ResponseEntity<ResponseDto> updateItemById(@RequestParam("id") @NotNull(message = "ID cannot be empty") Long id, @RequestBody @Valid RequestDto requestDto) {
        return ResponseEntity.ok().body(itemService.updateItem(id, requestDto));
    }


    @DeleteMapping("/deleteItemById")
    public ResponseEntity<ResponseDto> deleteItemById(@RequestParam("id") @NotNull(message = "ID cannot be empty") Long id) {
        return ResponseEntity.ok().body(itemService.deleteItem(id));
    }
}

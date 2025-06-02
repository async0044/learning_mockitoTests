package com.async.learning.mockitoTests.mapper;

import com.async.learning.mockitoTests.dto.RequestDto;
import com.async.learning.mockitoTests.dto.ResponseDto;
import com.async.learning.mockitoTests.entity.Item;

public class ItemMapper {
    public static ResponseDto itemToDto(Item item) {
        return new ResponseDto(  item.getId(),
                                        item.getTitle(),
                                        item.getContent(),
                                        item.getAuthor());
    }

    public static Item dtoToItem(RequestDto requestDto) {
        Item item = new Item();
        item.setTitle(requestDto.title());
        item.setContent(requestDto.content());
        item.setAuthor(requestDto.author());
        return item;
    }

}

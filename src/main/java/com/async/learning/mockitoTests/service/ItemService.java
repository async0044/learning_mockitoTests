package com.async.learning.mockitoTests.service;

import com.async.learning.mockitoTests.dto.RequestDto;
import com.async.learning.mockitoTests.dto.ResponseDto;
import com.async.learning.mockitoTests.entity.Item;
import com.async.learning.mockitoTests.mapper.ItemMapper;
import com.async.learning.mockitoTests.repository.ItemRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    public ResponseDto addItem(RequestDto requestDto) {
        if (itemRepository.existsByTitle(requestDto.title()))
            throw new ServiceException("Item with similar title already exists");
        Item item = itemRepository.save(ItemMapper.dtoToItem(requestDto));
        return ItemMapper.itemToDto(item);
    }


    public ResponseDto getItemById(Long id) {
        return ItemMapper.itemToDto(itemRepository.findById(id).orElseThrow(() -> new ServiceException("Item with id " + id + " does not exist")));
    }


    public ResponseDto getItemByTitle(String title) {
        return ItemMapper.itemToDto(itemRepository.findByTitle(title).orElseThrow(() -> new ServiceException("Item with title " + title + " does not exist")));
    }


    public List<ResponseDto> getAllItem() {
        if (itemRepository.findAll().isEmpty())
            throw new ServiceException("Item repository is empty");
        return itemRepository.findAll().stream().map(item -> ItemMapper.itemToDto(item)).toList();
    }


    public ResponseDto updateItem(Long id, RequestDto requestDto) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ServiceException("Item with id " + id + " does not exist"));
        item.setTitle(requestDto.title());
        item.setContent(requestDto.content());
        item.setAuthor(requestDto.author());
        return ItemMapper.itemToDto(itemRepository.save(item));
    }


    public ResponseDto deleteItem(Long id) {
        ResponseDto deletedItemDto = getItemById(id);  //проверится в том методе
        itemRepository.deleteById(id);
        return deletedItemDto;
    }
}

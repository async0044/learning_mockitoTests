package com.async.learning.mockitoTests.controller;

import com.async.learning.mockitoTests.dto.RequestDto;
import com.async.learning.mockitoTests.dto.ResponseDto;
import com.async.learning.mockitoTests.service.ItemService;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = Controller.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ItemService itemService;

    private Long testId = 1L;
    private RequestDto requestDto = new RequestDto("title", "content", "author");
    private ResponseDto responseDto = new ResponseDto(testId, "title", "content", "author");

    /*
    post addItemone
    get getItemoneById
    get getItemoneByTitle
    get getAllItems
    put updateItemoneById
    delete deleteItemone
     */

    @Test
    public void addItem_expectSucess() throws Exception {
        when(itemService.addItem(requestDto)).thenReturn(responseDto);

        mockMvc.perform(post("/one/addItem")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"title\", \"content\": \"content\", \"author\": \"author\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(testId))
                .andExpect(jsonPath("$.title").value("title"))
                .andExpect(jsonPath("$.content").value("content"))
                .andExpect(jsonPath("$.author").value("author"));
    }

    @Test
    public void addItem_expectSimilarTitle() throws Exception {
        when(itemService.addItem(requestDto)).thenThrow(ServiceException.class);

        mockMvc.perform(post("/one/addItem")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"title\", \"content\": \"content\", \"author\": \"author\"}"))
                .andExpect(status().isBadRequest());
    }
}

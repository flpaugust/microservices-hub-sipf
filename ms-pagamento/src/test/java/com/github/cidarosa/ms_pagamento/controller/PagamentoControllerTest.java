package com.github.cidarosa.ms_pagamento.controller;

import com.github.cidarosa.ms_pagamento.dto.PagamentoDTO;
import com.github.cidarosa.ms_pagamento.service.PagamentoService;
import com.github.cidarosa.ms_pagamento.service.exceptions.ResourceNotFoundException;
import com.github.cidarosa.ms_pagamento.tests.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class PagamentoControllerTest {

    @Autowired
    private MockMvc mockMvc; // para chamar o endpoint
// controller tem dependência do service
// dependência mockada

    @MockitoBean
    private PagamentoService service;
    private PagamentoDTO dto;
    private Long existingId;
    private Long nonExistiId;

    // converter para JSON o objeto Java e enviar na requisição
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() throws Exception {
        existingId = 1L;
        nonExistiId = 100L;

        // Criando um PagamentoDTO
        dto = Factory.createPagamentoDTO();

        // Listando PagamentoDTO
        List<PagamentoDTO> list = List.of(dto);

        // Simulando o comportamento do service - getAll
        Mockito.when(service.getAll()).thenReturn(list);

        // simulando o comportamento do service - getById
        // ID existe
        Mockito.when(service.getById(existingId)).thenReturn(dto);

        // ID não existe - lança exception
        Mockito.when(service.getById(nonExistiId)).thenThrow(ResourceNotFoundException.class);
    }




    @Test
    public void getAllShouldReturnListPagamentoDTO() throws Exception {

        // chamando requisição com o método GET em /pagamentos
        ResultActions result = mockMvc.perform(get("/pagamentos")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    public void getByIdShouldReturnPagamentoDTOWhenIdExist() throws Exception {
        ResultActions result = mockMvc.perform(get("/pagamentos/{id}", existingId)
                .accept(MediaType.APPLICATION_JSON));

        // Assertions
        result.andExpect(status().isOk());

        // verifica se tem os campos em result
        // $ - acessar o objeto result
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.valor").exists());
        result.andExpect(jsonPath("$.status").exists());
    }

    @Test
    public void getByIdShouldReturnNotFoundExceptionWhenIdDoesNotExist() throws Exception {
        ResultActions result = mockMvc.perform(get("/pagamentos/{id}", nonExistiId)
                .accept(MediaType.APPLICATION_JSON));

        // Assertions
        result.andExpect(status().isNotFound());
    }






}
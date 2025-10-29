package com.hybridchatbot.services;

import com.hybridchatbot.models.Genre;
import com.hybridchatbot.repository.GenreRepository;
import com.hybridchatbot.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public BaseResponse<?> listGenres(){
        List<Genre> genres = genreRepository.findAll();
        return new BaseResponse<>(200,"Ok", Optional.of(genres));
    }
}

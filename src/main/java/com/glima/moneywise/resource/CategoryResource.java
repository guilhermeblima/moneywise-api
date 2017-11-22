package com.glima.moneywise.resource;

import com.glima.moneywise.event.CreatedResourceEvent;
import com.glima.moneywise.model.Category;
import com.glima.moneywise.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Category> listAll(){
        return categoryRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Category> save(@Valid @RequestBody Category category, HttpServletResponse response){
        Category categorySaved = categoryRepository.save(category);
        publisher.publishEvent(new CreatedResourceEvent(this, response, categorySaved.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(categorySaved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findOne(@PathVariable Long id){
        Category category = categoryRepository.findOne(id);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }
}

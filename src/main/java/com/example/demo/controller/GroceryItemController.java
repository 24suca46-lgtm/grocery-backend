package com.example.demo.controller;

import com.example.demo.model.GroceryItem;
import com.example.demo.repository.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grocery-items")
@CrossOrigin(origins = "*")
public class GroceryItemController {
    @Autowired
    private GroceryItemRepository repository;

    @GetMapping
    public List<GroceryItem> getAllItems() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public GroceryItem getItem(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public GroceryItem createItem(@RequestBody GroceryItem item) {
        return repository.save(item);
    }

    @PutMapping("/{id}")
    public GroceryItem updateItem(@PathVariable Long id, @RequestBody GroceryItem item) {
        Optional<GroceryItem> existing = repository.findById(id);
        if (existing.isPresent()) {
            GroceryItem toUpdate = existing.get();
            toUpdate.setName(item.getName());
            toUpdate.setQuantity(item.getQuantity());
            toUpdate.setPurchased(item.isPurchased());
            return repository.save(toUpdate);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

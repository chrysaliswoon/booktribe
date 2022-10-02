package vttp.project.booktribe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.project.booktribe.model.Bookshelf;
import vttp.project.booktribe.repository.ShelfRepository;

@Service
public class ShelfService {
    
    @Autowired
    private ShelfRepository shelfRepo;

    public void save(List<Bookshelf> saveBook) {
        shelfRepo.saveBook(saveBook);
    }

}

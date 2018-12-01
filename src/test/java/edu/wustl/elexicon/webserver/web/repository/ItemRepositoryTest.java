package edu.wustl.elexicon.webserver.web.repository;

import edu.wustl.elexicon.webserver.web.domain.MappedItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void teststuff(){
        List<MappedItem> mappedItems = itemRepository.get();
        System.out.println(mappedItems);
    }

}
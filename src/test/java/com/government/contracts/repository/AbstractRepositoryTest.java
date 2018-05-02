package com.government.contracts.repository;

import com.government.contracts.model.Identifiable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * Created by vano on 29.4.18.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@EnableJpaRepositories(basePackages = "com.government.contracts.repository")
public abstract class AbstractRepositoryTest<T extends Identifiable<ID>, ID> {

    @Test
    public void testSave() {
        T entity = createEntity();
        T savedEntity = getRepository().save(entity);
        Assert.assertNotNull(savedEntity);
        Assert.assertNotNull(savedEntity.getId());
    }

    @Test
    public void testFindById() {
        T entity = createEntity();
        T savedEntity = getRepository().save(entity);
        Assert.assertNotNull(savedEntity);
        Assert.assertNotNull(savedEntity.getId());
        assertEntity(savedEntity);
        Optional<T> stored = getRepository().findById(savedEntity.getId());
        Assert.assertTrue(stored.isPresent());
    }

    @Test
    public void testFindAll() {
        T entity = createEntity();
        T savedEntity = getRepository().save(entity);
        Assert.assertNotNull(savedEntity);
        Assert.assertNotNull(savedEntity.getId());

        Iterable<T> stored = getRepository().findAll();
        Assert.assertTrue(stored.iterator().hasNext());

        stored.forEach(entityItem -> {
            Assert.assertNotNull(entityItem.getId());
        });
    }

    protected abstract T createEntity();

    protected abstract CrudRepository<T, ID> getRepository();

    protected abstract void assertEntity(T entity);
}

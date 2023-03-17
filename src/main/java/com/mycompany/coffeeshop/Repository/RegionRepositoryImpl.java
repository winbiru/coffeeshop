package com.mycompany.coffeeshop.Repository;

import com.mycompany.coffeeshop.Model.Country;
import com.mycompany.coffeeshop.Model.Region;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;
@Repository
public class RegionRepositoryImpl implements RegionRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public RegionRepositoryImpl(EntityManagerFactory entityManagerFactory) {
    }

    @Override
    public List<Region> findRegionsByCountry(String country) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Region> query = cb.createQuery(Region.class);
        Root<Region> region = query.from(Region.class);
        Join<Region, Country> countryJoin = region.join(country);
        query.select(region)
                .where(cb.equal(countryJoin.get(country), country));
        return entityManager.createQuery(query).getResultList();
    }
}

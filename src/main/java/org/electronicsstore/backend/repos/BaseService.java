package org.electronicsstore.backend.repos;

import java.util.List;

public interface BaseService<T, I> {
    List<T> findAll();
    <P> List<P> findAllBy(Class<P> clz);
    T findById(I id);
    <P> P findProjById(I id, Class<P> clz);
    List<T> findAllById(List<I> ids);
    <P> List<P> findAllProjByIds(List<I> ids, Class<P> clz);
    T createOne(T entity);
    T updateOne(I id, T entity);
    T patchOne(I id, T entity);
    void deleteOne(I id);
}

package Q3Project.DataAPIApplication.Interface;

import java.util.List;

public interface IGeneric<T> {
        List<T> GetAll();

        boolean Create(T entity);

        boolean Update(T entityOriginal, T entityDetails);

        void Delete(long id);
    }


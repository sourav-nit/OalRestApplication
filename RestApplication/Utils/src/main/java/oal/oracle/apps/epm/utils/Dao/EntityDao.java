package oal.oracle.apps.epm.utils.Dao;

import java.util.HashMap;
import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import oal.oracle.apps.epm.entities.BaseEntity;

public interface EntityDao {
    public BaseEntity create(final BaseEntity t) throws NotSupportedException, SystemException, RollbackException,
                                                        HeuristicMixedException, HeuristicRollbackException;
    public void delete(final Object id) throws NotSupportedException, SystemException, RollbackException,
                                               HeuristicMixedException, HeuristicRollbackException;
    public BaseEntity find(final Object id);
    public BaseEntity update(final BaseEntity t) throws NotSupportedException, SystemException, RollbackException,
                                                        HeuristicMixedException, HeuristicRollbackException;
    public List<BaseEntity> getData(int offset,int limit,HashMap<String,String> queryData);
    public void setEntity(Class temp);
    public void closeEntityManager();
}

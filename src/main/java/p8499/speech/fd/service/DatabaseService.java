package p8499.speech.fd.service;

import p8499.speech.fd.FilterExpr;
import p8499.speech.fd.OrderByListExpr;
import p8499.speech.fd.mask.DatabaseMask;
import p8499.speech.fd.bean.Database;
import p8499.speech.fd.mapper.fd.DatabaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service("databaseService")
public class DatabaseService {
  @Transactional(value = "fd_transaction", readOnly = true)
  public Database get(Integer dbid, DatabaseMask mask) {
    return databaseMapper.get(dbid, mask);
  }

  @Transactional(value = "fd_transaction")
  public Database add(Database bean) {
    if (!validatorFactory.getValidator().validate(bean, Insert.class).isEmpty()) {
      return null;
    }
    databaseMapper.add(bean);
    return bean;
  }

  @Transactional(value = "fd_transaction")
  public Database update(Database bean, DatabaseMask mask) {
    Set<ConstraintViolation<Database>> violationSet =
        validatorFactory.getValidator().validate(bean, Update.class);
    for (ConstraintViolation<Database> violation : violationSet)
      if (mask.get(violation.getPropertyPath().toString())) return null;
    databaseMapper.update(bean, mask);
    return bean;
  }

  public boolean delete(Integer dbid) {
    return databaseMapper.delete(dbid);
  }

  @Transactional(value = "fd_transaction")
  public void delete(FilterExpr filter) {
    databaseMapper.deleteWhere(filter);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public long count(FilterExpr filter) {
    return databaseMapper.count(filter);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public List<Database> query(
      FilterExpr filter, OrderByListExpr orderByList, long start, long count, DatabaseMask mask) {
    return databaseMapper.query(filter, orderByList, start, count, mask);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public boolean exists(Integer dbid) {
    return databaseMapper.exists(dbid);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer minDbid(FilterExpr filter, Integer defaultValue) {
    return databaseMapper.minDbid(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer maxDbid(FilterExpr filter, Integer defaultValue) {
    return databaseMapper.maxDbid(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer minDbstatus(FilterExpr filter, Integer defaultValue) {
    return databaseMapper.minDbstatus(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer maxDbstatus(FilterExpr filter, Integer defaultValue) {
    return databaseMapper.maxDbstatus(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer minDbmanager(FilterExpr filter, Integer defaultValue) {
    return databaseMapper.minDbmanager(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer maxDbmanager(FilterExpr filter, Integer defaultValue) {
    return databaseMapper.maxDbmanager(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer minDbspeaker(FilterExpr filter, Integer defaultValue) {
    return databaseMapper.minDbspeaker(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer maxDbspeaker(FilterExpr filter, Integer defaultValue) {
    return databaseMapper.maxDbspeaker(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer minDborig(FilterExpr filter, Integer defaultValue) {
    return databaseMapper.minDborig(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer maxDborig(FilterExpr filter, Integer defaultValue) {
    return databaseMapper.maxDborig(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public java.util.Date minDbcrtime(FilterExpr filter, java.util.Date defaultValue) {
    return databaseMapper.minDbcrtime(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public java.util.Date maxDbcrtime(FilterExpr filter, java.util.Date defaultValue) {
    return databaseMapper.maxDbcrtime(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer minDbdpstatus(FilterExpr filter, Integer defaultValue) {
    return databaseMapper.minDbdpstatus(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer maxDbdpstatus(FilterExpr filter, Integer defaultValue) {
    return databaseMapper.maxDbdpstatus(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer minDbdpc(FilterExpr filter, Integer defaultValue) {
    return databaseMapper.minDbdpc(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer maxDbdpc(FilterExpr filter, Integer defaultValue) {
    return databaseMapper.maxDbdpc(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer minDbdpc0(FilterExpr filter, Integer defaultValue) {
    return databaseMapper.minDbdpc0(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer maxDbdpc0(FilterExpr filter, Integer defaultValue) {
    return databaseMapper.maxDbdpc0(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer minDbdpc1(FilterExpr filter, Integer defaultValue) {
    return databaseMapper.minDbdpc1(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer maxDbdpc1(FilterExpr filter, Integer defaultValue) {
    return databaseMapper.maxDbdpc1(filter, defaultValue);
  }

  @Value(value = "#{databaseMapper}")
  protected DatabaseMapper databaseMapper;

  @Value(value = "#{validatorFactory}")
  protected LocalValidatorFactoryBean validatorFactory;
}
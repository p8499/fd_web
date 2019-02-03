package p8499.speech.fd.service;

import p8499.speech.fd.FilterExpr;
import p8499.speech.fd.OrderByListExpr;
import p8499.speech.fd.mask.DiphoneMask;
import p8499.speech.fd.bean.Diphone;
import p8499.speech.fd.mapper.fd.DiphoneMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service("diphoneService")
public class DiphoneService {
  @Transactional(value = "fd_transaction", readOnly = true)
  public Diphone get(Integer dpid, DiphoneMask mask) {
    return diphoneMapper.get(dpid, mask);
  }

  @Transactional(value = "fd_transaction")
  public Diphone add(Diphone bean) {
    if (!validatorFactory.getValidator().validate(bean, Insert.class).isEmpty()) {
      return null;
    }
    diphoneMapper.add(bean);
    return bean;
  }

  @Transactional(value = "fd_transaction")
  public Diphone update(Diphone bean, DiphoneMask mask) {
    Set<ConstraintViolation<Diphone>> violationSet =
        validatorFactory.getValidator().validate(bean, Update.class);
    for (ConstraintViolation<Diphone> violation : violationSet)
      if (mask.get(violation.getPropertyPath().toString())) return null;
    diphoneMapper.update(bean, mask);
    return bean;
  }

  public boolean delete(Integer dpid) {
    return diphoneMapper.delete(dpid);
  }

  @Transactional(value = "fd_transaction")
  public void delete(FilterExpr filter) {
    diphoneMapper.deleteWhere(filter);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public long count(FilterExpr filter) {
    return diphoneMapper.count(filter);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public List<Diphone> query(
      FilterExpr filter, OrderByListExpr orderByList, long start, long count, DiphoneMask mask) {
    return diphoneMapper.query(filter, orderByList, start, count, mask);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public boolean exists(Integer dpid) {
    return diphoneMapper.exists(dpid);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer minDpid(FilterExpr filter, Integer defaultValue) {
    return diphoneMapper.minDpid(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer maxDpid(FilterExpr filter, Integer defaultValue) {
    return diphoneMapper.maxDpid(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer minDplnid(FilterExpr filter, Integer defaultValue) {
    return diphoneMapper.minDplnid(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer maxDplnid(FilterExpr filter, Integer defaultValue) {
    return diphoneMapper.maxDplnid(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer minDpstatus(FilterExpr filter, Integer defaultValue) {
    return diphoneMapper.minDpstatus(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer maxDpstatus(FilterExpr filter, Integer defaultValue) {
    return diphoneMapper.maxDpstatus(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer minDpspeaker(FilterExpr filter, Integer defaultValue) {
    return diphoneMapper.minDpspeaker(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public Integer maxDpspeaker(FilterExpr filter, Integer defaultValue) {
    return diphoneMapper.maxDpspeaker(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public java.util.Date minDprctime(FilterExpr filter, java.util.Date defaultValue) {
    return diphoneMapper.minDprctime(filter, defaultValue);
  }

  @Transactional(value = "fd_transaction", readOnly = true)
  public java.util.Date maxDprctime(FilterExpr filter, java.util.Date defaultValue) {
    return diphoneMapper.maxDprctime(filter, defaultValue);
  }

  @Value(value = "#{diphoneMapper}")
  protected DiphoneMapper diphoneMapper;

  @Value(value = "#{validatorFactory}")
  protected LocalValidatorFactoryBean validatorFactory;
}
package p8499.speech.fd.controller.base;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import p8499.speech.fd.*;
import p8499.speech.fd.bean.Role;
import p8499.speech.fd.mask.RoleMask;
import p8499.speech.fd.service.RoleService;

public abstract class RoleControllerBase {
  protected static final String path = "api/Role/";
  protected static final String attachmentPath = "api/Role_attachment/";
  protected static final String pathKey = "{rlid}";

  @RequestMapping(
    value = path + pathKey,
    method = RequestMethod.GET,
    produces = "application/json;charset=UTF-8"
  )
  public String get(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      @PathVariable String rlid,
      @RequestParam(required = false) String mask)
      throws Exception {
    RoleMask maskObj =
        mask == null || mask.equals("")
            ? new RoleMask().all(true)
            : jackson.readValue(mask, RoleMask.class);
    Role bean = onGet(session, request, response, rlid, maskObj);
    return jackson.writeValueAsString(bean);
  }

  protected abstract Role onGet(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      String rlid,
      RoleMask mask)
      throws Exception;

  @RequestMapping(
    value = path + pathKey,
    method = RequestMethod.POST,
    produces = "application/json;charset=UTF-8"
  )
  public String add(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      @PathVariable String rlid,
      @RequestBody Role bean)
      throws Exception {
    onAdd(session, request, response, rlid, bean);
    return jackson.writeValueAsString(bean);
  }

  protected abstract Role onAdd(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      String rlid,
      Role bean)
      throws Exception;

  @RequestMapping(
    value = path + pathKey,
    method = RequestMethod.PUT,
    produces = "application/json;charset=UTF-8"
  )
  public String update(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      @PathVariable String rlid,
      @RequestBody Role bean,
      @RequestParam(required = false) String mask)
      throws Exception {
    RoleMask maskObj =
        mask == null || mask.equals("")
            ? new RoleMask().all(true)
            : jackson.readValue(mask, RoleMask.class);
    onUpdate(session, request, response, rlid, bean, maskObj);
    return jackson.writeValueAsString(bean);
  }

  protected abstract Role onUpdate(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      String rlid,
      Role bean,
      RoleMask mask)
      throws Exception;

  @RequestMapping(
    value = path + pathKey,
    method = RequestMethod.DELETE,
    produces = "application/json;charset=UTF-8"
  )
  public void delete(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      @PathVariable String rlid)
      throws Exception {
    onDelete(session, request, response, rlid);
  }

  protected abstract void onDelete(
      HttpSession session, HttpServletRequest request, HttpServletResponse response, String rlid)
      throws Exception;

  @RequestMapping(
    value = path,
    method = RequestMethod.GET,
    produces = "application/json;charset=UTF-8"
  )
  public String query(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      @RequestParam(required = false) String filter,
      @RequestParam(required = false) String orderBy,
      @RequestHeader(required = false, name = "Range", defaultValue = "items=0-9") String range,
      @RequestParam(required = false) String mask)
      throws Exception {
    FilterExpr filterObj =
        filter == null || filter.equals("") ? null : jackson.readValue(filter, FilterExpr.class);
    OrderByListExpr orderByListObj =
        orderBy == null || orderBy.equals("") ? null : OrderByListExpr.fromQuery(orderBy);
    RangeExpr rangeObj = RangeExpr.fromQuery(range);
    RoleMask maskObj =
        mask == null || mask.equals("")
            ? new RoleMask().all(true)
            : jackson.readValue(mask, RoleMask.class);
    Long total = onCount(session, request, response, filterObj);
    if (total == null) return null;
    long start = rangeObj.getStart(total);
    long count = rangeObj.getCount(total);
    List<Role> results =
        onQuery(session, request, response, filterObj, orderByListObj, start, count, maskObj);
    response.setHeader(
        "Content-Range", RangeListExpr.getContentRange(start, results.size(), total));
    return jackson.writeValueAsString(results);
  }

  protected abstract Long onCount(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      FilterExpr filter)
      throws Exception;

  protected abstract List<Role> onQuery(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      FilterExpr filter,
      OrderByListExpr orderByList,
      long start,
      long count,
      RoleMask mask)
      throws Exception;

  @RequestMapping(
    value = attachmentPath + pathKey,
    method = RequestMethod.GET,
    produces = "application/octet-stream"
  )
  public void downloadAttachment(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      @PathVariable String rlid,
      @RequestParam(required = true) String name)
      throws Exception {
    InputStream input = inputStream(session, request, response, rlid, name);
    if (input == null) return;
    String contentType = URLConnection.guessContentTypeFromName(name);
    response.setContentType(contentType == null ? "application/octet-stream" : contentType);
    response.setHeader("Content-Disposition", "attachment;fileName=" + name);
    StreamUtils.copy(input, response.getOutputStream());
    input.close();
    response.getOutputStream().close();
  }

  protected abstract InputStream inputStream(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      String rlid,
      String name)
      throws Exception;

  @RequestMapping(
    value = attachmentPath + pathKey,
    method = RequestMethod.PUT,
    produces = "application/json;charset=UTF-8"
  )
  public void uploadAttachment(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      @PathVariable String rlid,
      @RequestParam(required = true) String name)
      throws Exception {
    OutputStream output = outputStream(session, request, response, rlid, name);
    if (output == null) return;
    StreamUtils.copy(request.getInputStream(), output);
    request.getInputStream().close();
    output.close();
  }

  protected abstract OutputStream outputStream(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      String rlid,
      String name)
      throws Exception;

  @RequestMapping(
    value = attachmentPath + pathKey,
    method = RequestMethod.DELETE,
    produces = "application/json;charset=UTF-8"
  )
  public void deleteAttachment(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      @PathVariable String rlid,
      @RequestParam(required = true) String name)
      throws Exception {
    onDeleteAttachment(session, request, response, rlid, name);
  }

  protected abstract void onDeleteAttachment(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      String rlid,
      String name)
      throws Exception;

  @RequestMapping(
    value = attachmentPath + pathKey,
    method = RequestMethod.GET,
    produces = "application/json;charset=UTF-8"
  )
  public String listAttachments(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      @PathVariable String rlid)
      throws Exception {
    List<String> result = onListAttachments(session, request, response, rlid);
    return jackson.writeValueAsString(result);
  }

  protected abstract List<String> onListAttachments(
      HttpSession session, HttpServletRequest request, HttpServletResponse response, String rlid)
      throws Exception;

  @Value(value = "#{jackson}")
  protected ObjectMapper jackson;

  @Value(value = "#{roleService}")
  protected RoleService roleService;
}
package p8499.speech.fd.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import p8499.speech.fd.FilterExpr
import p8499.speech.fd.FilterLogicExpr
import p8499.speech.fd.OrderByListExpr
import p8499.speech.fd.bean.Authority
import p8499.speech.fd.bean.Database
import p8499.speech.fd.bean.Diphone
import p8499.speech.fd.controller.base.DatabaseControllerBase
import p8499.speech.fd.mask.DatabaseMask
import p8499.speech.fd.mask.UserMask
import p8499.speech.fd.service.DiphoneService
import p8499.speech.fd.service.RoleAuthorityService
import p8499.speech.fd.service.UserService
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

/**
 * Created by jdeuser on 2019/2/2.
 */
@RestController
class DatabaseController : DatabaseControllerBase() {
    override fun onGet(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, dbid: Int?, mask: DatabaseMask?): Database? {
        return databaseService[dbid, mask] ?: run { response.status = HttpServletResponse.SC_NOT_FOUND; return null }
    }

    override fun onAdd(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, bean: Database?): Database? {
        bean ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        val usid = session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        roleAuthorityService.check(usid, Authority.AUID_DATABASE).takeIf { it } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        return databaseService.add(bean
                .setDbstatus(Database.DBSTATUS_INITIALIZED)
                .setDbmanager(usid)
                .setDborig(usid)
                .setDbcrtime(Date())) ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
    }

    override fun onUpdate(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, dbid: Int?, bean: Database?, mask: DatabaseMask?): Database? {
        response.status = HttpServletResponse.SC_FORBIDDEN
        return null
    }

    @RequestMapping(value = [(path + pathKey + "/rename/")], method = [(RequestMethod.PUT)], produces = ["application/json;charset=UTF-8"])
    fun rename(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, @PathVariable dbid: Int?, @RequestParam dbname: String?): Database? {
        session.isSigned.takeIf { it } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        dbid ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        val target = databaseService[dbid, DatabaseMask().setDbmanager(true)] ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        (target.dbmanager == session.usid).takeIf { it } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        return databaseService.update(target.setDbname(dbname), DatabaseMask().setDbname(true)) ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
    }

    @RequestMapping(value = [(path + pathKey + "/handover/")], method = [(RequestMethod.PUT)], produces = ["application/json;charset=UTF-8"])
    fun handover(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, @PathVariable dbid: Int?, @RequestParam usalias: String?, @RequestParam usfrom: String?): Database? {
        session.isSigned.takeIf { it } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        dbid ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        usalias ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        usfrom ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        val target = databaseService[dbid, DatabaseMask().setDbmanager(true)] ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        (target.dbmanager == session.usid).takeIf { it } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        val manager = userService[usalias, usfrom, UserMask()]?.usid ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        return databaseService.update(target.setDbmanager(manager), DatabaseMask().setDbmanager(true)) ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
    }

    @RequestMapping(value = [(path + pathKey + "/assign/")], method = [(RequestMethod.PUT)], produces = ["application/json;charset=UTF-8"])
    fun assign(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, @PathVariable dbid: Int?, @RequestParam usalias: String?, @RequestParam usfrom: String?): Database? {
        session.isSigned.takeIf { it } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        dbid ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        usalias ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        usfrom ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        val target = databaseService[dbid, DatabaseMask().setDbmanager(true)] ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        (target.dbmanager == session.usid).takeIf { it } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        val speaker = userService[usalias, usfrom, UserMask()]?.usid ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        return databaseService.update(target.setDbspeaker(speaker), DatabaseMask().setDbspeaker(true)) ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
    }

    @RequestMapping(value = [(path + pathKey + "/promote/")], method = [(RequestMethod.PUT)], produces = ["application/json;charset=UTF-8"])
    fun promote(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, @PathVariable dbid: Int?): Database? {
        session.isSigned.takeIf { it } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        dbid ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        val target = databaseService[dbid, DatabaseMask().setDbmanager(true)] ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        (target.dbmanager == session.usid).takeIf { it } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        return when (target.dbstatus) {
            Database.DBSTATUS_INITIALIZED -> {
                diphoneService.count(FilterLogicExpr().equalsNumber(Diphone.FIELD_DPDBID, dbid)).takeIf { it > 0L } ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
                diphoneService.count(FilterLogicExpr().equalsNumber(Diphone.FIELD_DPDBID, dbid).append(FilterLogicExpr().equalsNumber(Diphone.FIELD_DPSTATUS, Diphone.DPSTATUS_RECORDED).not())).takeIf { it == 0L } ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
                databaseService.update(target.setDbstatus(Database.DBSTATUS_DIPHONES_PREPARED), DatabaseMask().setDbstatus(true)) ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
            }
            Database.DBSTATUS_DIPHONES_PREPARED -> databaseService.update(target.setDbstatus(Database.DBSTATUS_BUILD_PENDING), DatabaseMask().setDbstatus(true)) ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
            else -> run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        }
    }

    @RequestMapping(value = [(path + pathKey + "/demote/")], method = [(RequestMethod.PUT)], produces = ["application/json;charset=UTF-8"])
    fun demote(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, @PathVariable dbid: Int?): Database? {
        session.isSigned.takeIf { it } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        dbid ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        val target = databaseService[dbid, DatabaseMask().setDbmanager(true)] ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        (target.dbmanager == session.usid).takeIf { it } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        return when (target.dbstatus) {
            Database.DBSTATUS_DIPHONES_PREPARED -> databaseService.update(target.setDbstatus(Database.DBSTATUS_INITIALIZED), DatabaseMask().setDbstatus(true)) ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
            Database.DBSTATUS_BUILD_PENDING -> databaseService.update(target.setDbstatus(Database.DBSTATUS_DIPHONES_PREPARED), DatabaseMask().setDbstatus(true)) ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
            else -> run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        }
    }

    override fun onDelete(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, dbid: Int?) {
        //after all the statuses are designed
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCount(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, filter: FilterExpr?): Long? {
        return databaseService.count(filter)
    }

    override fun onQuery(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, filter: FilterExpr?, orderByList: OrderByListExpr?, start: Long, count: Long, mask: DatabaseMask?): MutableList<Database>? {
        return databaseService.query(filter, orderByList, start, count, mask)
    }

    override fun inputStream(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, dbid: Int?, name: String?): InputStream? {
        return File(session.servletContext.getRealPath("$attachmentFolder/${Database.NAME}/$dbid/$name")).takeIf { it.exists() }?.inputStream() ?: run { response.status = HttpServletResponse.SC_NOT_FOUND; return null }
    }

    override fun outputStream(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, dbid: Int?, name: String?): OutputStream? {
        session.isSigned.takeIf { it } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        dbid ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        val target = databaseService[dbid, DatabaseMask().setDbmanager(true)] ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        (target.dbmanager == session.usid).takeIf { it } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        val file = File(session.servletContext.getRealPath("$attachmentFolder/${Database.NAME}/$dbid/$name"))
        return when (name) {
            "icon.png" -> {
                if (!file.parentFile.exists()) file.parentFile.mkdirs()
                file.outputStream()
            }
            else -> run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        }
    }

    override fun onDeleteAttachment(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, dbid: Int?, name: String?) {
        session.isSigned.takeIf { it } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        dbid ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return }
        val target = databaseService[dbid, DatabaseMask().setDbmanager(true)] ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return }
        (target.dbmanager == session.usid).takeIf { it } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        val file = File(session.servletContext.getRealPath("$attachmentFolder/${Database.NAME}/$dbid/$name")).takeIf { it.exists() } ?: kotlin.run { response.status = HttpServletResponse.SC_NO_CONTENT; return }
        when (name) {
            "icon.png" -> file.delete()
            else -> run { response.status = HttpServletResponse.SC_BAD_REQUEST; return }
        }
    }

    override fun onListAttachments(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, dbid: Int?): MutableList<String>? {
        return File(session.servletContext.getRealPath("$attachmentFolder/${Database.NAME}/$dbid")).takeIf { it.exists() }?.list()?.toMutableList()
    }

    @RequestMapping(value = [(attachmentPath + pathKey + "/md5/")], method = [(RequestMethod.GET)], produces = ["application/json;charset=UTF-8"])
    fun md5(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, @PathVariable dbid: Int?, @RequestParam name: String?): String? {
        return File(session.servletContext.getRealPath("$attachmentFolder/${Database.NAME}/$dbid/$name")).takeIf { it.exists() }?.md5 ?: run { response.status = HttpServletResponse.SC_NOT_FOUND; return null }
    }

    @Value(value = "\${app.attachmentFolder}")
    protected lateinit var attachmentFolder: String
    @Value(value = "#{roleAuthorityService}")
    protected lateinit var roleAuthorityService: RoleAuthorityService
    @Value(value = "#{userService}")
    protected lateinit var userService: UserService
    @Value(value = "#{diphoneService}")
    protected lateinit var diphoneService: DiphoneService
}
package p8499.speech.fd.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import p8499.speech.fd.FilterExpr
import p8499.speech.fd.OrderByListExpr
import p8499.speech.fd.bean.Database
import p8499.speech.fd.bean.Diphone
import p8499.speech.fd.controller.base.DiphoneControllerBase
import p8499.speech.fd.mask.DatabaseMask
import p8499.speech.fd.mask.DiphoneMask
import p8499.speech.fd.service.DatabaseService
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

/**
 * Created by jdeuser on 2019/2/2.
 */
@RestController
class DiphoneController : DiphoneControllerBase() {
    override fun onGet(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, dpid: Int?, mask: DiphoneMask?): Diphone? {
        return diphoneService.get(dpid, mask) ?: run { response.status = HttpServletResponse.SC_NOT_FOUND; return null }
    }

    override fun onAdd(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, bean: Diphone?): Diphone? {
        response.status = HttpServletResponse.SC_FORBIDDEN
        return null
    }

    override fun onUpdate(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, dpid: Int?, bean: Diphone?, mask: DiphoneMask?): Diphone? {
        response.status = HttpServletResponse.SC_FORBIDDEN
        return null
    }

    @RequestMapping(value = [(path + pathKey + "/promote/")], method = [(RequestMethod.PUT)], produces = ["application/json;charset=UTF-8"])
    fun promote(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, @PathVariable dpid: Int?): Diphone? {
        session.isSigned.takeIf { it } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        dpid ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        val database = databaseService.getFromDiphone(dpid, DatabaseMask().setDbspeaker(true)) ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        (database.dbspeaker == session.usid).takeIf { it } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        val target = diphoneService.get(dpid, DiphoneMask().setDpstatus(true)) ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        return when (target.dpstatus) {
            Diphone.DPSTATUS_INITIALIZED -> {
                File(session.servletContext.getRealPath("$attachmentFolder/employee/$dpid/record.wav")).takeIf { it.exists() } ?: kotlin.run { response.status = HttpServletResponse.SC_BAD_REQUEST; null }
                diphoneService.update(target.setDpstatus(Diphone.DPSTATUS_RECORDED).setDpspeaker(session.usid), DiphoneMask().setDpstatus(true).setDpspeaker(true)) ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; null }
            }
            else -> run { response.status = HttpServletResponse.SC_BAD_REQUEST; null }
        }
    }

    @RequestMapping(value = [(path + pathKey + "/demote/")], method = [(RequestMethod.PUT)], produces = ["application/json;charset=UTF-8"])
    fun demote(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, @PathVariable dpid: Int?): Diphone? {
        session.isSigned.takeIf { it } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        dpid ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        val database = databaseService.getFromDiphone(dpid, DatabaseMask().setDbspeaker(true)) ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        (database.dbspeaker == session.usid).takeIf { it } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        val target = diphoneService.get(dpid, DiphoneMask().setDpstatus(true)) ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        return when (target.dpstatus) {
            Diphone.DPSTATUS_RECORDED -> {
                File(session.servletContext.getRealPath("$attachmentFolder/${Diphone.NAME}/$dpid/record.wav")).delete()
                diphoneService.update(target.setDpstatus(Diphone.DPSTATUS_INITIALIZED).setDpspeaker(null), DiphoneMask().setDpstatus(true).setDpspeaker(true)) ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
            }
            else -> run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        }
    }

    override fun onDelete(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, dpid: Int?) {
        response.status = HttpServletResponse.SC_FORBIDDEN
    }

    override fun onCount(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, filter: FilterExpr?): Long? {
        return diphoneService.count(filter)
    }

    override fun onQuery(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, filter: FilterExpr?, orderByList: OrderByListExpr?, start: Long, count: Long, mask: DiphoneMask?): MutableList<Diphone>? {
        return diphoneService.query(filter, orderByList, start, count, mask)
    }

    override fun inputStream(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, dpid: Int?, name: String?): InputStream? {
        return File(session.servletContext.getRealPath("$attachmentFolder/${Diphone.NAME}/$dpid/$name")).takeIf { it.exists() }?.inputStream() ?: run { response.status = HttpServletResponse.SC_NOT_FOUND; return null }
    }

    override fun outputStream(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, dpid: Int?, name: String?): OutputStream? {
        session.isSigned.takeIf { it } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        dpid ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        val database = databaseService.getFromDiphone(dpid, DatabaseMask().setDbstatus(true).setDbspeaker(true)) ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        (database.dbspeaker == session.usid).takeIf { it } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        val target = diphoneService.get(dpid, DiphoneMask().setDpstatus(true)) ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        val file = File(session.servletContext.getRealPath("$attachmentFolder/${Diphone.NAME}/$dpid/$name"))
        return when (name) {
            "record.wav" -> {
                (database.dbstatus == Database.DBSTATUS_DIPHONES_PREPARED && target.dpstatus == Diphone.DPSTATUS_INITIALIZED).takeIf { it } ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
                if (!file.parentFile.exists()) file.parentFile.mkdirs()
                file.outputStream()
            }
            else -> run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        }
    }

    override fun onDeleteAttachment(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, dpid: Int?, name: String?) {
        session.isSigned.takeIf { it } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        dpid ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return }
        val database = databaseService.getFromDiphone(dpid, DatabaseMask().setDbstatus(true).setDbspeaker(true)) ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return }
        (database.dbspeaker == session.usid).takeIf { it } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        val target = diphoneService.get(dpid, DiphoneMask().setDpstatus(true)) ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return }
        val file = File(session.servletContext.getRealPath("$attachmentFolder/${Diphone.NAME}/$dpid/$name"))
        when (name) {
            "record.wav" -> {
                (database.dbstatus == Database.DBSTATUS_DIPHONES_PREPARED && target.dpstatus == Diphone.DPSTATUS_INITIALIZED).takeIf { it } ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return }
                file.delete()
            }
            else -> run { response.status = HttpServletResponse.SC_BAD_REQUEST; return }
        }
    }

    override fun onListAttachments(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, dpid: Int?): MutableList<String>? {
        return File(session.servletContext.getRealPath("$attachmentFolder/${Diphone.NAME}/$dpid")).takeIf { it.exists() }?.list()?.toMutableList()
    }

    @Value(value = "\${app.attachmentFolder}")
    protected lateinit var attachmentFolder: String
    @Value(value = "#{databaseService}")
    protected lateinit var databaseService: DatabaseService

}
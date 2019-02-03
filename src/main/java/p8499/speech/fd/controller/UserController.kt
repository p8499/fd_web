package p8499.speech.fd.controller

import org.apache.commons.codec.digest.DigestUtils
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import p8499.speech.fd.FilterExpr
import p8499.speech.fd.OrderByListExpr
import p8499.speech.fd.bean.User
import p8499.speech.fd.controller.base.UserControllerBase
import p8499.speech.fd.mask.UserMask
import java.io.InputStream
import java.io.OutputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

/**
 * Created by jdeuser on 2019/2/1.
 */
@RestController
class UserController : UserControllerBase() {
    override fun onGet(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, usid: Int?, mask: UserMask?): User? {
        response.status = HttpServletResponse.SC_FORBIDDEN
        return null
    }

    override fun onAdd(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, bean: User?): User? {
        response.status = HttpServletResponse.SC_FORBIDDEN
        return null
    }

    override fun onUpdate(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, usid: Int?, bean: User?, mask: UserMask?): User? {
        response.status = HttpServletResponse.SC_FORBIDDEN
        return null
    }

    override fun onDelete(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, usid: Int?) {
        response.status = HttpServletResponse.SC_FORBIDDEN
    }

    override fun onCount(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, filter: FilterExpr?): Long? {
        response.status = HttpServletResponse.SC_FORBIDDEN
        return null
    }

    override fun onQuery(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, filter: FilterExpr?, orderByList: OrderByListExpr?, start: Long, count: Long, mask: UserMask?): MutableList<User>? {
        response.status = HttpServletResponse.SC_FORBIDDEN
        return null
    }

    override fun inputStream(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, usid: Int?, name: String?): InputStream? {
        response.status = HttpServletResponse.SC_FORBIDDEN
        return null
    }

    override fun outputStream(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, usid: Int?, name: String?): OutputStream? {
        response.status = HttpServletResponse.SC_FORBIDDEN
        return null
    }

    override fun onDeleteAttachment(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, usid: Int?, name: String?) {
        response.status = HttpServletResponse.SC_FORBIDDEN
    }

    override fun onListAttachments(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, usid: Int?): MutableList<String>? {
        response.status = HttpServletResponse.SC_FORBIDDEN
        return null
    }

    @RequestMapping(value = [(path + "signin/")], method = [(RequestMethod.GET)], produces = ["application/json;charset=UTF-8"])
    fun signin(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, @RequestParam usalias: String?, @RequestParam usfrom: String?, @RequestParam uspswd: String?) {
        usalias ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        usfrom ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        uspswd ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        val user = userService.get(usalias, usfrom, UserMask().setUsid(true).setUspswd(true)) ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        user.uspswd.takeIf { it == DigestUtils.md5Hex(uspswd) } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        session.setAttribute("usid", user.usid)
    }

    @RequestMapping(value = [(path + "status/")], method = [(RequestMethod.GET)], produces = ["application/json;charset=UTF-8"])
    fun status(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse) {
        session.getAttribute("usid") ?: run { response.status = HttpServletResponse.SC_NO_CONTENT; return }
    }

    @RequestMapping(value = [(path + "signout/")], method = [(RequestMethod.GET)], produces = ["application/json;charset=UTF-8"])
    fun signout(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse) {
        session.getAttribute("usid") ?: run { response.status = HttpServletResponse.SC_NO_CONTENT; return }
        session.removeAttribute("usid")
    }
}
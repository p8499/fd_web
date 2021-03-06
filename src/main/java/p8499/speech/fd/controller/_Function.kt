package p8499.speech.fd.controller

import org.apache.commons.codec.digest.DigestUtils
import p8499.speech.fd.FilterLogicExpr
import p8499.speech.fd.bean.*
import p8499.speech.fd.mask.DatabaseMask
import p8499.speech.fd.mask.UserMask
import p8499.speech.fd.service.DatabaseService
import p8499.speech.fd.service.RoleAuthorityService
import p8499.speech.fd.service.UserService
import java.io.File
import java.security.MessageDigest
import javax.servlet.http.HttpSession

/**
 * Created by jdeuser on 2019/2/1.
 */
val HttpSession.isSigned: Boolean get() = usid != null
val HttpSession.usid: Int? get() = getAttribute("usid") as? Int?

operator fun UserService.get(usalias: String, usfrom: String, mask: UserMask): User? = query(FilterLogicExpr().equalsString(User.FIELD_USALIAS, usalias).equalsString(User.FIELD_USFROM, usfrom), null, 0, 1, mask)?.takeUnless { it.isEmpty() }?.get(0)

fun RoleAuthorityService.check(usid: Int, auid: String): Boolean = count(
        FilterLogicExpr().equalsString(RoleAuthority.FIELD_RAAUID, auid).existsObject(
                UserRole.TABLE, FilterLogicExpr().equalsField(UserRole.FIELD_URRLID, RoleAuthority.FIELD_RARLID).equalsNumber(UserRole.FIELD_URUSID, usid))) > 0

fun DatabaseService.getFromDiphone(dpid: Int, mask: DatabaseMask): Database? = query(FilterLogicExpr().existsObject(Diphone.TABLE, FilterLogicExpr().equalsField(Diphone.FIELD_DPDBID, Database.FIELD_DBID).equalsNumber(Diphone.FIELD_DPID, dpid)), null, 0, 1, mask)?.takeUnless { it.isEmpty() }?.get(0)

val ByteArray.md5: String
    get() =
        StringBuffer().also {
            for (b in MessageDigest.getInstance("MD5").digest(this)) {
                it.append(Integer.toHexString(b.toInt() and 0xff).padStart(2, '0'))
            }
        }.toString()
val File.md5: String
    get() {
        val input = inputStream()
        val md5 = input.readBytes().md5
        input.close()
        return md5
    }

fun main(args: Array<String>) {
    println(DigestUtils.md5Hex("123456"))
}

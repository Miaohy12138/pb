package net.miaohy.pb.common.vo;

import cn.hutool.core.util.ObjectUtil;
import net.miaohy.pb.modules.entity.PbUserBasic;
import org.springframework.core.NamedThreadLocal;

public class PbUserManager {

    static  ThreadLocal<PbUserBasic> bosuserThreadLocal = new NamedThreadLocal<PbUserBasic>("bosuseridentity 对象共享");

    public static Integer getUserId() {
        PbUserBasic pbUserBasic = bosuserThreadLocal.get();
        return ObjectUtil.isNull(pbUserBasic)? 0:pbUserBasic.getId();
    }

    public static PbUserBasic getBosUser() {
        PbUserBasic pbUserBasic = bosuserThreadLocal.get();
        return ObjectUtil.isNull(pbUserBasic)?null: pbUserBasic;
    }

    public static void setBosUser(PbUserBasic pbUserBasic) {
        bosuserThreadLocal.set(pbUserBasic);
    }
}

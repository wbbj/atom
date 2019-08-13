package wbb.ssm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wbb.ssm.dao.GarbageDao;
import wbb.ssm.entity.Garbage;
import wbb.ssm.service.GarbageService;

@Service
public class GarbageServiceImpl implements GarbageService {
    @Autowired
    private GarbageDao garbageDao;

    //根据垃圾名称查询垃圾
    public List<Garbage> getGarbageByGname(String gname) {
        //这里是反编译后的结果直接转换成了?:的形式
        return garbageDao.getGarbageByGname(gname) != null && !garbageDao.getGarbageByGname(gname).isEmpty() ? garbageDao.getGarbageByGname(gname) : null;
    }

    //根据垃圾名称查询是否有此垃圾
    public String queryGarbage(String gname) {
        //同上
        return garbageDao.getGarbageByGname(gname) != null && !garbageDao.getGarbageByGname(gname).isEmpty() ? "1" : "-1";
    }

    //添加垃圾信息
    public String createGarbage(String gname, String variety, String describe, String handle) {
        List<Garbage> hasgarbage = garbageDao.getGarbageByGname(gname);
        if (hasgarbage != null && !hasgarbage.isEmpty()) {
            return "-1";
        } else {
            Garbage garbage = new Garbage();
            garbage.setGname(gname);
            garbage.setVariety(variety);
            garbage.setDescribe(describe);
            garbage.setHandle(handle);
            garbageDao.insertGarbage(garbage);
            return "1";
        }
    }
}

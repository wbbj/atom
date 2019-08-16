package wbb.ssm.service;

import java.util.List;
import wbb.ssm.entity.Garbage;

public interface GarbageService {
    /**
     * 根据垃圾名称获取垃圾信息
     * @param gname
     * @return
     */
    List<Garbage> getGarbageByGname(String gname);

    /**
     * 快捷查询垃圾信息
     * @param gname
     * @return
     */
    String queryGarbage(String gname);

    /**
     * 创建新的垃圾信息
     * @param gname
     * @param variety
     * @param describe
     * @param handle
     * @return
     */

    String createGarbage(String gname, String variety, String describe, String handle);
}
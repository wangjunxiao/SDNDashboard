package cn.dlut.dao;

import java.util.List;

import cn.dlut.entity.Line;

public interface LineDao   extends IBaseDao<Object>{
    public List<Line> getLineByVisible (int vi);
    public List<Line> getAvailableLine();
    public Line getByLineName(String name);
    public Line getByLineId(int id);
    public Line getByLineChiName(String name);
}

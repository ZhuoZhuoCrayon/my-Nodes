package cn.com.sm.service.impl;

import cn.com.sm.mapper.LogsMapper;
import cn.com.sm.po.Log;
import cn.com.sm.po.Result;
import cn.com.sm.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogsServiceImpl implements BaseService<Log> {
    @Autowired
    private LogsMapper logsMapper;
    @Override
    public List<Log> findAll(){
        try{
            return logsMapper.findAll();
        }catch (Exception e){
            System.out.println(e.toString());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Log> findById(String id){
        return new ArrayList<>();
    }

    //重构findById--由于id类型改变
    public List<Log> findById(Integer id){
        try{
            return logsMapper.findById(id);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Result insert(Log log){
        try{
            String formatInfo = checkFormat(log);
            if(!formatInfo.equals("pass")){
                return new Result(false,formatInfo);
            }else if(findById(log.getLogid()).size()!=0){
                return new Result(false,
                        "logid:[" + log.getLogid() + "] existed");
            }else{
                logsMapper.insert(log);
                return new Result(true,
                        "insert in logs successfully");
            }

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"insert[" +
                    log.getLogid() + "] in logs Failed\n" + e.getMessage());
        }

    }

    @Override
    public Result update(Log log){
        //purchasesMapper.update(purchase);
        try{
            String formatInfo = checkFormat(log);
            if(!formatInfo.equals("pass")){
                return new Result(false,formatInfo);
            }else if(findById(log.getLogid()).size()==0){
                return new Result(false,
                        "logid:[" + log.getLogid() + "] not existed");
            }else{
                logsMapper.update(log);
                return new Result(true,
                        "update [" + log.getLogid() +
                                "] in logs successfully");
            }

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"update[" +
                    log.getLogid() + "] in logs Failed\n" + e.getMessage());
        }
    }

    @Override
    public Result delete(String... ids){
        return null;
    }

    @Override
    public String checkFormat(Log log) {
        if(log.getWho()==null||log.getWho().length()>10){
            return "who is null or too long";
        }else if(log.getTime()==null){
            return "time is null";
        }else if(log.getOperation()==null||log.getOperation().length()>6){
            return "operation is null or too long";
        }else if(log.getTable_name()==null||log.getTable_name().length()>20){
            return "table_name is null or too long";
        }
        return "pass";
    }


    //重构delete--由于id类型改变
    public Result delete(Integer ...ids){
        try {
            for (Integer id : ids) {
                if (logsMapper.findById(id).size() == 0){
                    return new Result(false,
                            id + "not existed in logs");
                }
            }
            String idStr = "";
            for(Integer id : ids){
                logsMapper.delete(id);
                idStr += "[" + id + "]";
            }
            return new Result(true,
                    "delete" + idStr +
                            "in logs successfully");
        }catch (Exception e){
            return new Result(false,
                    "error from delete on logs\n" +
                            e.getMessage());
        }
    }


}

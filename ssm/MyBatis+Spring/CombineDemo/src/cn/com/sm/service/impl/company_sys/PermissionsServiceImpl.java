package cn.com.sm.service.impl.company_sys;

import cn.com.sm.mapper.company_sys.PermissionsMapper;
import cn.com.sm.po.Result;
import cn.com.sm.po.company_sys.Permission;
import cn.com.sm.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionsServiceImpl implements BaseService<Permission> {
    @Autowired
    private PermissionsMapper permissionsMapper;
    @Override
    public List<Permission> findAll(){
        try{
            return permissionsMapper.findAll();
        }catch (Exception e){
            System.out.println(e.toString());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Permission> findById(String id){
        return new ArrayList<>();
    }

    //重构findById--由于id类型改变
    public List<Permission> findById(Long id){
        try{
            return permissionsMapper.findById(id);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Result insert(Permission permission){
        try{
            String formatInfo = checkFormat(permission);
            if(!formatInfo.equals("pass")){
                return new Result(false,formatInfo);
            }else if(findById(permission.getId()).size()!=0){
                return new Result(false,
                        "[" + permission.getPermission() + "] existed");
            }else{
                permissionsMapper.insert(permission);
                return new Result(true,
                        "insert in permissions successfully");
            }

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"insert[" +
                    permission.getPermission() + "] in permissions Failed\n" + e.getMessage());
        }

    }

    @Override
    public Result update(Permission permission){
        //purchasesMapper.update(purchase);
        try{
            String formatInfo = checkFormat(permission);
            if(!formatInfo.equals("pass")){
                return new Result(false,formatInfo);
            }else if(findById(permission.getId()).size()==0){
                return new Result(false,
                        "[" + permission.getId() + "] not existed");
            }else{
                permissionsMapper.update(permission);
                return new Result(true,
                        "update [" + permission.getPermission() +
                                "] in permissions successfully");
            }

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"update[" +
                    permission.getPermission() + "] in permissions Failed\n" + e.getMessage());
        }
    }

    @Override
    public Result delete(String... ids){
        return null;
    }

    @Override
    public String checkFormat(Permission permission) {
        if(permission.getId()==null||permission.getPermission()==null||permission.getPerms()==null){
            return "Existed null value";
        }
        return "pass";
    }


    //重构delete--由于id类型改变
    public Result delete(Long ...ids){
        try {
            for (Long id : ids) {
                if (permissionsMapper.findById(id).size() == 0){
                    return new Result(false,
                            id + "not existed in permissions");
                }
            }
            String idStr = "";
            for(Long id : ids){
                permissionsMapper.delete(id);
                idStr += "[" + id + "]";
            }
            return new Result(true,
                    "delete" + idStr +
                            "in permissions successfully");
        }catch (Exception e){
            return new Result(false,
                    "error from delete on permissions\n" +
                            e.getMessage());
        }
    }
}

package cn.com.sm.service.impl.company_sys;

import cn.com.sm.mapper.company_sys.RolesMapper;
import cn.com.sm.po.Result;
import cn.com.sm.po.company_sys.Role;
import cn.com.sm.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RolesServiceImpl implements BaseService<Role> {
    @Autowired
    private RolesMapper rolesMapper;
    @Override
    public List<Role> findAll(){
        try{
            return rolesMapper.findAll();
        }catch (Exception e){
            System.out.println(e.toString());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Role> findById(String id){
        return new ArrayList<>();
    }

    //重构findById--由于id类型改变
    public List<Role> findById(Long id){
        try{
            return rolesMapper.findById(id);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Result insert(Role role){
        try{
            String formatInfo = checkFormat(role);
            if(!formatInfo.equals("pass")){
                return new Result(false,formatInfo);
            }else if(findById(role.getId()).size()!=0){
                return new Result(false,
                        "[" + role.getRole() + "] existed");
            }else{
                rolesMapper.insert(role);
                return new Result(true,
                        "insert in roles successfully");
            }

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"insert[" +
                    role.getRole() + "] in roles Failed\n" + e.getMessage());
        }

    }

    @Override
    public Result update(Role role){
        //purchasesMapper.update(purchase);
        try{
            String formatInfo = checkFormat(role);
            if(!formatInfo.equals("pass")){
                return new Result(false,formatInfo);
            }else if(findById(role.getId()).size()==0){
                return new Result(false,
                        "[" + role.getRole() + "] not existed");
            }else{
                rolesMapper.update(role);
                return new Result(true,
                        "update [" + role.getRole() +
                                "] in roles successfully");
            }

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"update[" +
                    role.getRole() + "] in roles Failed\n" + e.getMessage());
        }
    }

    @Override
    public Result delete(String... ids){
        return null;
    }

    @Override
    public String checkFormat(Role role) {
        if(role.getId()==null||role.getRole()==null||role.getDescription()==null){
            return "Existed null value";
        }
        return "pass";
    }


    //重构delete--由于id类型改变
    public Result delete(Long ...ids){
        try {
            for (Long id : ids) {
                if (rolesMapper.findById(id).size() == 0){
                    return new Result(false,
                            id + "not existed in roles");
                }
            }
            String idStr = "";
            for(Long id : ids){
                rolesMapper.delete(id);
                idStr += "[" + id + "]";
            }
            return new Result(true,
                    "delete" + idStr +
                            "in roles successfully");
        }catch (Exception e){
            return new Result(false,
                    "error from delete on roles\n" +
                            e.getMessage());
        }
    }
}

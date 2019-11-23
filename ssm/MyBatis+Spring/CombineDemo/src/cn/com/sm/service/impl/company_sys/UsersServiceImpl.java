package cn.com.sm.service.impl.company_sys;

import cn.com.sm.mapper.company_sys.UsersMapper;
import cn.com.sm.po.Result;
import cn.com.sm.po.company_sys.User;
import cn.com.sm.service.PasswordHelper;
import cn.com.sm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private PasswordHelper passwordHelper;

    @Override
    public User findByName(String username) {
        try{
            return usersMapper.findByName(username);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> findById(Long id) {
        try{
            return usersMapper.findById(id);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Result delete(Long... ids) {
        try{
            for(Long id:ids){
                usersMapper.delete(id);
            }
            return new Result(true,"delete successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"error");
        }
    }

    @Override
    public Result changePassword(Long id, String newPassword) {
        try {
            User user = usersMapper.findById(id).get(0);
            user.setPassword(newPassword);
            passwordHelper.encryptPassword(user);
            usersMapper.update(user);
            return new Result(true,"success");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"error");
        }
    }

    @Override
    public Result insert(User user) {
        try{
            passwordHelper.encryptPassword(user);
            usersMapper.update(user);
            return new Result(true,"success");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"error");
        }
    }

    @Override
    public Result update(User user) {
        try{
            passwordHelper.encryptPassword(user);
            usersMapper.update(user);
            return new Result(true,"update success");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"update error");
        }
    }

    @Override
    public Result delete(String... ids) {
        return null;
    }

    @Override
    public String checkFormat(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findById(String id) {
        return null;
    }
}

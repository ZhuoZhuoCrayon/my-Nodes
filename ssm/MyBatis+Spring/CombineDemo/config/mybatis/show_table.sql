# 查看表
drop procedure if exists show_table;
create procedure show_table(in table_name varchar(20))
begin
    ## 连接sql语句，编辑为可执行sql语句
    set @selectAll = concat('select * from ',table_name);
    prepare selectAll from @selectAll;
    execute selectAll;
end;

# 统计指定商品的月销售情况
drop procedure if exists report_monthly_sale;
create procedure report_monthly_sale(in pid varchar(4))
begin
    select pro.pname,
           date_format(pur.ptime,'%b') as month,
           date_format(pur.ptime,'%Y') as year,
           sum(pur.total_price) as total, # 每月销售的总金额
           sum(pur.qty) as sales, # 每月销售总量
           sum(pur.total_price)/sum(pur.qty) as avgPrice # 每月的平均销售价格

    from purchases pur inner join products pro
    on pro.pid = pur.pid
    where pro.pid = pid
    # 按年和月份进行分组
    group by extract(year from pur.ptime),extract(month from pur.ptime);
end;

# 新建订单
drop procedure if exists add_purchase;
create procedure add_purchase(in _purid int(11),in _cid varchar(4),
                              in _eid varchar(3),in _pid varchar(4),
                              in _qty int(5))
begin
    declare _total_price decimal(7,2);
    declare new_pur_id int(11);
    declare isExe boolean default false;
    declare message varchar(255);

    # 获取插入id
    select max(purid)+1 into new_pur_id
    from purchases;

    # 提示库存不足
    if _qty > (select p.qoh from products p where p.pid = _pid) then
        set isExe = false;
    else
        select _qty*(1-pro.discnt_rate)*original_price into _total_price
        from products pro
        where pro.pid = _pid;

        insert into purchases
            value(new_pur_id,_cid,_eid,_pid,_qty,now(),_total_price);

        set isExe = true;
    end if;
    set message = @update_qoh_meg;
    select isExe,message;
end;

# 插入订单后响应
drop trigger if exists update_qoh;
create trigger update_qoh
    after insert on purchases for each row
    begin
        # now_qoh:保存更新后的qoh
        declare old_qoh int(5);
        declare now_qoh int(5);
        set old_qoh = (select pro.qoh from products pro where pro.pid = NEW.pid);
        set now_qoh = old_qoh - NEW.qty;
        set @update_qoh_meg := concat('Now qoh:',now_qoh ,'.\n');

        update products pro
            set pro.qoh = now_qoh
        where pro.pid = NEW.pid;

        # 低于阈值
        if now_qoh < (select pro.qoh_threshold from products pro where pro.pid = NEW.pid) then
            update products pro
                set pro.qoh = 2*old_qoh
            where pro.pid = NEW.pid;

            set @update_qoh_meg := concat(@update_qoh_meg, 'Below qoh_threshold',
                                    ',The product has been increased by ',(old_qoh + NEW.qty) + '.');
        end if;

        # 更新客户数据
        update customers cus
            set
                cus.visits_made = cus.visits_made + 1,
                cus.last_visit_time = NEW.ptime
        where cus.cid = NEW.cid;
    end;

# 将一个元组插入购买表中
drop trigger if exists log_purchase;
create trigger log_purchase
    after insert on purchases for each row
    begin
        insert into logs
        value (null,NEW.eid,NEW.ptime,'purchases','insert',NEW.purid);
    end;

# 更新products表的qoh属性
drop trigger if exists log_product_qoh;
create trigger log_product_qoh
    after update on products for each row
    begin
        if OLD.qoh <> NEW.qoh then
            insert into logs
            value (null,'system',now(),'products','update',NEW.pid);
        end if;
    end;

# 更新客户表的visits_made属性
drop trigger if exists log_custom_vis;
create trigger log_custom_vis
    after update on customers for each row
    begin
        if OLD.visits_made <> NEW.visits_made then
            insert into logs
            value (null,'system',now(),'customers','update',NEW.cid);
        end if;
    end;
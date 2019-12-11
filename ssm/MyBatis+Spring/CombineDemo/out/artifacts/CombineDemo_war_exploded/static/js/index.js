axios.defaults.headers.post['Content-Type'] = 'application/json';
new Vue({
    el:'#app',

    data:{
        //用户数据
        username:null,
        role:null,
        //表格等动态数据
        tableData: [],
        header: [],
        initHeaders: {
            'employees':['eid','ename','city'],
            'customers':['cid','cname','city','visits_made','last_visit_time'],
            'suppliers':['sid','sname','city','telephone_no'],
            'products':['pid','pname','qoh','qoh_threshold','original_price','discnt_rate','sid'],
            'purchases':['purid','cid','eid','pid','qty','ptime','total_price'],
            'logs':['logid','who','time','table_name','operation','key_value'],
            'monthly':['pname','month','year','total','sales','avgPrice']
        },
        form: {},
        timeArr:null,

        //格式
        formLabelWidth: '80px',
        columnWidth:220,
        optionWidth:250,

        //动态变量
        searchKeyword:"",
        selectArr:"",
        activeName:"employees",
        //所查找的pid
        searchPid:'',

        //bool判断
        loadings:true, //true表示开始加载
        isAdd:true,
        dialogFormVisible: false,   //提交窗口
        dialogErrorVisible:false,   //错误窗口
        dialogMonthVisible:false,   //查找窗口


    },
    created() {
        this.getTable(this.activeName);
        axios
            .get("/user/getUser.do")
            .then(respone=>{
                this.username = respone.data.username;
                this.role = respone.data.role;
            })
            .catch(error=>{
                this.dialogErrorVisible = true;
            })
    },
    methods:{
        setLoading(){
            this.loadings = true;
            //设置加载延时为2500
            setTimeout(() => {
                this.loadings = false
            }, 2500)
        },
        logout(){
            //退出登录
            window.location.href = "/logout";
        },
        add() {
            this.isAdd = true;
            this.dialogFormVisible = true
        },
        update() {
            //解决：空值及长度为0字符串的处理
            //将YYYY-MM-DD HH:ii:ss格式转换为时间戳
            console.log("time:" + this.timeArr);
            if(this.timeArr!=null){
                this.form[this.timeArr] = new Date(this.form[this.timeArr]).getTime();
            }

            //将空串转换为null
            for(let idx in this.form){
                if(this.form[idx].toString().length===0){
                    this.form[idx] = null;
                }
            }
            if(this.isAdd){
                this.changeTable(this.activeName,
                    "/" + this.activeName + "/insert.do",'insert',this.form);
            }else{
                this.changeTable(this.activeName,
                    "/" + this.activeName + "/update.do",'update',this.form)
            }
            this.dialogFormVisible = false;
            this.form = {};
        },
        search() {
            this.tableData =  this.tableData.filter(item=>{
                if(this.selectArr===""||this.selectArr=="all"){
                    for(let arr in item){
                        //console.log(item[arr]);
                        if(item!=null&&item[arr].toString().indexOf(this.searchKeyword)!=-1)
                            return true;
                    }
                    return false;
                }else{
                    return item!=null&&item[this.selectArr].
                    toString().indexOf(this.searchKeyword)!=-1;
                }
            });
            this.searchKeyword = '';
            this.$message({
                type: 'success',
                message: 'search amount:  ' + this.tableData.length
            })
        },
        handleEdit(index, row) {
            this.form = this.tableData[index];
            this.isAdd = false;
            this.dialogFormVisible = true;
        },
        handleDelete(index, row) {
            this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                ids = [];
                ids.push(this.tableData[index][this.header[0]]);
                this.changeTable(this.activeName,
                    "/" + this.activeName + "/delete.do",'delete',ids);
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                })
            })
        },
        cancel() {
            this.dialogFormVisible = false
        },
        cancelSearch(){
            this.dialogMonthVisible = false;
        },
        searchMonth(){
            axios({
                method:"get",
                url:"/products/reportMonthlySale.do",
                headers:{
                    'Content-type': 'application/x-www-form-urlencoded'
                },
                params:{
                    'id':this.searchPid
                },
            }).then((respone)=>{
                this.tableData = respone.data;
                this.dialogMonthVisible = false;
            }).catch(error=>{
                this.$message({
                    type: 'error',
                    message: 'Get Error'
                })
            })
        },
        handleClick(tab, event) {
            if(tab.label!="date") {
                if(tab.label=="monthly"){
                    this.tableData = [];
                    this.header = this.initHeaders['monthly'];
                    this.optionWidth = Math.ceil(window.innerWidth/this.header.length);
                    this.dialogMonthVisible=true;
                }else {
                    this.getTable(tab.label);
                }
            }else{
                axios
                    .get("/date.do")
                    .then(respone=>{
                        this.$notify({
                            title: '距离暑假(2021-7-15)',
                            message: respone.data + "天",
                            duration: 0
                        });
                    })
                    .catch(error=>{
                        this.dialogErrorVisible = true;
                    })
            }
        },
        getTable(tableName){
            /**
             * 在函数体中this.tableData报错原因：回调函数处于其它函数的内部this不会与任何对象绑定，为undefined
             * 解决方法：使用钩子函数
             * 解决2：在外面给this赋值，_this = this，用_this在函数体内访问Vue的数据
             */
            this.setLoading();
            this.header = this.initHeaders[tableName];
            this.optionWidth = Math.ceil(window.innerWidth/this.header.length);
            this.timeArr = null;    //含有time的属性，初始化为空
            axios
                .get("/" + tableName + "/detail.do")
                .then(respone=>{
                    this.tableData = respone.data;
                    //将时间戳转为YYYY-MM-DD HH:ii:ss
                    for(let idx in this.header){
                        if(this.header[idx].toString().includes("time")){
                            //上一次由于将该刷新放置外层，导致NaN
                            this.timeArr = this.header[idx];
                            this.tableData.map(item=>{
                                item[this.header[idx]] = jutils.formatDate(
                                    new Date(item[this.header[idx]]),"YYYY-MM-DD HH:ii:ss");
                            })
                        }
                    }
                    this.loadings = false;  //查找成功，手动关闭动画
                })
                .catch(error=>{
                    this.tableData = [];
                    this.loadings = false;
                    this.dialogErrorVisible = true;
                });
        },
        changeTable(tableName,url,operation,data){
            axios({
                method:'post',
                url:url,
                data:JSON.stringify(data)
            }).then(response => {
                this.getTable(this.activeName);
                if(response.data.success!=true){
                    //this.dialogErrorVisible = true;
                    this.$message({
                        type: 'error',
                        message: response.data.message
                    })
                }else {
                    if(tableName!='logs') {
                        let t = 0;
                        let key = null;
                        for (let i in data) {
                            key = data[i];
                            break;
                        }
                        this.submitLog(tableName, operation, key);
                        this.$message({
                            type: 'success',
                            message: response.data.message
                        })
                    }
                }
            }).catch(function (error) { // 请求失败处理
                this.dialogErrorVisible = true;
            });
        },
        submitLog(tableName,operation,key){
            log = {
                logid:null,
                who:this.username,
                time:Date.now(),
                table_name:tableName,
                operation:operation,
                key_value:key
            };
            axios({
                method:'post',
                url:'/logs/insert.do',
                data:JSON.stringify(log)
            }).then(response => {
                if(response.data.success!=true){
                    //this.dialogErrorVisible = true;
                    this.$message({
                        type: 'error',
                        message: response.data.message
                    })
                }else {
                    this.$message({
                        type: 'success',
                        message: response.data.message
                    })
                }
            }).catch(function (error) { // 请求失败处理
                this.dialogErrorVisible = true;
            });
        }
    }
})
new Vue({
    el: '#app',
    data: {
        products: [
            {
                "pid": "pr00",
                "pname": "Milk",
                "qoh": 12,
                "qoh_threshold": 10,
                "original_price": 2.4,
                "discnt_rate": 0.1,
                "sid": "s0"
            }, {
                "pid": "pr01",
                "pname": "Egg",
                "qoh": 20,
                "qoh_threshold": 10,
                "original_price": 1.5,
                "discnt_rate": 0.2,
                "sid": "s1"
            }, {
                "pid": "pr02",
                "pname": "Bread",
                "qoh": 15,
                "qoh_threshold": 10,
                "original_price": 1.2,
                "discnt_rate": 0.1,
                "sid": "s0"
            }, {
                "pid": "pr03",
                "pname": "Pineapple",
                "qoh": 6,
                "qoh_threshold": 5,
                "original_price": 2.0,
                "discnt_rate": 0.3,
                "sid": "s0"
            }, {
                "pid": "pr04",
                "pname": "Knife",
                "qoh": 10,
                "qoh_threshold": 8,
                "original_price": 2.5,
                "discnt_rate": 0.2,
                "sid": "s1"
            }, {
                "pid": "pr05",
                "pname": "Shovel",
                "qoh": 5,
                "qoh_threshold": 5,
                "original_price": 7.99,
                "discnt_rate": 0.1,
                "sid": "s0"
            }
        ],
        form: {
            purid: 100007,
            cid: null,
            eid: null,
            pid: null,
            qty: 1,
            ptime: null,
            total_price: null
        },
        purchase: {
            purid: 100007,
            cid: null,
            eid: null,
            pid: null,
            qty: 1,
            ptime: null,
            total_price: null
        },

        cids: ['c000', 'c001', 'c002'],
        eids: ['e00', 'e02', 'e03'],
        selectProduct: {},

        //用户
        username:'username',
        role:'role',

        //格式
        formLabelWidth: '60px',

        //bool判断
        loadings: true, //true表示开始加载
        isAdd: true,
        dialogFormVisible: false,   //提交窗口

        //步骤
        step: {
            active: 0,
            processStatus: 'finish'
        },
        steps: {
            'Choose': 0,
            'Buy': 1,
            'Fill_Form': 2,
            'Confirm': 3,
            'Status': 4
        },

        //表单规则
        rules: {
            cid: [
                {required: true, message: 'please select cid', trigger: 'change'}
            ],
            eid: [
                {required: true, message: 'please select eid', trigger: 'change'}
            ],
        },
    },
    created() {
        this.getProducts();
        axios
            .get("/user/getUser.do")
            .then(respone=>{
                this.username = respone.data.username;
                this.role = respone.data.role;
            })
            .catch(error=>{
                this.$message({
                    type: 'error',
                    message: '网络无连接'
                });
            })
    },
    methods: {
        trade(product) {
            this.getIds('employees');
            this.getIds('customers');
            this.selectProduct = product;
            for (let i in this.purchase) {
                this.purchase[i] = this.form[i];
            }
            this.purchase.pid = product.pid;
            this.purchase.total_price = product.original_price * (1 - product.discnt_rate);
            this.dialogFormVisible = true;

            //set step
            this.step.processStatus = 'finish';
            this.step.active = this.steps.Buy;
        },
        handleChange() {
            this.purchase.total_price =
                this.selectProduct.original_price * (1 - this.selectProduct.discnt_rate) * this.purchase.qty;
        },
        sign_out(){
            //退出登录
            console.log("yes");
            window.location.href = "/logout";
        },
        gotoManage(){
            window.location.href = "/index.html";
        },
        cancelSubmit(formName) {
            this.dialogFormVisible = false;
            this.$refs[formName].resetFields();

            for (let i in this.purchase) {
                this.purchase[i] = this.form[i];
            }

            //set step
            this.step.active = this.steps.Choose;
            this.step.processStatus = 'finish';

        },
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    //set step
                    this.step.active = this.steps.Confirm;
                    this.step.processStatus = 'finish';

                    this.$confirm('Please make sure you have confirmed the transaction information',
                        'Notice', {
                            confirmButtonText: 'Confirm',
                            cancelButtonText: 'Cancel',
                            type: 'warning'
                        }).then(() => {
                        //console.log(this.purchase);
                        //提交订单
                        this.requestByData('post', '/purchases/trade.do', this.purchase);
                        this.cancelSubmit('purchase');
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: 'cancel trade'
                        })
                    })
                } else {
                    //set step
                    this.step.active = this.steps.Fill_Form;
                    this.step.processStatus = 'error';
                    return false;
                }
            });
        },
        /**
         * @return 返回请求数据
         * @param method
         * @param url
         * @param data
         */
        requestByData(method, url, data) {
            axios({
                method: method,
                url: url,
                headers: {
                    'Content-type': 'application/json'
                },
                data: JSON.stringify(data)
            }).then(response => {
                if (response.data.success != true) {
                    //set step
                    this.step.active = this.steps.Status;
                    this.step.processStatus = 'error';

                    this.$message({
                        type: 'error',
                        message: response.data.message
                    })
                } else {
                    this.$message({
                        type: 'success',
                        message: response.data.message
                    });

                    //set step
                    this.step.active = this.steps.Status;
                    this.step.processStatus = 'success';
                }
            }).catch(error => {
                //set step
                this.step.active = this.steps.Status;
                this.step.processStatus = 'error';

                this.$message({
                    type: 'error',
                    message: '网络无连接'
                });
                return null;
            })
        },
        getProducts() {
            /**
             * 在函数体中this.tableData报错原因：回调函数处于其它函数的内部this不会与任何对象绑定，为undefined
             * 解决方法：使用钩子函数
             * 解决2：在外面给this赋值，_this = this，用_this在函数体内访问Vue的数据
             */
            axios
                .get("/products/detail.do")
                .then(respone => {
                    this.products = respone.data;
                })
                .catch(error => {
                    this.$message({
                        type: 'error',
                        message: '网络无连接'
                    });
                });
        },
        getIds(tableName){
            url = '/customers/getIds.do';
            if(tableName==='employees')
                url = '/employees/getIds.do';
            axios
                .get(url)
                .then(respone => {
                    if(tableName==='employees')
                        this.eids = respone.data;
                    else
                        this.cids = respone.data;
                })
                .catch(error => {
                    console.log(error);
                });
        }
    }
});
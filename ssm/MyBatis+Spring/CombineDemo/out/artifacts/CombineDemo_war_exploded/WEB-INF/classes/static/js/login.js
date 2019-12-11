new Vue({

    el:'#app',

    data:{
        form: {
            username: '',
            password: '',
            remember: false,
        }
    },
    methods: {
        onSubmit() {
            axios({
                method:"post",
                url:"/login.do",
                headers:{
                    'Content-type': 'application/x-www-form-urlencoded'
                },
                params:{
                    username: this.form.username,
                    password: this.form.password,
                    remember: this.form.remember
                },
            }).then((respone)=>{
                if(respone.data.success){
                    window.location.href="mall.html";
                    this.form = {};
                }else{
                    this.$message({
                        message: respone.data.message,
                        type: 'warning',
                        duration: 6000
                    })
                }
            }).catch(error=>{
                console.log(error);
                this.$message({
                    type: 'error',
                    message: '网络无连接'
                })
            })
        }
    }
});
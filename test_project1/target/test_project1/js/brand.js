new Vue({
    el:"#app",
    data:{
        brandList:[],
        brand:{},
        title:"品牌添加",
        page: 1,  //显示的是哪一页 当前角标
        pageSize: 10, //每一页显示的数据条数
        total: 0, //记录总数
        maxPage:9,
        selectedID:[],
        searchObj:{}
    },
    methods:{
        pageHandler:function (page) {
            this.page = page;
            var _this =this;
            axios.post("/brand/findPage.do?curPage="+this.page+"&pageSize="+this.pageSize,this.searchObj).then(function (response) {
                var pageResult = response.data.message;
                _this.brandList = pageResult.rows;
                _this.total = pageResult.total;
            }).catch(function (reason) {
                console.log(reason);
            });
        },
        add:function(){
            this.title = "品牌添加";
            this.brand ={};
        },
        update:function(item){
            this.title = "品牌编辑";
            this.brand = JSON.parse(JSON.stringify(item))
        },
        save:function () {
            var _this = this;
            var url = '';
            if (_this.brand.id != null){
                url = '/brand/updateBrand.do' //更新
            } else {
                url = '/brand/addBrand.do';  //添加
            }
            axios.post(url, _this.brand)
                .then(function (response) {
                    if (response.data.success){
                        alert(response.data.message);
                        //刷新页面
                        _this.pageHandler(1);
                    } else {
                        alert(response.data.message);
                    }
                })
        },

        deleteSelection:function (event,brand) {
            //判断当前是否选中
            if(event.target.checked){
                //如果选中  添加记录id
                this.selectedID.push(brand.id);
            }else {
                //如果不选中(取消选中)   原来 记录id 移除
                var idx = this.selectedID.indexOf(brand.id);
                this.selectedID.splice(idx,1);
            }
        },
        deleteBrand:function () {
            var _this = this;
            axios.post("/brand/deleteBrandWithId.do",Qs.stringify({ids:this.selectedID},{indices:false}))
                .then(function (response) {
                    if (response.data.success){
                        alert(response.data.message);
                        //刷新页面
                        _this.pageHandler(1);
                    } else {
                        alert(response.data.message);
                    }
                });

        }
    },
    created:function () {
        this.pageHandler(1);
    }

});
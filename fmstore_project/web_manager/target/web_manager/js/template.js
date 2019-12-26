Vue.component('v-select', VueSelect.VueSelect);
new Vue({
    el:"#app",
    data:{
        tempList:[],
        temp:{},
        searchTemp:{},
        page: 1,  //显示的是哪一页
        pageSize: 10, //每一页显示的数据条数
        total: 150, //记录总数
        maxPage:9,
        selectIds:[], //记录选择了哪些记录的id

        brandsOptions: [],
        placeholder: '可以进行多选',
        selectBrands: [],
        sel_brand_obj: [],

        specOptions: [],
        selectSpecs: [],
        sel_spec_obj: [],

        addname:''
    },
    methods:{
        pageHandler: function (page) {
            _this = this;
            this.page = page;
            axios.post("/temp/search.do?page="+page+"&pageSize="+this.pageSize,this.searchTemp)
                .then(function (response) {
                    //取服务端响应的结果
                    _this.tempList = response.data.message.rows;
                    _this.total = response.data.message.total;
                    //console.log(response.data);
                }).catch(function (reason) {
                console.log(reason);
            })
        },

        /*
        * [{"id":4,"name":"小米"},{"id":2,"name":"华为"}]
        *  小米,华为
        * */
        jsonToString:function (jsonStr,key) {
            //1.将传入的字符串转成json
            var jsonObj = JSON.parse(jsonStr);
            var value = "";
            for(var i=0; i<jsonObj.length;i++){
                if (i > 0){
                    value +=",";
                }
                value += jsonObj[i][key];
            }
            return value;
        },

        selected_brand: function(values){
            this.selectBrands =values.map(function(obj){
                return obj.id;
            });
            /*console.log(this.selectBrands);*/
            console.log(this.sel_brand_obj);
        },

        selected_spec: function(values){
            this.selectSpecs =values.map(function(obj){
                return obj.id
            });
            console.log(this.sel_spec_obj);
        },

        selLoadData:function () {
            var _this = this;
            axios.get("/brand/selectOptionList.do")
                .then(function (response) {
                  console.log(response.data);
                    _this.brandsOptions = response.data;
                }).catch(function (reason) {
                console.log(reason);
            });
            axios.get("/spec/selectOptionList.do")
                .then(function (response) {
                    _this.specOptions = response.data;
                }).catch(function (reason) {
                console.log(reason);
            });
        },
        addClick:function () {

            this.addname = '';
            this.sel_brand_obj = [];
            this.sel_spec_obj = [];
        },
        deleteSelection:function (event, id) {
            if(event.target.checked){
                //如果选中  添加记录id
                this.selectIds.push(id);
            }else {
                //如果不选中(取消选中)   原来 记录id 移除
                var idx = this.selectIds.indexOf(id);
                this.selectIds.splice(idx,1);
            }
        },

        save:function () {
            var entity = {
                name:this.addname,
                specIds:this.sel_spec_obj,
                brandIds:this.sel_brand_obj
            };
            axios.post(url,entity)
                .then(function (response) {
                    console.log(response);
                    _this.pageHandler(1);
                }).catch(function (reason) {
                console.log(reason);
            });
        },
        deleteTemp:function () {
            var _this = this;
            axios.post("/temp/deleteTempWithId.do",Qs.stringify({ids:this.selectIds},{indices:false}))
                .then(function (response) {
                    if (response.data.success){
                        alert(response.data.message);
                        //刷新页面
                        _this.pageHandler(1);
                    } else {
                        alert(response.data.message);
                    }
                });
        },
        editClick:function (tempItem) {
            var _this = this;
            _this.addname = tempItem.name;
        }
    },
    created: function() {
        this.pageHandler(1);
        this.selLoadData();
    }

});
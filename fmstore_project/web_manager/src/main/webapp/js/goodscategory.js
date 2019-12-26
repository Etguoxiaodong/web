new Vue({
    el: "#app",
    data:{
        categoryList:[],
        grade:1, /*当前级别*/
        gradeEntity1:{}, /*面包屑导航1*/
        gradeEntity2:{}, /*面包屑导航2*/
        selectedID:[],
        selectedParentIds:[],
        category:{
            parentId:0,
            name:'',
            typeId:44
        }//当前正在添加的分类
    },
    methods: {
        selectCateByParentId: function (id) {
            var _this = this;
            axios.post("/itemCat/findByParentId.do?parentId="+id)
                .then(function (response) {
                    //取服务端响应的结果
                    _this.categoryList =response.data;
                    console.log(response);
                    console.log(_this.gradeEntity1.id)
                }).catch(function (reason) {
                console.log(reason);
            })
        },
        setGrade:function(grade){
            this.grade = grade;
        },
        nextGrade:function (item) {
            if (this.grade ==1){
                this.gradeEntity1 = {};
                this.gradeEntity2 = {};
            }
            if (this.grade == 2){
                this.gradeEntity1 = item;
                this.gradeEntity2 = {};
            }
            if (this.grade == 3){
                this.gradeEntity2 = item;
            }
            this.selectCateByParentId(item.id);
        },
        saveClick:function () {
            var _this = this;
            if(this.gradeEntity2.id != null){
            this.category.parentId = this.gradeEntity2.id;
            }else if (this.gradeEntity1.id != null){
                this.category.parentId = this.gradeEntity1.id;
            }
            axios.post("/itemCat/saveCategory.do",this.category).then(function (response){
               if(response.data.success == true){
                   alert(response.data.message);
                   _this.selectCateByParentId(_this.category.parentId);
               }else {
                   alert(response.data.message);
               }
            }).catch(function (reason) {
                console.log(reason);
            });
        },
        deleteSelection:function (event,item) {
            //判断当前是否选中
            if(event.target.checked){
                //如果选中  添加记录id
                this.selectedID.push(item.id);
            }else {
                //如果不选中(取消选中)   原来 记录id 移除
                var idx = this.selectedID.indexOf(item.id);
                this.selectedID.splice(idx,1);
            }
        },
        deleteClick:function () {
            var _this = this;
            axios.post("/itemCat/deleteCatWithId.do",Qs.stringify({ids:this.selectedID},{indices:false}))
                .then(function (response) {
                    if (response.data.success){
                        alert(response.data.message);
                        //刷新页面
                        if(_this.grade == 1){
                            _this.selectCateByParentId(0);
                        }else if(_this.grade == 2){
                            _this.selectCateByParentId(_this.gradeEntity1.id);
                        }else{
                            _this.selectCateByParentId(_this.gradeEntity2.id);
                        }
                    } else {
                        alert(response.data.message);
                    }
                });
        }
        
    },
    created: function () {
        this.selectCateByParentId(0);
    }
});
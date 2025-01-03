const base = {
    get() {
        return {
            url : "http://172.29.14.248/:8081/springbootnp4n3/",
            name: "springbootnp4n3",
            // 退出到首页链接
            indexUrl: 'http://172.29.14.248:8081/springbootnp4n3/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "学生社团信息管理系统"
        } 
    }
}
export default base

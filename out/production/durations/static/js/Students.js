function handle(){

    $("button[event=upd]").on("click", (e)=>{

        $.ajax({
            url: "/durations/students/info",
            type: "GET",
            async: false,
            data:{
                id: $(e.target).attr("data"),
            },
            success: function(res){
                if(res.code == 0){

                    $.initForm("updForm", res.data);
                    $.model(".updWin");
                }else{
                    $.msg("error", res.msg);
                }
            }
        });
    });

    $("button[event=del]").on("click", (e)=>{

        $.confirm("确认要删除吗", () =>{

            $.ajax({
                url: "/durations/students/del",
                type: "POST",
                async: false,
                data:{
                    id: $(e.target).attr("data"),
                },
                success: function(res){
                    if(res.code == 0){
                        $.alert(res.msg, () =>{

                            window.location.reload();
                        });
                    }else{
                        $.msg("error", res.msg);
                    }
                }
            });
        });
    });
}

$(function (){

    let tableView =  {
        el: "#tableShow",
        url: "/durations/students/page",
        method: "GET",
        where: {
            pageIndex: 1,
            pageSize: 10
        },
        page: true,
        cols: [
            {
                type: "number",
                title: "序号",
            },
            {
				field: "id",
				title: "学生学号",
				align: "center",
			},
			{
                field: "userName",
                title: "学生账号",
                align: "center",
            },
            {
                field: "name",
                title: "学生姓名",
                align: "center",
            },
            {
                field: "gender",
                title: "学生性别",
                align: "center",
            },
            {
                field: "age",
                title: "学生年龄",
                align: "center",
            },
             {
                field: "majorName",
                title: "所学专业",
                align: "center",
            },
             {
                field: "grade",
                title: "所属班级",
                align: "center",
            },
            {
                title: "操作",
                template: (d)=>{

                    return `
                            <button type="button" event="upd" data="${d.id}" class="fater-btn fater-btn-primary fater-btn-sm">
                                <span data="${d.id}" class="fa fa-edit"></span>
                            </button>
                            <button type="button" event="del" data="${d.id}" class="fater-btn fater-btn-danger fater-btn-sm">
                                <span data="${d.id}" class="fa fa-trash"></span>
                            </button>
                            `;
                }
            }
        ]
    }
    $.table(tableView);

    $(".fater-btn-form-qry").on("click", ()=>{

        tableView.where["userName"] = $("[name=para1]").val();
        tableView.where["name"] = $("[name=para2]").val();
        tableView.where["majorId"] = $("[name=para3]").val();

        $.table(tableView);
        
        handle();
    });

    $("button[event=add]").on("click", ()=>{

        $.model(".addWin");
    });
    
    handle();

    $("#addFormBtn").on("click", ()=>{

        let formVal = $.getFrom("addForm");

        $.ajax({
            url: "/durations/students/add",
            type: "POST",
            data: formVal,
            success: function(res){
                if(res.code == 0){
                    $.alert(res.msg, () =>{

                        window.location.reload();
                    });
                }else{
                    $.msg("error", res.msg);
                }
            }
        });
    });

    $("#updFormBtn").on("click", ()=>{

        let formVal = $.getFrom("updForm");

        $.ajax({
            url: "/durations/students/upd",
            type: "POST",
            data: formVal,
            success: function(res){
                if(res.code == 0){
                    $.alert(res.msg, () =>{

                        window.location.reload();
                    });
                }else{
                    $.msg("error", res.msg);
                }
            }
        });
    });
});
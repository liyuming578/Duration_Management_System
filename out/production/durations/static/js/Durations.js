function handle(){

$("button[event=upd]").on("click", (e)=>{

        $.ajax({
            url: "/durations/durations/info",
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
                url: "/durations/durations/del",
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
        url: "/durations/durations/page",
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
                field: "studentName",
                title: "学生姓名",
                align: "center",
            },
            {
                field: "majorName",
                title: "所学专业",
                align: "center",
            },
            {
				field: "active",
				title: "活动时长",
				align: "center",
			},
			{
				field: "ideal",
				title: "理想信念",
				align: "center",
			},
			{
				field: "recreation",
				title: "文体时长",
				align: "center",
			},
			{
				field: "innovate",
				title: "创新创业",
				align: "center",
			},
			{
				field: "wish",
				title: "志愿时长",
				align: "center",
			},
			{
				field: "total",
				title: "时长总计",
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

        tableView.where["studentName"] = $("[name=para1]").val();
        tableView.where["type"] = $("[name=para2]").val();

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
            url: "/durations/durations/add",
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
            url: "/durations/durations/upd",
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
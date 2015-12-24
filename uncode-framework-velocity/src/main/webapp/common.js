$(document).ready(function() {
			var setting = {
					autoOpen: false,
				      show: {
				        effect: "blind",
				        duration: 1000
				      },
				      hide: {
				        effect: "explode",
				        duration: 1000
				      },
				      minWidth : 400,
				      minHeight : 300
			};
			$("#modifyDiv").dialog($.extend({},setting,{
				"title" : pageConfig.modify.dialogTitle,
				"buttons" : {
					"submit" : function() {
						$.post(pageConfig.modify.action,$("#modifyForm").serialize(),function(data) {
							if(data == "success") {
								window.location.href = pageConfig.list.action;
							} else {
								alert("Modify Error!");
							}
						});
					}
				}
			}));
			$("#addDiv").dialog($.extend({},setting,{
				"title" : pageConfig.add.dialogTitle,
				"buttons" : {
					"submit" : function() {
						$.post(pageConfig.add.action,$("#addForm").serialize(),function(data){
							if(data == "success") {
								window.location.href = pageConfig.list.action;
							} else {
								alert("Add Error!");
							}
						});
					}
				}
			}));
			$(".modifyButton").click(function() {
				var id = $(this).parent().parent().attr(pageConfig.idName);
				$("#modifyDiv").empty().load(pageConfig.modify.toAction,{
					"id" : id
				}).dialog("open");
			});
			$("#addButton").click(function() {
				$("#addDiv").empty().load(pageConfig.add.toAction).dialog("open");
			});
			$(".deleteButton").click(function() {
				var id = $(this).parent().parent().attr(pageConfig.idName);
				var flag = window.confirm("Delete Comfirm?");
				if(flag) {
					$.post(pageConfig["delete"].action,{
						"id" : id
					},function(data) {
						if(data == "success") {
							window.location.href = pageConfig.list.action;
						} else {
							alert("Delete Error!");
						}
					});
				}
			});
		});
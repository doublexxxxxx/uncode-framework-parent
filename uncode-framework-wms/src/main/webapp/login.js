Ext.onReady(function() {
	var userLoginPanel = Ext.create('Ext.panel.Panel', {
		bodyCls : 'bgimage',
		border : false,
		defaults : {
			margin : '58 0'
		},
		items : {
			xtype : 'tabpanel',
			id : 'loginTabs',
			activeTab : 0,
			height : 180,
			border : false,
			items : [ {
				title : "身份认证",
				xtype : 'form',
				id : 'loginForm',
				defaults : {
					width : 300,
					margin : '10 0 0 70'
				},
				// The fields
				defaultType : 'textfield',
				labelWidth : 40,
				items : [ {
					fieldLabel : '用户名',
					cls : 'user',
					name : 'username',
					labelAlign : 'right',
					labelWidth : 60,
					maxLength : 30,
					emptyText : '请在这里填写用户名',
					maxLengthText : '账号的最大长度为30个字符',
					blankText : "用户名不能为空，请填写！",// 错误提示信息，默认为This field is
												// required!
					allowBlank : false
				}, {
					fieldLabel : '密   码',
					cls : 'key',
					name : 'password',
					inputType : "password",
					labelWidth : 60,
					labelAlign : 'right',
					emptyText : '请在这里填写密码',
					maxLengthText : '密码长度不能超过20',
					maxLength : 20,
					blankText : "密码不能为空，请填写！",// 错误提示信息，默认为This field is
												// required!
					allowBlank : false
				}, {
					xtype : "combo",
					fieldLabel : '角   色',
					cls : 'Userkey',
					name : 'role',
					labelAlign : 'right',
					labelWidth : 60,
					store : [ "管理员", "校领导", "教职工" ],// 数据源为一数组
					emptyText : '请选择角色.',
					blankText : "请选择角色！",// 错误提示信息，默认为This field is required!
					allowBlank : false
				}, {
					id : 'id_reg_panel',
					xtype : 'panel',
					border : false,
					hidden : true,
					html : '<br><span id="messageTip" style="color:red"> </span>'
				} ]
			}, {
				title : '关于',
				contentEl : 'aboutDiv',
				defaults : {
					width : 230
				}
			} ]
		}
	});
	
	Ext.create('Ext.window.Window', {
		title : 'UNCODE FRAMEWORK WMS',
		width : 440,
		height : 300,
		layout : 'fit',
		plain : true,
		modal : true,
		maximizable : false,
		draggable : false,
		closable : false,
		resizable : false,
		items : userLoginPanel,
		// 重置 和 登录 按钮.
		buttons : [ {
			text : '重置',
			iconCls : 'Wrench',
			handler : function() {
				Ext.Msg.alert('提示', '重置！');
			}
		}, {
			text : '登录',
			iconCls : 'User',
			handler : function() {
				/*Ext.Msg.wait("请等待", "温馨提示", {
					text : '正在登录……'
				});*/
				window.location.href="main.jsp";
			}
		} ]
	}).show();
});
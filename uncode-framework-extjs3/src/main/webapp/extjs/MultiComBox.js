Ext.ns('Ext.ux');
Ext.ux.MultiComBox = Ext
		.extend(
				Ext.form.ComboBox,
				{
					splitSign : ',',
					selections : [],
					checks : [],
					hiddenValue : '',
					lastSelectionText : '',
					initList : function() {
						var cls = 'x-combo-list';
						this.tpl = '<tpl for="."><div class="'
								+ cls
								+ '-item"><table border="0" width=80% height="16px" style="height:18px;"><tr ><td class="check-box" style="width:20px;height:18px;"></td><td style="font-size:12px;">{'
								+ this.displayField + '}</td></tr></table></div></tpl>';
						Ext.ux.MultiComBox.superclass.initList.call(this);
						this.view.updateIndexes = this.updateIndexes.createDelegate(this.view);
						this.view.refresh = this.refresh.createDelegate(this.view, [ this ], 0);
						if (this.view.store) {
							this.view.setStore(this.view.store, true);
						}
					},
					refresh : function(multi) {
						this.clearSelections(false, true);
						this.el.update("");
						var records = this.store.getRange();
						if (records.length < 1) {
							if (!this.deferEmptyText || this.hasSkippedEmptyText) {
								this.el.update(this.emptyText);
							}
							this.hasSkippedEmptyText = true;
							this.all.clear();
							return;
						}
						this.tpl.overwrite(this.el, this.collectData(records, 0));
						this.all.fill(Ext.query(this.itemSelector, this.el.dom));
						multi.createCheck();
						this.updateIndexes(0);
					},
					updateIndexes : function(startIndex, endIndex) {
						var ns = this.all.elements;
						startIndex = startIndex || 0;
						endIndex = endIndex || ((endIndex === 0) ? 0 : (ns.length - 1));
						for (var i = startIndex; i <= endIndex; i++) {
							ns[i].viewIndex = i;
							if (ns[i].checkbox) {
								ns[i].checkbox.index = i;
							}
						}
					},
					createCheck : function() {
						this.checks = [];
						for (var i = 0; i < this.view.all.elements.length; i++) {
							var el = this.view.all.elements[i];
							var check = new Ext.form.Checkbox({
								width : 20,
								renderTo : Ext.getBody()
							});
							check.initCheckEvents = Ext.emptyFn();
							var m = {
								index : el.viewIndex,
								check : check,
								node : el
							};
							el.checkbox = m;
							this.checks.push(m);
							Ext.fly(el).select('.check-box').insertFirst(check.wrap);
						}
					},
					findCheckBox : function(index) {
						for (var i = 0; i < this.checks.length; i++) {
							if (this.checks[i].index == index)
								return this.checks[i];
						}
						return null;
					},
					onSelect : function(record, index, checked) {
						if (this.fireEvent('beforeselect', this, record, index) !== false) {
							if (!record)
								return;
							var checkObj = this.findCheckBox(index);
							var checkbox = checkObj && checkObj.check;
							if (!checkbox)
								return;
							if (checked == undefined)
								checked = checkbox.checked;
							this.toggleCheckBox(index, checked, record, checkbox);
							this.select(index, false);// 用来设定选择样式
							this.fireEvent('select', this, record, index);
						}
					},
					toggleCheckBox : function(index, checked, r, item) {
						if (checked == false) {
							item.setValue(1);
							if (this.isExist(index) == true)
								return;
							this.selections.push({
								record : r,
								index : index
							});
						} else {
							item.setValue(0);
							for (var i = 0; i < this.selections.length; i++) {
								if (index == this.selections[i].index)
									this.selections.remove(this.selections[i]);
							}
						}
						this.setValue(r.data[this.valueField || this.displayField], checked);
					},

					isExist : function(index) {
						for (var i = 0; i < this.selections.length; i++) {
							if (this.selections[i].index == index)
								return true;
						}
						return false;
					},

					setValue : function(v, checked) {
						var text = this.lastSelectionText;
						var hiddenValue = this.hiddenValue;
						var values = v.toString().split(this.splitSign);
						for (i = 0, l = values.length; i < l; i++) {
							var r = this.findRecord(this.valueField, values[i]);
							if (r) {
								var name = r.data[this.displayField], value = r.data[this.valueField];
								var split = Ext.escapeRe(this.splitSign);
								var con = Ext.escapeRe(name.toString()), val = Ext.escapeRe(value.toString());
								var nemeRe = new RegExp("(^" + con + "[" + split + "]?" + ")" + "|([" + split + "]?" + con + ")", 'g');
								var valueRe = new RegExp("(^" + val + "[" + split + "]?" + ")" + "|([" + split + "]?" + val + ")", 'g');

								if (checked == false || typeof checked == "undefined") {
									text = text.replace(nemeRe, "");
									hiddenValue = hiddenValue.replace(valueRe, "");
									var separate = !text ? "" : this.splitSign;
									text = text + separate + name;
									hiddenValue = hiddenValue + separate + value;
								} else {
									text = text.replace(nemeRe, "");
									hiddenValue = hiddenValue.replace(valueRe, "");
								}
							}
						}
						this.lastSelectionText = text;
						Ext.form.ComboBox.superclass.setValue.call(this, text);
						this.hiddenValue = hiddenValue || this.hiddenValue;

						if (this.hiddenField) {
							this.hiddenField.value = this.hiddenValue;
						}
						this.value = this.hiddenValue;

					},

					getValue : function() {
						return Ext.ux.MultiComBox.superclass.getValue.call(this);
					},

					selectByValue : function(v, scrollIntoView) {
						var value = this.getRawValue().trim() || "";
						var v1 = value.trim().split(this.splitSign);
						for (var i = 0; i < v1.length; i++) {
							var v = v1[i];
							if (v) {
								var r = this.findRecord(this.displayField, v);
								this.onSelect(r, this.store.indexOf(r), false);
							}
						}
					},
					getRawValue : function(flag) {
						var v = this.rendered ? this.el.getValue() : Ext.value(this.value, '');
						if (v === this.emptyText) {
							v = '';
						}
						if (flag != true)
							return v;
						var v1 = v.trim().split(this.splitSign);
						if (v1.length > 0) {
							var v2 = "";
							for (var i = 0; i < v1.length; i++) {
								if (v1[i])
									v2 = v2 + "(" + v1[i] + ")" + "|";
							}
							if (v2.length - 2 > 0)
								v2 = v2.substring(0, v2.length - 1);
							v = new RegExp(v2);
							v.length = v2.length;
						}
						return v;
					},

					onLoad : function() {
						if (!this.hasFocus) {
							return;
						}
						if (this.store.getCount() > 0) {
							this.expand();
							this.restrictHeight();
							if (this.lastQuery == this.allQuery) {
								if (this.editable)
									this.el.dom.select();
								this.selectByValue(this.value, true);
							} else {
								this.selectByValue(this.value, true)
							}
						} else {
							this.onEmptyResults();
						}
					},
					initQuery : function() {
						this.doQuery(this.getRawValue(true));
					},
					onTriggerClick : function() {
						if (this.disabled) {
							return;
						}
						if (this.isExpanded()) {
							this.collapse();
							this.el.focus();
						} else {
							this.onFocus({});
							if (this.triggerAction == 'all') {
								this.doQuery(this.allQuery, true);
							} else {
								this.doQuery(this.getRawValue(true));
							}
							this.el.focus();
						}
					}

				});
Ext.reg('multiCombox', Ext.ux.MultiComBox);
/*
 * Ext.onReady(function() { var myData = [[3300, "彭仁夔"], [3301, "李明"], [3302,
 * "王华"], [3303, "张三"], [3304, "李四"], [3305, "王五"], [3306, "彭小明"], [3307, "张华"],
 * [3308, "李小"]];
 * 
 * var store1 = new Ext.data.SimpleStore( { fields : [ { name : 'value', type :
 * 'string' }, { name : 'name', type : 'string' }], data : myData });
 * 
 * var test = new Ext.ux.MultiComBox( { store : store1, displayField : 'name',
 * valueField : 'value', // typeAhead : true, triggerAction : 'all', mode :
 * 'local', emptyText : '请选择...', selectOnFocus : true, loadingText :
 * 'loading....' }) test.render(Ext.getBody()); });
 */
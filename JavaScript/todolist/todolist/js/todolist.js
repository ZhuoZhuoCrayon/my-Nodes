todoItem = `<span class="todo-work" onclick="edit(this)">want to do</span>
			<input type="button" value="done" onclick="done(this)"/>
			<input type="button" value="del" onclick="del(this,1)"/>`
doneItem = `<span class="finish-work" onclick="edit(this)">finish task</span>
			<input type="button" value="todo" onclick="todo(this)"/>
			<input type="button" value="del" onclick="del(this,2)"/>`

/*在网页的输入框添加事件的响应*/
function add(){
	var task = document.getElementById('add').value;
	document.getElementById('add').value = '';		//重置输入框
	if(task.length===0){
		alert('添加事件为空!')
		return false;
	}
	if(changeCache(task,'add')===false){
		alert("不能添加重复事件!");
		return false;
	}
	var 
		todo_list = document.getElementById("todo-list"),
		new_task = document.createElement('li'),
		addHtmlCode = todoItem.replace('want to do',task);
	new_task.innerHTML = addHtmlCode;
	todo_list.appendChild(new_task);
	
	return true;
}
/*网页端done按钮的响应*/
function done(done_task){
	// 回到父节点li
	done_task = done_task.parentElement;
	changeCache(done_task.innerText.trim(),'changeTo_done');	//修改缓存该事件的状态
	var 
		finish_list = document.getElementById('finish-list'),
		todo_list = document.getElementById('todo-list'),
		addHtmlCode = doneItem.replace('finish task',done_task.innerText.trim());
	/*把该事件从todo放入done*/
	todo_list.removeChild(done_task);
	done_task.innerHTML = addHtmlCode;
	finish_list.appendChild(done_task);
}
/*网页端todo按钮响应*/
function todo(todo_task){
	// 回到父节点li
	todo_task = todo_task.parentElement;
	changeCache(todo_task.innerText.trim(),'changeTo_todo');	//修改缓存该事件的状态
	var
		finish_list = document.getElementById('finish-list'),
		todo_list = document.getElementById('todo-list'),
		addHtmlCode = todoItem.replace('want to do',todo_task.innerText.trim());
		/*把该事件从done放入todo*/
		finish_list.removeChild(todo_task);
		todo_task.innerHTML = addHtmlCode;
		todo_list.appendChild(todo_task);
}

/*网页端del按钮响应*/
function del(del_task,method){
	del_task = del_task.parentElement;	//回到父节点
	/*从todo端删除*/
	if(method===1){
		var todo_list = document.getElementById('todo-list');
		todo_list.removeChild(del_task);
	}else if(method===2){	/*从done端删除*/
		var finish_list = document.getElementById('finish-list');
		finish_list.removeChild(del_task);
	}
	changeCache(del_task.innerText.trim(),"del");	//修改缓存-删除该事件
}

function edit(task){
	task_name = task.innerText.trim();
	task.innerHTML = '<input type="text" id="change" value="'+ task_name + '" />';
	var input = document.getElementById("change");
	input.setSelectionRange(0,input.value.length);
	input.focus();
	input.onblur =function(){
		if(input.value.length == 0){
			p.innerHTML = title;
			alert("内容不能为空");
		}
		else{
			update(i,"title",input.value);
		}
	};
}

/*初始化加入事件*/
function addTask(task){
	console.log(task);
	if(task.status==='todo'){
		//alert("todo");
		var
			todo_list = document.getElementById("todo-list"),
			new_task = document.createElement('li'),
			addHtmlCode = todoItem.replace('want to do',task.name);
		new_task.innerHTML = addHtmlCode;
		todo_list.appendChild(new_task);
	}else if(task.status==='done'){
		var
			finish_list = document.getElementById('finish-list'),
			done_task = document.createElement('li'),
			addHtmlCode = doneItem.replace('finish task',task.name);
		done_task.innerHTML = addHtmlCode;
		finish_list.appendChild(done_task);
	}else{
		alert("非法访问：addTask/status");
	}
}

/*缓存修改机制*/
function changeCache(task_name,method){
	taskObj = JSON.parse(localStorage.todolistCache);	//获取todolist缓存对象
	if(method==='add'){
		//存在键值，不允许添加重复事件
		if(task_name in taskObj.tasks){
			return false;
		}
		var new_task = {
			name:task_name,
			status:'todo'
		}
		taskObj.tasks[task_name]=new_task;
		//console.log(taskObj);
	}else if(method==='del'){
		//alert(task_name+"*");
		delete taskObj.tasks[task_name]
	}else if(method==='changeTo_todo'){
		taskObj.tasks[task_name].status = 'todo';
	}else if(method==='changeTo_done'){
		taskObj.tasks[task_name].status = 'done';
	}else{
		alert("功能未开放");
	}
	//更新缓存
	localStorage.todolistCache = JSON.stringify(taskObj);
	return true;
}

//清空todolist缓存
function clearCache(){
	if(confirm("确认清空所有所有记录？？？")){
		localStorage.todolistCache = '{"tasks":{}}';
		location.reload();		//更新页面
	}
}
function loadAll(){
	//查看是否支持本地缓存
	if(typeof(Storage)!=='undefined'){
		//查看是否存在todolist缓存
		if(localStorage.todolistCache){
			//初始化缓存
			console.log(localStorage.todolistCache);
			taskObj= JSON.parse(localStorage.todolistCache);
			for(var task in taskObj.tasks){
				//alert(task.name+" "+task.status);
				addTask(taskObj.tasks[task]);
			}
		}else{
			//第一次加载，初始化缓存信息
			localStorage.todolistCache ='{"tasks":{}}';
		}
	}else{
		alert("不支持！")
	}
}


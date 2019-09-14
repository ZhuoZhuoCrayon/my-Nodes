function add(){
	var task = document.getElementById('add').value;
	if(task.length===0){
		alert('添加事件为空!')
		return false;
	}
	var addHtmlCode = `<span class="todo-work">want to do</span>
						<input type="button" value="done"/>
						<input type="button" value="del"/>`.replace('want to do',task);
	var todo_list = document.getElementById("todo-list");
	var new_task = document.createElement('li');
	new_task.innerHTML = addHtmlCode
	todo_list.appendChild(new_task);
	
	return true;
}
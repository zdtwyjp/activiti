package helloworld;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

public class HelloWorldTest2 extends PluggableActivitiTestCase {

	/**
	 * Deployment(resources = {"bpmn20/helloworld/helloworld.bpmn20.xml"})相当于通过repositoryServcie发布流程定义
	 */
	@Deployment(resources = {"bpmn20/helloworld/helloworld.bpmn20.xml"})
	public void testProcess() {
		// 启动流程实例<process id="process">
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("process");
		// HelloTask1任务
		//查询当前任务
		Task task = taskService.createTaskQuery().singleResult();
		
		assertTrue("HelloTask1".equals(task.getName()));
		//完成helloTask1任务
		taskService.complete(task.getId());
		
		//HelloTask2任务
		task = taskService.createTaskQuery().singleResult();
		assertTrue("HelloTask2".equals(task.getName()));
		
		//完成HelloTask2任务
		taskService.complete(task.getId());
		
		assertProcessEnded(processInstance.getId());
	}

}

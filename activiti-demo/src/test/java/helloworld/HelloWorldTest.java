package helloworld;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class HelloWorldTest {
	@Test
	public void test() {
		// 加载activiti.cfg.xml产生配置对象
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResourceDefault();
		// 得到ProcessEngine
		ProcessEngine processEngine = processEngineConfiguration
				.buildProcessEngine();
		// 得到repositoryServcie：操作流程定义
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		// 发布流程定义到数据库
		repositoryService
				.createDeployment()
				.addClasspathResource("bpmn20/helloworld/helloworld.bpmn20.xml")
				.deploy();
		// 得到RuntimeServcie：操作流程实例
		RuntimeService runtimeService = processEngine.getRuntimeService();
		// 启动流程实例<process id="process">
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey("process");
		// HelloTask1任务
		// 得到TaskSercie：操作任务
		TaskService taskService = processEngine.getTaskService();
		// 查询当前任务
		Task task = taskService.createTaskQuery().singleResult();
		System.out.println(task.getName());
		// 完成任务
		taskService.complete(task.getId());
		// HelloTask2任务
		task = taskService.createTaskQuery().singleResult();
		System.out.println(task.getName());
	}
}
